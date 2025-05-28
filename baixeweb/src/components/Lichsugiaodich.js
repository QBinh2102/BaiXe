import { useEffect, useState } from "react";

function Lichsugiaodich() {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);


  const idNguoiDung = localStorage.getItem("idNguoiDung");

  useEffect(() => {
  if (!idNguoiDung) {
    setError("Bạn chưa đăng nhập");
    setLoading(false);
    return;
  }

  const fetchBookings = async () => {
    try {
      
       const res = await fetch(`http://localhost:8080/SpringBaiXeThongMinh/api/bookings/user/${idNguoiDung}`);

      if (!res.ok) throw new Error("Lỗi khi tải dữ liệu booking");

      const data = await res.json();
      data.sort((a, b) => new Date(b.thoiGianDat) - new Date(a.thoiGianDat));

      setBookings(data);
    } catch (err) {
      setError(err.message || "Lỗi không xác định");
    } finally {
      setLoading(false);
    }
  };

  fetchBookings();
}, [idNguoiDung]);

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
                <td style={tdStyle}>{booking.thanhTien?.toLocaleString("vi-VN", { style: "currency", currency: "VND" }) || 0}</td>
                <td style={tdStyle}>{booking.trangThai}</td>
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
