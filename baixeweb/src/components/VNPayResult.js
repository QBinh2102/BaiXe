import { useEffect, useRef, useState } from "react";
import { useLocation, Link, Form } from "react-router-dom";
import Apis, { authApis } from "../configs/Apis";

function VNPayResult() {
  const location = useLocation();
  const [status, setStatus] = useState("loading");
  const [message, setMessage] = useState("");
  const [transactionId, setTransactionId] = useState("");

  // useRef để đánh dấu đã xử lý
  const hasHandledRef = useRef(false);

  useEffect(() => {
    if (hasHandledRef.current) return; // nếu đã xử lý thì bỏ qua

    hasHandledRef.current = true;

    const queryParams = new URLSearchParams(location.search);
    const responseCode = queryParams.get("vnp_ResponseCode");
    const txnId = queryParams.get("vnp_TransactionNo");
    const bookingId = queryParams.get("vnp_TxnRef");

    setTransactionId(txnId);

    const updateBookingStatus = async (status) => {
      try {
        console.info(bookingId);
        console.info(status);
        const form = new FormData();
        form.append("idBooking", bookingId);
        form.append("trangThai", status);
        await authApis().patch("http://localhost:8080/SpringBaiXeThongMinh/api/secure/bookings/update-status/", form, {
          headers: {
              'Content-Type': 'multipart/form-data'
          }
        });
      } catch (err) {
        console.error("Lỗi cập nhật trạng thái booking:", err);
      }
    };

    const createInvoice = async () => {
      try {
        const idNguoiDungLocal = localStorage.getItem("idNguoiDung");
        const res = await Apis.post("http://localhost:8080/SpringBaiXeThongMinh/api/hoadons/", {
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            idBooking: bookingId,
            idNguoiDung: idNguoiDungLocal,
            phuongThuc: "VNPAY",
            thoiGianThanhToan: new Date().toISOString(),
            trangThai: "thanh_cong",
            maGD: txnId
          })
        });
        console.info(res.data);
      } catch (err) {
        console.error("Lỗi khi tạo hóa đơn:", err);
      }
    };

    const handlePaymentResult = async () => {
      if (responseCode === "00") {
        setStatus("success");
        setMessage("✅ Thanh toán thành công!");
        await updateBookingStatus("da_dat");
        await createInvoice();
      } else {
        const friendlyMsg = getVNPayErrorMessage(responseCode);
        setStatus("fail");
        setMessage(`❌ Thanh toán thất bại: ${friendlyMsg}`);
        await updateBookingStatus("da_huy");
      }
    };

    handlePaymentResult();
  }, [location]);

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        {status === "loading" && <p>Đang xử lý kết quả thanh toán...</p>}
        {status === "success" && (
          <>
            <h2 style={{ color: "green" }}>{message}</h2>
            <p>Mã giao dịch: <strong>{transactionId}</strong></p>
          </>
        )}
        {status === "fail" && (
          <>
            <h2 style={{ color: "red" }}>{message}</h2>
          </>
        )}
        <Link to="/" style={styles.button}>Quay lại trang chủ</Link>
      </div>
    </div>
  );
}

function getVNPayErrorMessage(code) {
  const messages = {
    "24": "Giao dịch bị hủy bởi người dùng.",
    "07": "Giao dịch bị nghi ngờ gian lận.",
    "09": "Chưa đăng ký Internet Banking.",
    "10": "Xác thực thất bại.",
    "11": "Sai thông tin xác thực.",
    "12": "Tài khoản bị khóa hoặc không đủ tiền.",
    "13": "Hết thời gian thanh toán.",
    "51": "Không đủ số dư.",
    "65": "Vượt hạn mức giao dịch.",
    "99": "Lỗi không xác định.",
  };
  return messages[code] || "Không xác định được nguyên nhân.";
}

const styles = {
  container: {
    display: "flex", justifyContent: "center", alignItems: "center",
    height: "100vh", backgroundColor: "#f0f2f5",
  },
  card: {
    padding: "2rem", borderRadius: "1rem",
    backgroundColor: "#fff", boxShadow: "0 0 10px rgba(0,0,0,0.1)",
    textAlign: "center", minWidth: "320px",
  },
  button: {
    display: "inline-block", marginTop: "1rem",
    backgroundColor: "#007bff", color: "#fff",
    padding: "0.5rem 1rem", borderRadius: "0.5rem",
    textDecoration: "none",
  },
};

export default VNPayResult;
