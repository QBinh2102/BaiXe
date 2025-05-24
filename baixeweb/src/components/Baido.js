import { useEffect, useState, useContext } from "react";
import { Alert, Button, Card, Col, Form, Row } from "react-bootstrap";
import Apis, {endpoints} from "../configs/Apis";
import { useNavigate, useSearchParams } from "react-router-dom";
import MySpinner from "./layout/MySpinner";
import {  MyUserContext } from "../configs/Contexts";

const Baido = () => {
    const [baidos, setBaiDos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [page, setPage] = useState(1);
    const [q] = useSearchParams();
    const [kw, setKw] = useState();
    const nav = useNavigate();
    const user = useContext(MyUserContext);

    const [districts, setDistricts] = useState([
        { id: "Q1", name: "Quận 1" },
        { id: "Q2", name: "Quận 2" },
        { id: "Q3", name: "Quận 3" },
        { id: "Q4", name: "Quận 4" },
        { id: "Q5", name: "Quận 5" },
        { id: "Q6", name: "Quận 6" },
        { id: "Q7", name: "Quận 7" },
        { id: "Q8", name: "Quận 8" },
        { id: "Q9", name: "Quận 9" },
        { id: "Q10", name: "Quận 10" },
        { id: "Q11", name: "Quận 11" },
        { id: "Q12", name: "Quận 12" },
        { id: "Bình Thạnh", name: "Bình Thạnh" },
        { id: "Bình Tân", name: "Bình Tân" },
        { id: "Phú Nhuận", name: "Phú Nhuận" },
        { id: "Tân Phú", name: "Tân Phú" },
        { id: "Thủ Đức", name: "Thủ Đức" }, // (Quận cũ + Quận 2, 9)
        { id: "Gò Vấp", name: "Gò Vấp" },
        { id: "Hóc Môn", name: "Hóc Môn" },
        { id: "Củ Chi", name: "Củ Chi" },
        { id: "Nhà Bè", name: "Nhà Bè" },
        { id: "Cần Giờ", name: "Cần Giờ" },
        { id: "Tân Bình", name: "Tân Bình" },
    ]);
    const [selectedDistrict, setSelectedDistrict] = useState("");

    const loadBaiDos = async () => {
        try {
            setLoading(true);

            let url = `${endpoints['baidos']}?page=${page}`;

            let tenBaiDo = q.get('tenBai');
            let diaChiBaiDo = q.get('diaChi');

            console.info(diaChiBaiDo);
            if(tenBaiDo){
                url = `${url}&tenBai=${tenBaiDo}`;
            }
            if (diaChiBaiDo){
                url = `${url}&diaChi=${diaChiBaiDo}`;
            }

            let res = await Apis.get(url);
            if(res.data.length === 0)
                setPage(0);
            else {
                if (page === 1)
                    setBaiDos(res.data);
                else
                    setBaiDos([...baidos, ...res.data]);
            }

        } catch (ex) {
            console.error(ex);
        } finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        if (page > 0)
            loadBaiDos();
    }, [page, q])

    useEffect(() => {
        setPage(1);
        setBaiDos([]);
    }, [q])

    const search = (e) => {
        e.preventDefault();
        if(kw && selectedDistrict)
            nav(`/baidos/?tenBai=${kw}&diaChi=${selectedDistrict}`)
        else if (kw)
            nav(`/baidos/?tenBai=${kw}`)
        else if (selectedDistrict)
            nav(`/baidos/?diaChi=${selectedDistrict}`)
        else
            nav(`/baidos`)
    }

    const loadMore = () => {
        if (!loading && page > 0)
            setPage(page + 1);
    }
    
     const xemChiTiet = (idBaiDo) => {
        nav(`/baidos/${idBaiDo}`);
    }

    return (
        <>
            <h1 style={{textAlign:"center", margin:"20px 0"}}>Danh sách bãi đỗ</h1>

            {user?.vaiTro === 'ROLE_ADMIN' && (
                <div className="text-end mb-3">
                    <Button variant="success" onClick={() => nav("/baidos/add")}>
                        Thêm bãi đỗ
                    </Button>
                </div>
            )}

            <Form onSubmit={search} className="d-flex justify-content-end">
                <Row className="align-items-center">
                    {/* Ô tìm kiếm tên bãi */}
                    <Col xs="auto">
                        <Form.Control
                            type="search"
                            value={kw}
                            onChange={e => setKw(e.target.value)}
                            placeholder="Tìm tên bãi"
                            className="me-2"
                        />
                    </Col>
                    
                    {/* Combobox chọn quận */}
                    <Col xs="auto">
                        <Form.Select
                            value={selectedDistrict}
                            onChange={(e) => setSelectedDistrict(e.target.value)}
                        >
                            <option value="">Tất cả quận/huyện</option>
                            {districts.map((district) => (
                                <option key={district.id} value={district.id}>
                                    {district.name}
                                </option>
                            ))}
                        </Form.Select>
                    </Col>
                    
                    {/* Nút tìm kiếm */}
                    <Col xs="auto">
                        <Button variant="primary" type="submit">Tìm kiếm</Button>
                    </Col>
                </Row>
            </Form>
            
            <div className="container px-4">
                {loading && <MySpinner />}
                {baidos.length === 0 && <Alert variant="info" className="mt-2">Không có bãi đỗ nào</Alert>}
                <Row>
                    {baidos.map(bd => <Col className="p-2" md={3} xs={6} key={bd.id}>
                        <Card>
                            <Card.Img variant="top" src={bd.anhBai} />
                            <Card.Body>
                                <Card.Title>{bd.ten}</Card.Title>
                                <Card.Text>{bd.giaTien.toLocaleString()} VNĐ</Card.Text>

                                  <Button onClick={() => xemChiTiet(bd.id)} variant="primary" >Xem chi tiết</Button>

                                {user?.vaiTro === 'ROLE_ADMIN' && (
                                    <Button variant="warning" onClick={() => nav(`/baidos/edit/${bd.id}`)}>
                                        Sửa
                                     </Button>

                                )}
                            </Card.Body>
                        </Card>
                    </Col>)}
                </Row>

                {page > 0 && <div className="text-center mb-2 mt-2">
                    <Button variant="info" onClick={loadMore}>Xem thêm</Button>
                </div>}
                
            </div>
        </>
    );
}

export default Baido;