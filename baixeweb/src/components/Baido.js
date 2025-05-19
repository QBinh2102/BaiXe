import { useEffect, useState } from "react";
import { Alert, Button, Card, Col, Form, Row } from "react-bootstrap";
import Apis, {endpoints} from "../configs/Apis";
import { useNavigate, useSearchParams } from "react-router-dom";
import MySpinner from "./layout/MySpinner";

const Baido = () => {
    const [baidos, setBaiDos] = useState([]);
    const [loading, setLoading] = useState(true);
    const [page, setPage] = useState(1);
    const [q] = useSearchParams();
    const [kw, setKw] = useState();
    const nav = useNavigate();

    const loadBaiDos = async () => {
        try {
            setLoading(true);

            let url = `${endpoints['baidos']}?page=${page}`;

            let tenBaiDo = q.get('tenBai');
            if(tenBaiDo){
                url = `${url}&tenBai=${tenBaiDo}`;
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
        nav(`/baidos/?tenBai=${kw}`)
    }

    const loadMore = () => {
        if (!loading && page > 0)
            setPage(page + 1);
    }

    return (
        <>
            <h1 style={{textAlign:"center", margin:"20px 0"}}>Danh sách bãi đỗ</h1>
            <Form onSubmit={search} className="d-flex justify-content-end">
                <Row className="align-items-center">
                    <Col xs="auto">
                    <Form.Control
                        type="search"
                        value ={kw}
                        onChange={e => setKw(e.target.value)}
                        placeholder="Tìm tên bãi"
                        className="me-2"
                    />
                    </Col>
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
                                <Button variant="primary">Xem chi tiết</Button>
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