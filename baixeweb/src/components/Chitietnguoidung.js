import React, { useEffect, useState,useContext } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import Apis, { authApis, endpoints } from "../configs/Apis";
import { Navigate } from 'react-router-dom';
import { MyUserContext } from '../configs/Contexts';
const ChitietNguoiDung = () => {

    
  const { id } = useParams();
  const navigate = useNavigate();
    const user = useContext(MyUserContext);

  const [nguoiDung, setNguoiDung] = useState({
    hoTen: "",
    taiKhoan: "",
    matKhau: "",
    email: "",
    sdt: "",
    cccd: "",
    hieuXe: "",
    bienSo: "",
    mauXe: "",
    vaiTro: "ROLE_USER",
    active: true,
    avatar: null,
    anhXe: null,
  });

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [successMsg, setSuccessMsg] = useState(null);

  useEffect(() => {
   authApis().get(endpoints['thongtinnguoidung'](id))
      .then((res) => {
        setNguoiDung(res.data);
        setLoading(false);
      })
      .catch(() => {
        setError("Không thể tải thông tin người dùng.");
        setLoading(false);
      });
      // set o mat khau trong
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNguoiDung((prev) => ({
      ...prev,
      [name]: value,
    }));
  };
 const handleSubmit = (e) => {
    e.preventDefault();
    let form = new FormData();
     for (let key in nguoiDung)
        form.append(key, nguoiDung[key]);
                    

    console.log("Dữ liệu chuẩn bị gửi lên:");
        for (let pair of form.entries()) {
            console.log(`${pair[0]}: ${pair[1]}`);
        }

    authApis()
      .patch(endpoints.capnhatkhonganh(id), form, {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    })
      .then(() => {
        setSuccessMsg("Cập nhật thông tin thành công.");
        setError(null);
      
        // setNguoiDung((prev) => ({ ...prev, matKhau: "" }));
      })
      .catch((err) => {
        console.error("Lỗi cập nhật:", err);

        if (err.response) {
            console.error("Status code:", err.response.status);
            console.error("Response data:", err.response.data);
            console.error("Headers:", err.response.headers);
        } else if (err.request) {
            console.error("Không nhận được phản hồi từ server:", err.request);
        } else {
            console.error("Lỗi khi thiết lập yêu cầu:", err.message);
        }

        setError("Cập nhật thất bại.");
        setSuccessMsg(null);
        });
  };

  if (loading) return <div>Đang tải...</div>;
  if (error) return <div style={{ color: "red" }}>{error}</div>;
  if (!user || user.vaiTro !== 'ROLE_ADMIN') {
      return <Navigate to="/trangchu" replace />;
    }
  return (
    <>
      <div className="container">
        <h2>Chi tiết người dùng #{id}</h2>
        {successMsg && <div className="success-msg">{successMsg}</div>}

        <form onSubmit={handleSubmit} className="form">
          <div className="form-group">
            <label>Họ tên:</label>
            <input
              type="text"
              name="hoTen"
              value={nguoiDung.hoTen || ""}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Tài khoản:</label>
            <input
              type="text"
              name="taiKhoan"
              value={nguoiDung.taiKhoan || ""}
              onChange={handleChange}
              required
              disabled
              className="input-disabled"
            />
          </div>

          <div className="form-group">
            <label>Mật khẩu (nhập mật khẩu mới nếu muốn đổi):</label>
            <input
              type="password"
              name="matKhau"
              value={nguoiDung.matKhau || ""}
              onChange={handleChange}
              placeholder="Để trống nếu không đổi"
            />
          </div>

          <div className="form-group">
            <label>Email:</label>
            <input
              type="email"
              name="email"
              value={nguoiDung.email || ""}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Số điện thoại:</label>
            <input
              type="text"
              name="sdt"
              value={nguoiDung.sdt || ""}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>CCCD:</label>
            <input
              type="text"
              name="cccd"
              value={nguoiDung.cccd || ""}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Hiệu xe:</label>
            <input
              type="text"
              name="hieuXe"
              value={nguoiDung.hieuXe || ""}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Biển số:</label>
            <input
              type="text"
              name="bienSo"
              value={nguoiDung.bienSo || ""}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Màu xe:</label>
            <input
              type="text"
              name="mauXe"
              value={nguoiDung.mauXe || ""}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Vai trò:</label>
            <select
              name="vaiTro"
              value={nguoiDung.vaiTro || "ROLE_USER"}
              onChange={handleChange}
            >
              <option value="ROLE_USER">ROLE_USER</option>
              <option value="ROLE_ADMIN">ROLE_ADMIN</option>
            </select>
          </div>

          <div className="form-group checkbox-group">
            <label>Trạng thái hoạt động:</label>
            <input
              type="checkbox"
              checked={nguoiDung.active}
              onChange={() =>
                setNguoiDung((prev) => ({
                  ...prev,
                  active: !prev.active,
                }))
              }
            />
          </div>

          <div className="form-group buttons">
            <button type="submit" className="btn btn-submit">
              Cập nhật
            </button>
            <button
              type="button"
              onClick={() => navigate(-1)}
              className="btn btn-cancel"
            >
              Quay lại
            </button>
          </div>
        </form>

        <div className="images">
          <div>
            <h3>Ảnh đại diện:</h3>
            {nguoiDung.avatar ? (
              <img
                src={nguoiDung.avatar}
                alt="Avatar"
                className="image-preview"
              />
            ) : (
              <p>Chưa có ảnh đại diện</p>
            )}
          </div>
          <div>
            <h3>Ảnh xe:</h3>
            {nguoiDung.anhXe ? (
              <img
                src={nguoiDung.anhXe}
                alt="Ảnh xe"
                className="image-preview"
              />
            ) : (
              <p>Chưa có ảnh xe</p>
            )}
          </div>
        </div>
      </div>

      <style>{`
        .container {
          max-width: 600px;
          margin: 30px auto;
          padding: 20px 25px;
          background: #f9f9f9;
          border-radius: 10px;
          box-shadow: 0 6px 12px rgba(0,0,0,0.1);
          font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        h2 {
          text-align: center;
          margin-bottom: 25px;
          color: #333;
        }
        .success-msg {
          background-color: #d4edda;
          color: #155724;
          padding: 10px 15px;
          border-radius: 5px;
          margin-bottom: 20px;
          text-align: center;
          font-weight: 600;
        }
        .form {
          display: flex;
          flex-direction: column;
          gap: 15px;
        }
        .form-group {
          display: flex;
          flex-direction: column;
        }
        label {
          margin-bottom: 6px;
          font-weight: 600;
          color: #555;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"],
        select {
          padding: 10px 12px;
          font-size: 16px;
          border: 1px solid #ccc;
          border-radius: 6px;
          box-sizing: border-box;
          transition: border-color 0.3s ease;
        }
        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="password"]:focus,
        select:focus {
          border-color: #3498db;
          outline: none;
          background-color: #fff;
          color: #333;
        }
        .input-disabled {
          background-color: #e9ecef;
          cursor: not-allowed;
          color: #6c757d;
        }
        .checkbox-group {
          flex-direction: row;
          align-items: center;
          gap: 10px;
          margin-top: 10px;
        }
        .buttons {
          display: flex;
          gap: 15px;
          margin-top: 15px;
          justify-content: flex-start;
        }
        .btn {
          padding: 10px 20px;
          font-size: 16px;
          border-radius: 6px;
          border: none;
          cursor: pointer;
          transition: background-color 0.3s ease;
        }
        .btn-submit {
          background-color: #3498db;
          color: white;
        }
        .btn-submit:hover {
          background-color: #2980b9;
        }
        .btn-cancel {
          background-color: #ccc;
          color: #333;
        }
        .btn-cancel:hover {
          background-color: #b3b3b3;
        }
        .images {
          margin-top: 30px;
          display: flex;
          justify-content: space-between;
          gap: 20px;
          flex-wrap: wrap;
        }
        .images > div {
          flex: 1 1 45%;
          text-align: center;
        }
        .image-preview {
          max-width: 100%;
          max-height: 200px;
          border-radius: 8px;
          box-shadow: 0 3px 8px rgba(0,0,0,0.15);
          object-fit: contain;
        }
      `}</style>
    </>
  );
};

export default ChitietNguoiDung;
