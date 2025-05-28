import React, { useEffect, useState,useContext} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { Navigate } from 'react-router-dom';
import { MyUserContext } from '../configs/Contexts';

function QuanlyNguoidung() {
  const [nguoiDungs, setNguoiDungs] = useState([]);
  const [keyword, setKeyword] = useState('');
  const navigate = useNavigate();
   const user = useContext(MyUserContext);

  const baseURL = 'http://localhost:8080/SpringBaiXeThongMinh/api/nguoidungs';

  useEffect(() => {
    if (user?.vaiTro !== 'ROLE_ADMIN') return; 
    fetchNguoiDungs();
  }, []);

  const fetchNguoiDungs = async (params = {}) => {
    try {
      const res = await axios.get(baseURL, { params });
      setNguoiDungs(res.data);
    } catch (err) {
      console.error('Lỗi khi tải người dùng:', err);
    }
  };

  const handleSearch = () => {
    fetchNguoiDungs({ kw: keyword });
  };

  if (!user || user.vaiTro !== 'ROLE_ADMIN') {
      return <Navigate to="/trangchu" replace />;
    }

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>Quản lý người dùng</h2>

      <div style={styles.searchBar}>
        <input
          type="text"
          placeholder="Tìm kiếm theo họ tên hoặc tài khoản..."
          value={keyword}
          onChange={e => setKeyword(e.target.value)}
          style={styles.searchInput}
        />
        <button onClick={handleSearch} style={styles.searchButton}>
          Tìm kiếm
        </button>
      </div>

      <div style={styles.tableWrapper}>
        <table style={styles.table}>
          <thead>
            <tr>
              <th style={styles.th}>ID</th>
              <th style={styles.th}>Họ tên</th>
              <th style={styles.th}>Tài khoản</th>
              <th style={styles.th}>Email</th>
              <th style={styles.th}>SĐT</th>
              <th style={styles.th}>Hành động</th>
            </tr>
          </thead>
          <tbody>
            {nguoiDungs.map((nd, idx) => (
              <tr
                key={nd.id}
                style={idx % 2 === 0 ? styles.trEven : styles.trOdd}
                onMouseEnter={e => (e.currentTarget.style.backgroundColor = '#e8f0fe')}
                onMouseLeave={e =>
                  (e.currentTarget.style.backgroundColor = idx % 2 === 0 ? styles.trEven.backgroundColor : styles.trOdd.backgroundColor)
                }
              >
                <td style={styles.td}>{nd.id}</td>
                <td style={styles.td}>{nd.hoTen}</td>
                <td style={styles.td}>{nd.taiKhoan}</td>
                <td style={styles.td}>{nd.email}</td>
                <td style={styles.td}>{nd.sdt}</td>
                <td style={styles.td}>
                  <button
                    onClick={() => navigate(`/nguoidung/${nd.id}`)}
                    style={styles.detailButton}
                  >
                    Xem chi tiết
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
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
    fontSize: 20,
    marginBottom: 20,
    color: '#333',
  },
  searchBar: {
    display: 'flex',
    gap: 10,
    marginBottom: 20,
  },
  searchInput: {
    flex: 1,
    padding: 10,
    border: '1px solid #ccc',
    borderRadius: 4,
  },
  searchButton: {
    padding: '10px 20px',
    backgroundColor: '#4caf50',
    color: 'white',
    border: 'none',
    borderRadius: 4,
    cursor: 'pointer',
  },
  tableWrapper: {
    overflowX: 'auto',
    boxShadow: '0 0 10px rgba(0,0,0,0.1)',
  },
  table: {
    borderCollapse: 'collapse',
    width: '100%',
  },
  th: {
    backgroundColor: '#4caf50',
    color: 'white',
    padding: 12,
    border: '1px solid #ddd',
    textAlign: 'center',
  },
  td: {
    border: '1px solid #ddd',
    padding: 10,
    textAlign: 'center',
  },
  trEven: {
    backgroundColor: '#fff',
  },
  trOdd: {
    backgroundColor: '#f9f9f9',
  },
  detailButton: {
    padding: '6px 12px',
    backgroundColor: '#007bff',
    color: 'white',
    border: 'none',
    borderRadius: 4,
    cursor: 'pointer',
  },
};

export default QuanlyNguoidung;
