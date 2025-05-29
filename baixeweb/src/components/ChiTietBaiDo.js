import { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Apis, { authApis, endpoints } from "../configs/Apis";
import { Badge, Button, Card, Col, Image, ListGroup, Row, Table, Modal, Form, Alert } from "react-bootstrap";
import { CarFront, CheckCircle, CurrencyDollar, GeoAlt, Star, StarFill, PencilSquare, Trash } from "react-bootstrap-icons";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import MySpinner from "./layout/MySpinner";
import { MyUserContext } from "../configs/Contexts";

const ChiTietBaiDo = () => {
    const {idBaiDo} = useParams();
    const [baiDo, setBaiDo] = useState([]);
    const [loading, setLoading] = useState(false);
    const [startTime, setStartTime] = useState(new Date());
    const [endTime, setEndTime] = useState(new Date());
    const [choDos, setChoDo] = useState([]);
    const [danhGias, setDanhGias] = useState([]);
    const [userDanhGia, setUserDanhGia] = useState(null);
    const current_user = useContext(MyUserContext);
    const nav = useNavigate();
    const [showDatChoModal, setShowDatChoModal] = useState(false);
    const [currentChoDo, setCurrentChoDo] = useState(null);
    
    // States cho đánh giá
    const [showDanhGiaModal, setShowDanhGiaModal] = useState(false);
    const [rating, setRating] = useState(0);
    const [binhLuan, setBinhLuan] = useState("");
    const [isEditing, setIsEditing] = useState(false);
    const [danhGiaMessage, setDanhGiaMessage] = useState("");

    const handleOpenDatChoModal = (choDo) => {
        setCurrentChoDo(choDo);
        setShowDatChoModal(true);
    };

    const handleCloseDatChoModal = () => {
        setShowDatChoModal(false);
    };

    const LoadThongTin = async () => {
        try {
            setLoading(true);
            let res = await Apis.get(endpoints['baido'](idBaiDo));
            setBaiDo(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    const LoadDanhGia = async () => {
        try {
            let res = await Apis.get(endpoints['loadDanhGia'](idBaiDo));
            setDanhGias(res.data);
            console.info(res.data);
            

            // Tim danh gia user hien tai
            if (current_user) {
                const userReview = res.data.find(dg => dg.idNguoiDung?.id === current_user.id);
                console.info(userReview);
                setUserDanhGia(userReview || null);
            }


        } catch (ex) {
            console.error("Lỗi khi tải đánh giá:", ex);
        }
    }

    const LoadChoDo = async () => {
        try {
            const startTimeVN = new Date(startTime.getTime() + 7 * 60 * 60 * 1000);
            const startTimeFormatted = startTimeVN.toISOString().slice(0, 16);
            const endTimeVN = new Date(endTime.getTime() + 7 * 60 * 60 * 1000);
            const endTimeFormatted = endTimeVN.toISOString().slice(0, 16);

            const params = {
                startTime: startTimeFormatted,
                endTime: endTimeFormatted,
            };
            
            let res = await Apis.get(endpoints['searchChoDo'](baiDo.id), { params });
            setChoDo(res.data);
        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        LoadThongTin();
    }, [idBaiDo])

    useEffect(() => {
        if (current_user) {
            LoadDanhGia();
        }
    }, [idBaiDo, current_user])

    const timChoDo = () => {
        if (endTime <= startTime) {
            alert("Thời gian kết thúc phải lớn hơn thời gian bắt đầu");
            return;
        }
        LoadChoDo();
    }

    const tinhThanhTien = () => {
        const diffTime = endTime.getTime() - startTime.getTime();
        const diffHour = Math.ceil(diffTime / (1000 * 60 * 60));
        return diffHour * baiDo.giaTien;
    }

    function formatDateToCustomString(date) {
        const pad = (n) => n.toString().padStart(2, '0');
        const yyyy = date.getFullYear();
        const mm = pad(date.getMonth() + 1);
        const dd = pad(date.getDate());
        const hh = pad(date.getHours());
        const mi = pad(date.getMinutes());
        const ss = pad(date.getSeconds());
        return `${yyyy}-${mm}-${dd} ${hh}:${mi}:${ss}`;
    }

    // Hàm sắp xếp đánh giá - đánh giá của user hiện tại lên đầu
    const getSortedDanhGias = () => {
        if (!current_user) return danhGias;
        
        const sortedDanhGias = [...danhGias].sort((a, b) => {
            // Nếu a là đánh giá của user hiện tại, đưa lên đầu
            if (a.idNguoiDung?.id === current_user.id) return -1;
            // Nếu b là đánh giá của user hiện tại, đưa lên đầu
            if (b.idNguoiDung?.id === current_user.id) return 1;
            // Các đánh giá khác sắp xếp theo thời gian mới nhất
            return new Date(b.thoiGianDanhGia) - new Date(a.thoiGianDanhGia);
        });
        
        return sortedDanhGias;
    };

    // Hàm format thời gian đầy đủ
    const formatFullDateTime = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleString('vi-VN', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit',
            hour12: false
        });
    };

    // Hàm xử lý đánh giá
    const handleOpenDanhGiaModal = (isEdit = false) => {
        if (isEdit && userDanhGia) {
            setRating(userDanhGia.rating);
            setBinhLuan(userDanhGia.binhLuan);
            setIsEditing(true);
        } else {
            setRating(0);
            setBinhLuan("");
            setIsEditing(false);
        }
        setShowDanhGiaModal(true);
        setDanhGiaMessage("");
    };

    const handleCloseDanhGiaModal = () => {
        setShowDanhGiaModal(false);
        setRating(0);
        setBinhLuan("");
        setIsEditing(false);
        setDanhGiaMessage("");
    };

    const submitDanhGia = async () => {
        if (rating === 0) {
            setDanhGiaMessage("Vui lòng chọn số sao đánh giá!");
            return;
        }

        try {
            
            setLoading(true);
            const now = new Date();
            const form = new FormData();
            form.append('rating', rating);
            form.append('binhLuan', binhLuan);
            form.append('idNguoiDung', current_user.id);
            form.append('thoiGianDanhGia',  formatDateToCustomString(now));

            let res;
            if (isEditing && userDanhGia) {
                // Sua hoac xoa danh gia
                form.append('idDanhGia', userDanhGia.id);
         
                res = await authApis().patch(endpoints['danhgias'](idBaiDo), form, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });
            } else {
                // Tạo đánh giá mới
                res = await authApis().post(endpoints['danhgias'](idBaiDo), form, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });
            }

            setDanhGiaMessage("Đánh giá thành công!");
            setTimeout(() => {
                handleCloseDanhGiaModal();
                LoadDanhGia();
            }, 1500);

        } catch (ex) {
            console.error(ex);
            setDanhGiaMessage("Có lỗi xảy ra khi đánh giá!");
        } finally {
            setLoading(false);
        }
    };

   const xoaDanhGia = async () => {
        if (!userDanhGia) return;
        
        if (window.confirm("Bạn có chắc chắn muốn xóa đánh giá này!?")) {
            try {
                setLoading(true);
                
                
              
             await authApis().delete(`/secure/me/danhgias/${userDanhGia.id}/`);
                
                setUserDanhGia(null);
                LoadDanhGia();
                alert("Xóa đánh giá thành công!");
                
            } catch (ex) {
                console.error("Lỗi khi xóa đánh giá:", ex);
                
                // Hiển thị thông báo lỗi chi tiết hơn
                if (ex.response && ex.response.data && ex.response.data.error) {
                    alert(ex.response.data.error);
                } else {
                    alert("Có lỗi xảy ra khi xóa đánh giá!");
                }
            } finally {
                setLoading(false);
            }
        }
    };

    const renderStars = (rating, size = 16) => {
        const stars = [];
        for (let i = 1; i <= 5; i++) {
            stars.push(
                i <= rating ? 
                <StarFill key={i} size={size} className="text-warning" /> : 
                <Star key={i} size={size} className="text-muted" />
            );
        }
        return stars;
    };

    const renderRatingInput = () => {
        return (
            <div className="d-flex gap-1 mb-3">
                {[1, 2, 3, 4, 5].map(star => (
                    <Button
                        key={star}
                        variant="link"
                        className="p-0 border-0"
                        onClick={() => setRating(star)}
                    >
                        {star <= rating ? 
                            <StarFill size={24} className="text-warning" /> : 
                            <Star size={24} className="text-muted" />
                        }
                    </Button>
                ))}
                <span className="ms-2 align-self-center">({rating}/5)</span>
            </div>
        );
    };

    const thanhToanVNPAY = async (idChoDo) => {
        try {
            setLoading(true);
            const now = new Date();
            const thanhTien = tinhThanhTien();

            const form = new FormData();
            form.append('idBaiDo', baiDo.id);
            form.append('idChoDo', idChoDo);
            form.append('idNguoiDung', current_user.id);
            form.append('thanhTien', thanhTien);
            form.append('thoiGianBatDau', formatDateToCustomString(startTime));
            form.append('thoiGianKetThuc', formatDateToCustomString(endTime));

            const bookingRes = await authApis().post(endpoints['bookings'], form, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });

            const bookingId = bookingRes.data.id;
            const idNguoiDung = current_user.id;

            //console.info(bookingId);

            localStorage.setItem("idNguoiDung", idNguoiDung); 
            // localStorage.setItem("idBooking", bookingId);
            // console.info(bookingId);

            const res = await Apis.get(
                "http://localhost:8080/SpringBaiXeThongMinh/api/create-payment", {
                params: {
                    bookingId: bookingId,
                    amount: thanhTien,
                    bankCode: "NCB"
                }
            });

            if (res.data && res.data.paymentUrl) {
                window.location.href = res.data.paymentUrl;
            } else {
                alert("Không lấy được link thanh toán!");
            }

        } catch (ex) {
            console.error(ex);
            alert("Lỗi thanh toán!");
        } finally {
            setLoading(false);
        }
    }

    return (
        <>
            {loading && <MySpinner/>}
            <Row className="mb-4">
                <Col>
                    <h1 className="text-center text-primary fw-bold">{baiDo.ten}</h1>
                    <div className="text-center">
                        <Badge bg="info">
                            <CheckCircle className="me-1" /> {baiDo.trangThai}
                        </Badge>
                    </div>
                </Col>
            </Row>
            
            <Row>
                <Col md={8}>
                    <Image 
                        src={baiDo.anhBai} 
                        alt={baiDo.ten} 
                        fluid 
                        rounded 
                        className="shadow mb-3"
                        style={{ maxHeight: "400px", objectFit: "cover", width: "100%" }}
                    />
                </Col>
                
                <Col md={4}>
                    <Card className="shadow">
                        <Card.Body>
                            <h4 className="text-primary">Thông tin cơ bản</h4>
                            <ListGroup variant="flush">
                                <ListGroup.Item>
                                    <GeoAlt className="text-danger me-2" />
                                    <strong>Địa chỉ:</strong> {baiDo.diaChi}
                                </ListGroup.Item>
                                <ListGroup.Item>
                                    <CurrencyDollar className="text-success me-2" />
                                    <strong>Giá tiền:</strong> {baiDo.giaTien?.toLocaleString()} VNĐ/giờ
                                </ListGroup.Item>
                                <ListGroup.Item>
                                    <CarFront className="text-warning me-2" />
                                    <strong>Số lượng chỗ đỗ:</strong> {baiDo.soLuong} chỗ
                                </ListGroup.Item>
                                <ListGroup.Item>
                                    {baiDo.tienIch && (
                                        <>
                                            <h4 className="text-primary">Tiện ích</h4>
                                            <div className="d-flex flex-wrap">
                                                {baiDo.tienIch.split(',').map((tienIch, index) => (
                                                    <Badge key={index} bg="light" text="dark" className="me-2 mb-2 p-2">
                                                        {tienIch.trim()}
                                                    </Badge>
                                                ))}
                                            </div>
                                        </>
                                    )}
                                </ListGroup.Item>
                            </ListGroup>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>

            {/* Phần tìm kiếm chỗ đỗ */}
            <div className="mt-3 shadow rounded p-3 d-flex align-items-center gap-3">
                <strong>Thời gian:</strong>
                <div className="d-flex gap-2">
                    <div className="d-flex flex-column">
                        <label htmlFor="startTime" className="form-label small">Bắt đầu</label>
                        <DatePicker
                            id="startTime"
                            selected={startTime}
                            onChange={(date) => setStartTime(date)}
                            showTimeSelect
                            timeIntervals={15}
                            timeCaption="Giờ"
                            dateFormat="dd/MM/yyyy HH:mm"
                            className="form-control form-control-sm"
                        />
                    </div>
                    <div className="d-flex flex-column">
                        <label htmlFor="endTime" className="form-label small">Kết thúc</label>
                        <DatePicker
                            id="endTime"
                            selected={endTime}
                            onChange={(date) => setEndTime(date)}
                            showTimeSelect
                            timeIntervals={15}
                            timeCaption="Giờ"
                            dateFormat="dd/MM/yyyy HH:mm"
                            className="form-control form-control-sm"
                        />
                    </div>
                </div>
                <Button onClick={timChoDo}>Tìm kiếm</Button>
            </div>

            {/* Danh sách chỗ đỗ */}
            {choDos.length > 0 && (
                <div className="mt-4">
                    <h4 className="text-primary mb-3">Chỗ đỗ khả dụng</h4>
                    <Table bordered hover>
                        <thead>
                            <tr>
                                <th className="text-center w-50">Vị trí</th>
                                <th className="text-center w-50">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            {choDos.map(choDo => (
                                <tr key={choDo.id}>
                                    <td className="text-center align-middle">{choDo.viTri}</td>
                                    <td className="text-center">
                                        {current_user && (
                                            <Button 
                                                variant="primary" 
                                                size="sm" 
                                                onClick={() => handleOpenDatChoModal(choDo)}
                                            >
                                                Đặt chỗ
                                            </Button>
                                        )}
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </div>
            )}

            {/* Phần đánh giá */}
            <div className="mt-4">
                <div className="d-flex justify-content-between align-items-center mb-3">
                    <h4 className="text-primary">Đánh giá & Nhận xét</h4>
                    {current_user && (
                        <div>
                            {!userDanhGia ? (
                                <Button 
                                    variant="primary" 
                                    size="sm"
                                    onClick={() => handleOpenDanhGiaModal(false)}
                                >
                                    Viết đánh giá
                                </Button>
                            ) : (
                                <div className="d-flex gap-2">
                                    <Button 
                                        variant="outline-primary" 
                                        size="sm"
                                        onClick={() => handleOpenDanhGiaModal(true)}
                                    >
                                        <PencilSquare className="me-1" />
                                        Sửa
                                    </Button>
                                    <Button 
                                        variant="outline-danger" 
                                        size="sm"
                                        onClick={xoaDanhGia}
                                    >
                                        <Trash className="me-1" />
                                        Xóa
                                    </Button>
                                </div>
                            )}
                        </div>
                    )}
                </div>

                {danhGias.length > 0 ? (
                    <div className="d-flex flex-column gap-3">
                        {getSortedDanhGias().map(danhGia => (
                            <Card key={danhGia.id} className="w-100">
                                <Card.Body>
                                    <div className="d-flex justify-content-between align-items-start mb-2">
                                        <div>
                                            <h6 className="mb-1">{danhGia.idNguoiDung?.hoTen || "Người dùng"}</h6>
                                            <div className="d-flex align-items-center">
                                                {renderStars(danhGia.rating)}
                                                <small className="text-muted ms-2">
                                                    {formatFullDateTime(danhGia.thoiGianDanhGia)}
                                                </small>
                                            </div>
                                        </div>
                                        {userDanhGia && userDanhGia.id === danhGia.id && (
                                            <Badge bg="success">Đánh giá của bạn</Badge>
                                        )}
                                    </div>
                                    <p className="mb-0">{danhGia.binhLuan}</p>
                                </Card.Body>
                            </Card>
                        ))}
                    </div>
                ) : (
                    <Card>
                        <Card.Body className="text-center text-muted">
                            <p>Chưa có đánh giá nào cho bãi đỗ xe này.</p>
                            {current_user && (
                                <Button 
                                    variant="outline-primary"
                                    onClick={() => handleOpenDanhGiaModal(false)}
                                >
                                    Hãy là người đầu tiên đánh giá!
                                </Button>
                            )}
                        </Card.Body>
                    </Card>
                )}
            </div>

            {/* Modal đặt chỗ */}
            <Modal show={showDatChoModal} onHide={handleCloseDatChoModal}>
                <Modal.Header closeButton>
                    <Modal.Title>Đặt chỗ {currentChoDo?.viTri}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3">
                            <Form.Label>Họ tên</Form.Label>
                            <Form.Control
                                type="text"
                                value={current_user?.hoTen || ""}
                                readOnly
                            />
                        </Form.Group>
                        
                        <Form.Group className="mb-3">
                            <Form.Label>Số điện thoại</Form.Label>
                            <Form.Control
                                type="tel"
                                value={current_user?.sdt || ""}
                                readOnly
                            />
                        </Form.Group>
                        
                        <Form.Group className="mb-3">
                            <Form.Label>Biển số xe</Form.Label>
                            <Form.Control
                                type="text"
                                value={current_user?.bienSo || ""}
                                readOnly
                            />
                        </Form.Group>
                        
                        <div className="mb-3">
                            <strong>Thời gian:</strong>
                            <div>
                                Từ: {startTime.toLocaleString('vi-VN')} - 
                                Đến: {endTime.toLocaleString('vi-VN')}
                            </div>
                        </div>
                        <div className="mb-3">
                            <strong>Thành tiền:</strong>
                            <div className="text-success fw-bold">
                                {tinhThanhTien().toLocaleString('vi-VN')} VNĐ
                            </div>
                        </div>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseDatChoModal}>
                        Hủy
                    </Button>
                    <Button variant="primary" onClick={() => thanhToanVNPAY(currentChoDo.id)}>
                        Xác nhận đặt chỗ
                    </Button>
                </Modal.Footer>
            </Modal>

            {/* Modal đánh giá */}
            <Modal show={showDanhGiaModal} onHide={handleCloseDanhGiaModal}>
                <Modal.Header closeButton>
                    <Modal.Title>
                        {isEditing ? "Chỉnh sửa đánh giá" : "Viết đánh giá"}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {danhGiaMessage && (
                        <Alert variant={danhGiaMessage.includes("thành công") ? "success" : "danger"}>
                            {danhGiaMessage}
                        </Alert>
                    )}
                    
                    <Form>
                        <Form.Group className="mb-3">
                            <Form.Label>Đánh giá sao</Form.Label>
                            {renderRatingInput()}
                        </Form.Group>
                        
                        <Form.Group className="mb-3">
                            <Form.Label>Nhận xét</Form.Label>
                            <Form.Control
                                as="textarea"
                                rows={4}
                                value={binhLuan}
                                onChange={(e) => setBinhLuan(e.target.value)}
                                placeholder="Chia sẻ trải nghiệm của bạn về bãi đỗ xe này..."
                            />
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleCloseDanhGiaModal}>
                        Hủy
                    </Button>
                    <Button variant="primary" onClick={submitDanhGia} disabled={loading}>
                        {isEditing ? "Cập nhật" : "Gửi đánh giá"}
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default ChiTietBaiDo;