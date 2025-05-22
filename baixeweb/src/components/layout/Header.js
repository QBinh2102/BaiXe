import { useContext } from "react";
import { Button, Container, Image, Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";
import { MyDispatchContext, MyUserContext } from "../../configs/Contexts";

const Header = () => {
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    return (
        <Navbar expand="lg" className="bg-body-tertiary">
            <Container>
                <Navbar.Brand>Bãi xe</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                    <Link to="/" className="nav-link">Trang chủ</Link>
                    
                    {user === null ? <>
                        <Link to="/dangnhap" className="nav-link text-success">Đăng nhập</Link>
                        <Link to="/dangky" className="nav-link text-danger">Đăng ký</Link>
                    </>:<>
                        <Link to="/baidos" className="nav-link">Bãi xe</Link>
                        <Link to='/thongtin' className="nav-link text-success">
                            <Image src={user.avatar} className="rounded" width="40"/>
                            Chào {user.taiKhoan}
                        </Link>
                        <Button onClick={() => dispatch({"type": "logout"})} variant="danger">Đăng xuất</Button>
                    </>}
                    
                </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Header;