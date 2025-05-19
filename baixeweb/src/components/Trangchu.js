import { Container, Row, Col, Card, Alert } from "react-bootstrap";
import { Link } from "react-router-dom";

const Home = () => {
    return (
        <Container className="mt-4">
            <h1 style={{ textAlign: "center", marginBottom: "30px" }}>Trang chủ</h1>

            {/* Giới thiệu */}
            <Alert variant="info">
                Hệ thống <strong>bãi gửi xe thông minh</strong> giúp quản lý xe ra/vào, tính tiền tự động, 
                hỗ trợ đăng nhập và đăng ký dễ dàng cho người dùng và nhân viên.
            </Alert>

            {/* Tính năng nổi bật */}
            <h4 className="mt-4 mb-3">Tính năng nổi bật</h4>
            <Row>
                <Col md={4}>
                    <Card className="mb-3">
                        <Card.Body>
                            <Card.Title>🚗 Quản lý xe ra/vào</Card.Title>
                            <Card.Text>Kiểm soát xe vào, xe ra theo thời gian thực và tự động tính phí.</Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={4}>
                    <Card className="mb-3">
                        <Card.Body>
                            <Card.Title>📊 Thống kê doanh thu</Card.Title>
                            <Card.Text>Theo dõi thu nhập theo ngày, tháng hoặc thời gian tùy chọn.</Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
                <Col md={4}>
                    <Card className="mb-3">
                        <Card.Body>
                            <Card.Title>👤 Quản lý người dùng</Card.Title>
                            <Card.Text>Hỗ trợ đăng ký, đăng nhập và phân quyền cho nhân viên.</Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>

            {/* Hướng dẫn nhanh */}
            <h4 className="mt-4 mb-2">Hướng dẫn sử dụng</h4>
            <ul>
                <li>
                    👉 Nếu bạn là người dùng mới, hãy{" "}
                    <Link to="/dangky" className="text-danger fw-bold">Đăng ký</Link> tài khoản.
                </li>
                <li>
                    👉 Đã có tài khoản? Nhấn{" "}
                    <Link to="/dangnhap" className="text-success fw-bold">Đăng nhập</Link> để sử dụng đầy đủ chức năng.
                </li>
                <li>
                    👉 Là nhân viên?{" "}
                    <Link to="/dangnhap" className="text-success fw-bold">Đăng nhập</Link> để vào hệ thống quản trị.
                </li>

            </ul>

          
        </Container>
    );
};

export default Home;
