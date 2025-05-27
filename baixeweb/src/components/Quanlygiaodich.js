import React, { useEffect, useState, useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { MyUserContext } from '../configs/Contexts';

function Quanlygiaodich() {
  const [hoaDons, setHoaDons] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const user = useContext(MyUserContext);

  useEffect(() => {
    if (user?.vaiTro !== 'ROLE_ADMIN') return; 

    fetch('http://localhost:8080/SpringBaiXeThongMinh/api/hoadons')
      .then(res => {
        if (!res.ok) throw new Error('Lỗi khi lấy dữ liệu');
        return res.json();
      })
      .then(data => {
        const sorted = data.sort((a, b) =>
          new Date(b.thoiGianThanhToan) - new Date(a.thoiGianThanhToan)
        );
        setHoaDons(sorted);
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  }, [user]);

  // Đặt sau hooks, an toàn cho React
  if (!user || user.vaiTro !== 'ROLE_ADMIN') {
    return <Navigate to="/trangchu" replace />;
  }

  if (loading) return <p style={styles.statusMessage}>Đang tải dữ liệu...</p>;
  if (error) return <p style={{ ...styles.statusMessage, ...styles.error }}>{`Lỗi: ${error}`}</p>;

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>Danh sách Hóa đơn</h2>
      {hoaDons.length === 0 ? (
        <p style={styles.statusMessage}>Không có hóa đơn nào.</p>
      ) : (
        <div style={styles.tableWrapper}>
          <table style={styles.table}>
            <thead>
              <tr>
                <th style={styles.th}>ID</th>
                <th style={styles.th}>ID Người Dùng</th>
                <th style={styles.th}>ID Booking</th>
                <th style={styles.th}>Phương Thức</th>
                <th style={styles.th}>Thời Gian Thanh Toán</th>
                <th style={styles.th}>Trạng Thái</th>
                <th style={styles.th}>Mã Giao Dịch</th>
              </tr>
            </thead>
            <tbody>
              {hoaDons.map((hd, idx) => (
                <tr
                  key={hd.id}
                  style={idx % 2 === 0 ? styles.trEven : styles.trOdd}
                  onMouseEnter={e => (e.currentTarget.style.backgroundColor = '#e8f0fe')}
                  onMouseLeave={e =>
                    (e.currentTarget.style.backgroundColor = idx % 2 === 0 ? styles.trEven.backgroundColor : styles.trOdd.backgroundColor)
                  }
                >
                  <td style={styles.td}>{hd.id}</td>
                  <td style={styles.td}>{hd.idNguoiDung?.id || 'Chưa có'}</td>
                  <td style={styles.td}>{hd.idBooking?.id || 'Chưa có'}</td>
                  <td style={styles.td}>{hd.phuongThuc}</td>
                  <td style={styles.td}>{new Date(hd.thoiGianThanhToan).toLocaleString()}</td>
                  <td style={styles.td}>{hd.trangThai}</td>
                  <td style={styles.td}>{hd.maGD}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}



const styles = {
  container: {
    padding: 20,
    maxWidth: 1200,
    margin: '0 auto',
    fontFamily: 'Arial, sans-serif',
    fontSize: 14,
  },
  title: {
    textAlign: 'center',
    color: '#333',
  },
  statusMessage: {
    textAlign: 'center',
    marginTop: 20,
  },
  error: {
    color: 'red',
  },
  tableWrapper: {
    overflowX: 'auto',
    marginTop: 20,
    boxShadow: '0 0 10px rgba(0,0,0,0.1)',
  },
  table: {
    borderCollapse: 'collapse',
    width: '100%',
    maxWidth: '100%',
  },
  th: {
    backgroundColor: '#4caf50',
    color: 'white',
    padding: 12,
    textAlign: 'center',
    position: 'sticky',
    top: 0,
    zIndex: 1,
    border: '1px solid #ddd',
  },
  td: {
    border: '1px solid #ddd',
    padding: 10,
    textAlign: 'center',
  },
  trEven: {
    backgroundColor: 'white',
  },
  trOdd: {
    backgroundColor: '#f9f9f9',
  },
};

export default Quanlygiaodich;
