import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Apis, { endpoints } from "../configs/Apis";
import { Badge, Button, Card, Col, Image, ListGroup, Row } from "react-bootstrap";
import { CarFront, CheckCircle, CurrencyDollar, GeoAlt } from "react-bootstrap-icons";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import MySpinner from "./layout/MySpinner";

const ChiTietBaiDo = () => {
    const {idBaiDo} = useParams();
    const [baiDo, setBaiDo] = useState([]);
    const [loading, setLoading] = useState(false);
    const [startTime, setStartTime] = useState(new Date());
    const [endTime, setEndTime] = useState(new Date());
    const [choDos, setChoDo] = useState([]);
    const nav = useNavigate();

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
            let res = await Apis.get(`http://localhost:8080/SpringBaiXeThongMinh/api/baidos/${idBaiDo}/search`, { params });
            
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
        LoadChoDo();
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
                <Row className="mt-4">
                    <Col>
                        <h4 className="text-primary">Chỗ đỗ khả dụng</h4>
                        <ListGroup>
                            {choDos.map(choDo => (
                                <ListGroup.Item key={choDo.id}>{choDo.viTri}</ListGroup.Item>
                            ))}
                        </ListGroup>
                    </Col>
                </Row>
            )}
        </>
    );
}

export default ChiTietBaiDo;