import { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Apis, { endpoints } from "../configs/Apis";
import { Badge, Button, Card, Col, Image, ListGroup, Row, Table, Modal, Form } from "react-bootstrap";
import { CarFront, CheckCircle, CurrencyDollar, GeoAlt } from "react-bootstrap-icons";
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
    const current_user = useContext(MyUserContext);
    const nav = useNavigate();
    const [showDatChoModal, setShowDatChoModal] = useState(false);
    const [currentChoDo, setCurrentChoDo] = useState(null);

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
            console.info(baiDo.id);
            console.info(startTimeFormatted);
            console.info(endTimeFormatted);
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
    },[])

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

    // const themBooking = async (idChoDo) => {
    //     try {
    //         setLoading(true);
    //         const now = new Date();
    //         let form = new FormData();
    //         form.append('idBaiDo', baiDo.id);
    //         form.append('idChoDo', idChoDo);
    //         form.append('idNguoiDung', current_user.id);
    //         form.append('thanhTien', tinhThanhTien());
    //         form.append('thoiGianDat', formatDateToCustomString(now));
    //         form.append('thoiGianBatDau', formatDateToCustomString(startTime));
    //         form.append('thoiGianKetThuc', formatDateToCustomString(endTime));

    //         for (let pair of form.entries()) {
    //             console.info(`${pair[0]}:`, pair[1]);
    //         }

    //         let res = await Apis.post(endpoints['bookings'], form, {
    //             headers: {
    //                 'Content-Type': 'multipart/form-data'
    //             }
    //         })

    //     } catch (ex) {
    //         console.error(ex);
    //     } finally {
    //         setLoading(false);
    //     }
    // }

    const thanhToanVNPAY = async (idChoDo) => {
        try {
            setLoading(true);
            const now = new Date();
            const thanhTien = tinhThanhTien();

            // 1. Tạo booking
            const form = new FormData();
            form.append('idBaiDo', baiDo.id);
            form.append('idChoDo', idChoDo);
            form.append('idNguoiDung', current_user.id);
            form.append('thanhTien', thanhTien);
            form.append('thoiGianDat', formatDateToCustomString(now));
            form.append('thoiGianBatDau', formatDateToCustomString(startTime));
            form.append('thoiGianKetThuc', formatDateToCustomString(endTime));

            const bookingRes = await Apis.post(endpoints['bookings'], form, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });

            const bookingId = bookingRes.data.id;

            const res = await Apis.get(`http://localhost:8080/SpringBaiXeThongMinh/api/create-payment?amount=${thanhTien}&bankCode=NCB`);

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
            {loading&&<MySpinner/>}
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
            {/* Hiển thị danh sách các chỗ đỗ tìm được */}
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
                                        {current_user && <Button variant="primary" size="sm" onClick={() => handleOpenDatChoModal(choDo)}>
                                            Đặt chỗ
                                        </Button>}
                                        
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </div>
            )}

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
                                value={current_user.hoTen}
                                readOnly
                            />
                        </Form.Group>
                        
                        <Form.Group className="mb-3">
                            <Form.Label>Số điện thoại</Form.Label>
                            <Form.Control
                                type="tel"
                                value={current_user.sdt}
                                readOnly
                            />
                        </Form.Group>
                        
                        <Form.Group className="mb-3">
                            <Form.Label>Biển số xe</Form.Label>
                            <Form.Control
                                type="text"
                                value={current_user.bienSo}
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
        </>
    );
}

export default ChiTietBaiDo;