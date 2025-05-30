import { useContext, useEffect, useState } from "react";
import { MyUserContext } from "../configs/Contexts";
import { authApis, endpoints } from "../configs/Apis";

function Lichsugiaodich() {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [showInvoiceModal, setShowInvoiceModal] = useState(false);
  const [selectedInvoice, setSelectedInvoice] = useState(null);
  const [loadingInvoice, setLoadingInvoice] = useState(false);
  const current_user = useContext(MyUserContext);

  const idNguoiDung = current_user.id;

  useEffect(() => {
    if (!idNguoiDung) {
      setError("Bạn chưa đăng nhập");
      setLoading(false);
      return;
    }

    const fetchBookings = async () => {
      try {
        const res = await authApis().get(endpoints['bookingNguoiDung']);
        const sortedData = res.data.sort((a, b) => b.id - a.id);
        setBookings(sortedData);
      } catch (err) {
        setError(err.message || "Lỗi không xác định");
      } finally {
        setLoading(false);
      }
    };

    fetchBookings();
  }, [idNguoiDung]);

  const handleRefundRequest = async (idBooking) => {
    const booking = bookings.find(b => b.id === idBooking);
    if (!booking) {
      alert("Không tìm thấy booking.");
      return;
    }

    const now = new Date();
    const startTime = new Date(booking.thoiGianBatDau);
    const diffMs = startTime - now;
    const diffHours = diffMs / (1000 * 60 * 60);

    if (diffHours < 1) {
      alert("Bạn chỉ có thể yêu cầu hoàn tiền trước thời gian bắt đầu ít nhất 1 tiếng.");
      return;
    }

    const formattedStart = formatDateTime(booking.thoiGianBatDau);
    const formattedMoney = booking.thanhTien?.toLocaleString("vi-VN", {
      style: "currency",
      currency: "VND",
    }) || "0đ";

    const confirmMsg = `
Bạn sắp gửi yêu cầu hoàn tiền cho:
- Mã booking: ${booking.id}
- Bãi đỗ: ${booking.idBaiDo?.ten || "N/A"}
- Chỗ đỗ: ${booking.idChoDo?.viTri || "N/A"}
- Thời gian bắt đầu: ${formattedStart}
- Số tiền: ${formattedMoney}

⚠️ Yêu cầu hoàn tiền sẽ được gửi lên hệ thống và chờ xử lý.
Bạn có chắc chắn muốn tiếp tục?
    `.trim();

    const confirmed = window.confirm(confirmMsg);
    if (!confirmed) return;

    try {
      const res = await authApis().patch(
        `http://localhost:8080/SpringBaiXeThongMinh/api/secure/bookings/${idBooking}/status/?trangThai=yeu_cau_hoan_tien`
      );

      alert("✅ Yêu cầu hoàn tiền đã được gửi.");
      setBookings(prev =>
        prev.map(b => b.id === idBooking ? { ...b, trangThai: "yeu_cau_hoan_tien" } : b)
      );
    } catch (error) {
      alert("❌ Lỗi: " + error.message);
    }
  };

  // Hàm xem hóa đơn đã được sửa để truyền trực tiếp booking object
  const handleViewInvoice = async (bookingId, bookingObj) => {
    setLoadingInvoice(true);
    try {
      const res = await authApis().get(`/secure/me/hoadons/booking/${bookingId}/`);
      
      if (res.data) {
        // Gắn thông tin booking trực tiếp vào invoice
        const invoiceWithBooking = {
          ...res.data,
          bookingDetails: bookingObj
        };
        
        setSelectedInvoice(invoiceWithBooking);
        setShowInvoiceModal(true);
      } else {
        alert("Không tìm thấy hóa đơn cho booking này.");
      }
    } catch (error) {
      console.error("Error fetching invoice:", error);
      
      if (error.response?.status === 404) {
        alert("Không tìm thấy hóa đơn cho booking này.");
      } else if (error.response?.status === 403) {
        alert("Bạn không có quyền xem hóa đơn này.");
      } else if (error.response?.status === 401) {
        alert("Bạn cần đăng nhập để xem hóa đơn.");
      } else {
        alert("❌ Lỗi khi tải hóa đơn: " + (error.message || "Lỗi không xác định"));
      }
    } finally {
      setLoadingInvoice(false);
    }
  };

  const handleDownloadPDF = () => {
    if (!selectedInvoice) return;

   
    const invoiceHTML = generateInvoiceHTML(selectedInvoice);
    
   
    const printWindow = window.open('', '_blank');
    printWindow.document.write(invoiceHTML);
    printWindow.document.close();
    
    // Đợi load xong rồi in
    printWindow.onload = function() {
      printWindow.print();
      printWindow.close();
    };
  };

  const generateInvoiceHTML = (invoice) => {
    const booking = invoice.bookingDetails; // Sử dụng bookingDetails thay vì tìm trong array
    
    return `
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hóa đơn điện tử</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .invoice-header { text-align: center; margin-bottom: 30px; }
        .invoice-title { font-size: 24px; font-weight: bold; color: #2c5aa0; }
        .invoice-info { display: flex; justify-content: space-between; margin-bottom: 20px; }
        .invoice-details { border: 1px solid #ddd; padding: 15px; margin-bottom: 20px; }
        .detail-row { display: flex; justify-content: space-between; margin-bottom: 10px; }
        .total-section { background-color: #f9f9f9; padding: 15px; border: 2px solid #2c5aa0; }
        .total-amount { font-size: 18px; font-weight: bold; color: #2c5aa0; }
        .footer { text-align: center; margin-top: 30px; font-style: italic; }
    </style>
</head>
<body>
    <div class="invoice-header">
        <h1 class="invoice-title">HÓA ĐƠN ĐIỆN TỬ</h1>
        <p>Hệ thống đặt chỗ đỗ xe thông minh</p>
    </div>
    
    <div class="invoice-info">
        <div>
            <strong>Số hóa đơn:</strong> ${invoice.id}<br>
            <strong>Ngày tạo:</strong> ${formatDateTime(invoice.thoiGianThanhToan)}
        </div>
        <div>
            <strong>Mã giao dịch:</strong> ${invoice.maGD || 'N/A'}<br>
            <strong>Phương thức:</strong> ${invoice.phuongThuc}
        </div>
    </div>
    
    <div class="invoice-details">
        <h3>Thông tin booking</h3>
        <div class="detail-row">
            <span>Mã booking:</span>
            <span>${booking?.id || 'N/A'}</span>
        </div>
        <div class="detail-row">
            <span>Bãi đỗ xe:</span>
            <span>${booking?.idBaiDo?.ten || 'N/A'}</span>
        </div>
        <div class="detail-row">
            <span>Vị trí chỗ đỗ:</span>
            <span>${booking?.idChoDo?.viTri || 'N/A'}</span>
        </div>
        <div class="detail-row">
            <span>Thời gian bắt đầu:</span>
            <span>${formatDateTime(booking?.thoiGianBatDau)}</span>
        </div>
        <div class="detail-row">
            <span>Thời gian kết thúc:</span>
            <span>${formatDateTime(booking?.thoiGianKetThuc)}</span>
        </div>
    </div>
    
    <div class="total-section">
        <div class="detail-row total-amount">
            <span>Tổng tiền thanh toán:</span>
            <span>${booking?.thanhTien.toLocaleString("vi-VN", {
              style: "currency",
              currency: "VND",
            }) || '0đ'}</span>
        </div>
        <div class="detail-row">
            <span>Trạng thái:</span>
            <span>${invoice.trangThai === 'thanh_cong' ? 'Thành công' : invoice.trangThai}</span>
        </div>
    </div>
    
    <div class="footer">
        <p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!</p>
        <p>Hóa đơn được tạo tự động bởi hệ thống - ${new Date().toLocaleString('vi-VN')}</p>
    </div>
</body>
</html>
    `;
  };

  if (loading) return <p>Đang tải dữ liệu lịch sử giao dịch...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <div style={{ maxWidth: 1200, margin: "auto", padding: 20 }}>
      <h2>Lịch sử giao dịch</h2>

      {bookings.length === 0 ? (
        <p>Bạn chưa có giao dịch nào.</p>
      ) : (
        <table style={{ width: "100%", borderCollapse: "collapse" }}>
          <thead>
            <tr style={{ backgroundColor: "#eee" }}>
              <th style={thStyle}>Mã booking</th>
              <th style={thStyle}>Bãi đỗ</th>
              <th style={thStyle}>Chỗ đỗ</th>
              <th style={thStyle}>Thời gian đặt</th>
              <th style={thStyle}>Thời gian bắt đầu</th>
              <th style={thStyle}>Thời gian kết thúc</th>
              <th style={thStyle}>Thành tiền</th>
              <th style={thStyle}>Trạng thái</th>
              <th style={thStyle}>Hành động</th>
            </tr>
          </thead>
          <tbody>
            {bookings.map((booking) => (
              <tr key={booking.id} style={{ borderBottom: "1px solid #ccc" }}>
                <td style={tdStyle}>{booking.id}</td>
                <td style={tdStyle}>{booking.idBaiDo?.ten || "N/A"}</td>
                <td style={tdStyle}>{booking.idChoDo?.viTri || "N/A"}</td>
                <td style={tdStyle}>{formatDateTime(booking.thoiGianDat)}</td>
                <td style={tdStyle}>{formatDateTime(booking.thoiGianBatDau)}</td>
                <td style={tdStyle}>{formatDateTime(booking.thoiGianKetThuc)}</td>
                <td style={tdStyle}>
                  {booking.thanhTien?.toLocaleString("vi-VN", {
                    style: "currency",
                    currency: "VND",
                  }) || 0}
                </td>
                <td style={tdStyle}>{booking.trangThai}</td>
                <td style={tdStyle}>
                  <div style={{ display: 'flex', gap: '5px', flexDirection: 'column' }}>
                    {booking.trangThai === "da_dat" && (
                      <>
                        <button 
                          onClick={() => handleViewInvoice(booking.id, booking)}
                          disabled={loadingInvoice}
                          style={{
                            backgroundColor: '#4CAF50',
                            color: 'white',
                            border: 'none',
                            padding: '5px 10px',
                            borderRadius: '3px',
                            cursor: 'pointer',
                            fontSize: '12px'
                          }}
                        >
                          {loadingInvoice ? 'Đang tải...' : 'Xem hóa đơn'}
                        </button>
                        {new Date(booking.thoiGianBatDau) > new Date() && (
                          <button 
                            onClick={() => handleRefundRequest(booking.id)}
                            style={{
                              backgroundColor: '#f44336',
                              color: 'white',
                              border: 'none',
                              padding: '5px 10px',
                              borderRadius: '3px',
                              cursor: 'pointer',
                              fontSize: '12px'
                            }}
                          >
                            Yêu cầu hoàn tiền
                          </button>
                        )}
                      </>
                    )}
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {/* Modal hiển thị hóa đơn */}
      {showInvoiceModal && selectedInvoice && (
        <div style={modalOverlayStyle}>
          <div style={modalContentStyle}>
            <div style={modalHeaderStyle}>
              <h3>Hóa đơn điện tử</h3>
              <button 
                onClick={() => setShowInvoiceModal(false)}
                style={closeButtonStyle}
              >
                ×
              </button>
            </div>
            
            <div style={invoiceContentStyle}>
              <div style={invoiceHeaderStyle}>
                <h2 style={{ color: '#2c5aa0', textAlign: 'center' }}>HÓA ĐƠN ĐIỆN TỬ</h2>
                <p style={{ textAlign: 'center', margin: '5px 0' }}>Hệ thống đặt chỗ đỗ xe thông minh</p>
              </div>

              <div style={invoiceInfoStyle}>
                <div>
                  <strong>Số hóa đơn:</strong> {selectedInvoice.id}<br/>
                  <strong>Ngày tạo:</strong> {formatDateTime(selectedInvoice.thoiGianThanhToan)}
                </div>
                <div>
                  <strong>Mã giao dịch:</strong> {selectedInvoice.maGD || 'N/A'}<br/>
                  <strong>Phương thức:</strong> {selectedInvoice.phuongThuc}
                </div>
              </div>

              <div style={invoiceDetailsStyle}>
                <h4>Thông tin booking</h4>
                {(() => {
                  // Sử dụng bookingDetails từ selectedInvoice
                  const booking = selectedInvoice.bookingDetails;
                  
                  if (!booking) {
                    return (
                      <div style={{ color: '#ff6b6b', fontStyle: 'italic' }}>
                        <p>⚠️ Không tìm thấy thông tin chi tiết booking</p>
                      </div>
                    );
                  }
                  
                  return (
                    <div>
                      <div style={detailRowStyle}>
                        <span>Mã booking:</span>
                        <span>{booking.id}</span>
                      </div>
                      <div style={detailRowStyle}>
                        <span>Bãi đỗ xe:</span>
                        <span>{booking.idBaiDo?.ten || 'Chưa có thông tin'}</span>
                      </div>
                      <div style={detailRowStyle}>
                        <span>Vị trí chỗ đỗ:</span>
                        <span>{booking.idChoDo?.viTri || 'Chưa có thông tin'}</span>
                      </div>
                      <div style={detailRowStyle}>
                        <span>Thời gian bắt đầu:</span>          
                        <span>{formatDateTime(booking.thoiGianBatDau)}</span>
                      </div>
                      <div style={detailRowStyle}>
                        <span>Thời gian kết thúc:</span>
                        <span>{formatDateTime(booking.thoiGianKetThuc)}</span>
                      </div>
                    </div>
                  );
                })()}
              </div>

              <div style={totalSectionStyle}>
                <div style={detailRowStyle}>
                  <span style={{ fontSize: '18px', fontWeight: 'bold' }}>Tổng tiền thanh toán:</span>
                  <span style={{ fontSize: '18px', fontWeight: 'bold', color: '#2c5aa0' }}>
                    {(selectedInvoice.bookingDetails?.thanhTien)?.toLocaleString("vi-VN", {
                    style: "currency", 
                    currency: "VND",
                  }) || '0đ'}
                  </span>
                </div>
                <div style={detailRowStyle}>
                  <span>Trạng thái:</span>
                  <span>{selectedInvoice.trangThai === 'thanh_cong' ? 'Thành công' : selectedInvoice.trangThai}</span>
                </div>
              </div>
            </div>

            <div style={modalFooterStyle}>
              <button 
                onClick={handleDownloadPDF}
                style={downloadButtonStyle}
              >
                📄 Tải xuống PDF
              </button>
              <button 
                onClick={() => setShowInvoiceModal(false)}
                style={cancelButtonStyle}
              >
                Đóng
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}


const thStyle = {
  border: "1px solid #ddd",
  padding: "8px",
  textAlign: "left",
};

const tdStyle = {
  border: "1px solid #ddd",
  padding: "8px",
};

const modalOverlayStyle = {
  position: 'fixed',
  top: 0,
  left: 0,
  right: 0,
  bottom: 0,
  backgroundColor: 'rgba(0, 0, 0, 0.5)',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  zIndex: 1000,
};

const modalContentStyle = {
  backgroundColor: 'white',
  borderRadius: '8px',
  width: '90%',
  maxWidth: '800px',
  maxHeight: '90%',
  overflow: 'auto',
  boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
};

const modalHeaderStyle = {
  display: 'flex',
  justifyContent: 'space-between',
  alignItems: 'center',
  padding: '20px',
  borderBottom: '1px solid #eee',
};

const closeButtonStyle = {
  background: 'none',
  border: 'none',
  fontSize: '24px',
  cursor: 'pointer',
  color: '#999',
};

const invoiceContentStyle = {
  padding: '20px',
};

const invoiceHeaderStyle = {
  marginBottom: '20px',
};

const invoiceInfoStyle = {
  display: 'flex',
  justifyContent: 'space-between',
  marginBottom: '20px',
  fontSize: '14px',
};

const invoiceDetailsStyle = {
  border: '1px solid #ddd',
  padding: '15px',
  marginBottom: '20px',
  borderRadius: '4px',
};

const detailRowStyle = {
  display: 'flex',
  justifyContent: 'space-between',
  marginBottom: '10px',
};

const totalSectionStyle = {
  backgroundColor: '#f9f9f9',
  padding: '15px',
  border: '2px solid #2c5aa0',
  borderRadius: '4px',
};

const modalFooterStyle = {
  display: 'flex',
  justifyContent: 'flex-end',
  gap: '10px',
  padding: '20px',
  borderTop: '1px solid #eee',
};

const downloadButtonStyle = {
  backgroundColor: '#2c5aa0',
  color: 'white',
  border: 'none',
  padding: '10px 20px',
  borderRadius: '4px',
  cursor: 'pointer',
  fontSize: '14px',
};

const cancelButtonStyle = {
  backgroundColor: '#6c757d',
  color: 'white',
  border: 'none',
  padding: '10px 20px',
  borderRadius: '4px',
  cursor: 'pointer',
  fontSize: '14px',
};

function formatDateTime(dateTimeStr) {
  if (!dateTimeStr) return "";
  const dt = new Date(dateTimeStr);
  if (isNaN(dt)) return "";
  return dt.toLocaleString("vi-VN", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
  });
}

export default Lichsugiaodich;