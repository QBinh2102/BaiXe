import { useEffect, useState, useContext } from "react";
import { Alert, Button, Card, Col, Form, Row } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import { useNavigate } from "react-router-dom";
import MySpinner from "./layout/MySpinner";
import { MyUserContext } from "../configs/Contexts";

const Baido = () => {
    const [baidos, setBaiDos] = useState([]);
    const [allBaiDos, setAllBaiDos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [kw, setKw] = useState("");
    const [selectedDistrict, setSelectedDistrict] = useState("");
    const [selectedPriceRange, setSelectedPriceRange] = useState("");
    const nav = useNavigate();
    const user = useContext(MyUserContext);

    const districts = [
        { id: "Quận 1", name: "Quận 1" },
        { id: "Quận 2", name: "Quận 2" },
        { id: "Quận 3", name: "Quận 3" },
        { id: "Quận 4", name: "Quận 4" },
        { id: "Quận 5", name: "Quận 5" },
        { id: "Quận 6", name: "Quận 6" },
        { id: "Quận 7", name: "Quận 7" },
        { id: "Quận 8", name: "Quận 8" },
        { id: "Quận 9", name: "Quận 9" },
        { id: "Quận 10", name: "Quận 10" },
        { id: "Quận 11", name: "Quận 11" },
        { id: "Quận 12", name: "Quận 12" },
        { id: "Bình Thạnh", name: "Bình Thạnh" },
        { id: "Bình Tân", name: "Bình Tân" },
        { id: "Phú Nhuận", name: "Phú Nhuận" },
        { id: "Tân Phú", name: "Tân Phú" },
        { id: "Thủ Đức", name: "Thủ Đức" },
        { id: "Gò Vấp", name: "Gò Vấp" },
        { id: "Hóc Môn", name: "Hóc Môn" },
        { id: "Củ Chi", name: "Củ Chi" },
        { id: "Nhà Bè", name: "Nhà Bè" },
        { id: "Cần Giờ", name: "Cần Giờ" },
        { id: "Tân Bình", name: "Tân Bình" },
    ];

    useEffect(() => {
        const loadAll = async () => {
            try {
                setLoading(true);
                let url = `${endpoints["baidos"]}?trangThai=Hoạt động`;
                let res = await Apis.get(url);
                setAllBaiDos(res.data);
                setBaiDos(res.data);
            } catch (err) {
                console.error(err);
            } finally {
                setLoading(false);
            }
        };

        loadAll();
    }, []);

    const filterBaiDos = () => {
        let filtered = [...allBaiDos];

        if (kw) {
            filtered = filtered.filter(bd =>
                bd.ten.toLowerCase().includes(kw.toLowerCase())
            );
        }

        if (selectedDistrict) {
            filtered = filtered.filter(bd =>
                bd.diaChi.toLowerCase().includes(selectedDistrict.toLowerCase())
            );
        }

        if (selectedPriceRange) {
            switch (selectedPriceRange) {
                case "under10":
                    filtered = filtered.filter(bd => bd.giaTien < 10000);
                    break;
                case "10to20":
                    filtered = filtered.filter(bd => bd.giaTien >= 10000 && bd.giaTien <= 20000);
                    break;
                case "20to50":
                    filtered = filtered.filter(bd => bd.giaTien >= 20000 && bd.giaTien <= 50000);
                    break;
                case "above50":
                    filtered = filtered.filter(bd => bd.giaTien > 50000);
                    break;
                default:
                    break;
            }
        }

        setBaiDos(filtered);
    };

    useEffect(() => {
        filterBaiDos();
    }, [kw, selectedDistrict, selectedPriceRange, allBaiDos]);

    const xemChiTiet = (idBaiDo) => {
        nav(`/baidos/${idBaiDo}`);
    };

    return (
        <>
            <h1 style={{ textAlign: "center", margin: "20px 0" }}>Danh sách bãi đỗ</h1>

            {user?.vaiTro === "ROLE_ADMIN" && (
                <div className="text-end mb-3">
                    <Button variant="success" onClick={() => nav("/baidos/add")}>
                        Thêm bãi đỗ
                    </Button>
                </div>
            )}

            <Form className="d-flex justify-content-end mb-3">
                <Row className="align-items-center">
                    <Col xs="auto">
                        <Form.Control
                            type="search"
                            value={kw}
                            onChange={e => setKw(e.target.value)}
                            placeholder="Tìm tên bãi"
                            className="me-2"
                        />
                    </Col>

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

                    <Col xs="auto">
                        <Form.Select
                            value={selectedPriceRange}
                            onChange={(e) => setSelectedPriceRange(e.target.value)}
                        >
                            <option value="">Tất cả mức giá</option>
                            <option value="under10">Dưới 10.000 VNĐ</option>
                            <option value="10to20">Từ 10.000 - 20.000 VNĐ</option>
                            <option value="20to50">Từ 20.000 - 50.000 VNĐ</option>
                            <option value="above50">Trên 50.000 VNĐ</option>
                        </Form.Select>
                    </Col>
                </Row>
            </Form>

            <div className="container px-4">
                {loading && <MySpinner />}
                {!loading && baidos.length === 0 && (
                    <Alert variant="info" className="mt-2">
                        Không có bãi đỗ nào
                    </Alert>
                )}

                <Row>
                    {baidos.map((bd) => (
                        <Col className="p-2" md={3} xs={6} key={bd.id}>
                            <Card>
                                <Card.Img variant="top" src={bd.anhBai} />
                                <Card.Body>
                                    <Card.Title>{bd.ten}</Card.Title>
                                    <Card.Text>{bd.giaTien.toLocaleString()} VNĐ</Card.Text>
                                     <Card.Text><strong>Địa chỉ:</strong> {bd.diaChi}</Card.Text>

                                    <Button
                                        onClick={() => xemChiTiet(bd.id)}
                                        variant="primary"
                                        className="me-2"
                                    >
                                        Xem chi tiết
                                    </Button>

                                    {user?.vaiTro === "ROLE_ADMIN" && (
                                        <Button
                                            variant="warning"
                                            onClick={() => nav(`/baidos/edit/${bd.id}`)}
                                        >
                                            Sửa
                                        </Button>
                                    )}
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Row>
            </div>
        </>
    );
};

export default Baido;
