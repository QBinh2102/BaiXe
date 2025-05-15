import { Container, Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";

const Header = () => {
    return (
        <Navbar expand="lg" className="bg-body-tertiary">
            <Container>
                <Navbar.Brand>Bãi xe</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                    <Link to="/" className="nav-link">Trang chủ</Link>
                    <Link to="/baidos" className="nav-link">Bãi xe</Link>
                    <Link to="/dangnhap" className="nav-link text-success">Đăng nhập</Link>
                    <Link to="/dangky" className="nav-link text-danger">Đăng ký</Link>
                </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Header;