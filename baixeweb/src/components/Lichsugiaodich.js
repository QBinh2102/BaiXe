import { useContext, useEffect, useState } from "react";
import { MyUserContext } from "../configs/Contexts";
import { authApis, endpoints } from "../configs/Apis";

function Lichsugiaodich() {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
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

  if (loading) return <p>Đang tải dữ liệu lịch sử giao dịch...</p>;
  if (error) return <p style={{ color: "red" }}>{error}</p>;

  return (
    <div style={{ maxWidth: 900, margin: "auto", padding: 20 }}>
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
                 {booking.trangThai === "da_dat" && new Date(booking.thoiGianBatDau) > new Date() && (
                  <button onClick={() => handleRefundRequest(booking.id)}>
                    Yêu cầu hoàn tiền
                  </button>
                )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
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
