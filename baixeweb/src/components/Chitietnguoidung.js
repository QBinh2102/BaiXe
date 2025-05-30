import { useContext, useEffect, useRef, useState } from "react";
import { Alert, Button, Col, Form, Row, Card, Image } from "react-bootstrap";
import Apis, { endpoints, authApis } from "../configs/Apis";
import MySpinner from "./layout/MySpinner";
import { useNavigate, useParams } from "react-router-dom";
import { MyUserContext } from "../configs/Contexts";

const Chitietnguoidung = () => {
    const info = [{
        label: "Tài khoản",
        field: "taiKhoan",
        type: "text"
    }, {
        label: "Họ Tên",
        field: "hoTen",
        type: "text"
    }, {
        label: "Email",
        field: "email",
        type: "email"
    }, {
        label: "Số điện thoại",
        field: "sdt",
        type: "tel"
    }, {
        label: "Căn cước công dân",
        field: "cccd",
        type: "text"
    }, {
        label: "Hiệu xe",
        field: "hieuXe",
        type: "text"
    }, {
        label: "Biển số",
        field: "bienSo",
        type: "text"
    }, {
        label: "Màu xe",
        field: "mauXe",
        type: "text"
    }];

    const avatar = useRef();
    const anhXe = useRef();
    const current_user = useContext(MyUserContext);
    const { userId } = useParams();
    const nav = useNavigate();

    const [user, setUser] = useState({});
    const [originalUser, setOriginalUser] = useState({});
    const [msg, setMsg] = useState();
    const [loading, setLoading] = useState(false);
    const [isEditing, setIsEditing] = useState(false);
    const [avatarPreview, setAvatarPreview] = useState(null);
    const [anhXePreview, setAnhXePreview] = useState(null);

    useEffect(() => {
        const loadUserInfo = async () => {
            try {
                setLoading(true);
                let res = await authApis().get(endpoints['current-user']);
                
                if (res.status === 200) {
                    setUser(res.data);
                    setOriginalUser(res.data);
                    setAvatarPreview(res.data.avatar);
                    setAnhXePreview(res.data.anhXe);
                }
            } catch (ex) {
                console.error(ex);
                setMsg("Không thể tải thông tin người dùng");
            } finally {
                setLoading(false);
            }
        };

        loadUserInfo();
    }, [userId]);

    const handleImageChange = (ref, setPreview) => {
        const file = ref.current.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = (e) => setPreview(e.target.result);
            reader.readAsDataURL(file);
        }
    };

    const handleEdit = () => {
        setIsEditing(true);
        setMsg(null);
    };

    const handleCancel = () => {
        setIsEditing(false);
        setUser(originalUser);
        setAvatarPreview(originalUser.avatar);
        setAnhXePreview(originalUser.anhXe);
        setMsg(null);
    };

    const handleSave = async (e) => {
        e.preventDefault();
        
        try {
            setLoading(true);
            let form = new FormData();
            
            // Chỉ thêm các field cần cập nhật (loại bỏ avatar, anhXe, taiKhoan)
            for (let key in user) {
                if (key !== 'avatar' && key !== 'anhXe' && key !== 'taiKhoan') {
                    form.append(key, user[key]);
                }
            }

            if (avatar.current && avatar.current.files[0]) {
                form.append('avatar', avatar.current.files[0]);
            }

            if (anhXe.current && anhXe.current.files[0]) {
                form.append('anhXe', anhXe.current.files[0]);
            }

            let res = await authApis().patch(endpoints['capnhat'], form, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });

            if (res.status === 200) {
                setOriginalUser(res.data);
                setUser(res.data);
                setIsEditing(false);
                setMsg("Cập nhật thông tin thành công!");
            }
        } catch (ex) {
            console.error(ex);
            setMsg("Có lỗi xảy ra khi cập nhật thông tin!");
        } finally {
            setLoading(false);
        }
    };

    if (loading && !user.taiKhoan) {
        return <MySpinner />;
    }

    return (
        <>
            <Row className="justify-content-center mt-4">
                <Col md={8}>
                    <Card>
                        <Card.Header>
                            <h2 className="text-center text-success mb-0">
                                {isEditing ? "Chỉnh sửa thông tin" : "Chi tiết thông tin người dùng"}
                            </h2>
                        </Card.Header>
                        <Card.Body>
                            {msg && (
                                <Alert 
                                    variant={msg.includes("thành công") ? "success" : "warning"} 
                                    className="mb-3"
                                >
                                    {msg}
                                </Alert>
                            )}

                            <Form onSubmit={handleSave}>
                                <Row className="mb-4">
                                    <Col md={6}>
                                        <Card>
                                            <Card.Header>
                                                <h5>Avatar</h5>
                                            </Card.Header>
                                            <Card.Body className="text-center">
                                                {avatarPreview && (
                                                    <Image 
                                                        src={avatarPreview} 
                                                        alt="Avatar" 
                                                        width={150} 
                                                        height={150} 
                                                        className="mb-3"
                                                        style={{ objectFit: 'cover' }}
                                                        rounded
                                                    />
                                                )}
                                                {isEditing && (
                                                    <Form.Control 
                                                        ref={avatar} 
                                                        type="file" 
                                                        accept="image/*"
                                                        onChange={() => handleImageChange(avatar, setAvatarPreview)}
                                                    />
                                                )}
                                            </Card.Body>
                                        </Card>
                                    </Col>
                                    
                                    <Col md={6}>
                                        <Card>
                                            <Card.Header>
                                                <h5>Ảnh xe</h5>
                                            </Card.Header>
                                            <Card.Body className="text-center">
                                                {anhXePreview && (
                                                    <Image 
                                                        src={anhXePreview} 
                                                        alt="Ảnh xe" 
                                                        width={150} 
                                                        height={150} 
                                                        className="mb-3"
                                                        style={{ objectFit: 'cover' }}
                                                        rounded
                                                    />
                                                )}
                                                {isEditing && (
                                                    <Form.Control 
                                                        ref={anhXe} 
                                                        type="file" 
                                                        accept="image/*"
                                                        onChange={() => handleImageChange(anhXe, setAnhXePreview)}
                                                    />
                                                )}
                                            </Card.Body>
                                        </Card>
                                    </Col>
                                </Row>

                                <Row>
                                    {info.map((i, index) => (
                                        <Col md={6} key={index}>
                                            <Form.Group className="mb-3">
                                                <Form.Label><strong>{i.label}:</strong></Form.Label>
                                                <Form.Control 
                                                    value={user[i.field] || ''} 
                                                    onChange={e => setUser({...user, [i.field]: e.target.value})} 
                                                    type={i.type}
                                                    readOnly={!isEditing || i.field === 'taiKhoan'} 
                                                    className={!isEditing || i.field === 'taiKhoan' ? "bg-light" : ""}
                                                />
                                            </Form.Group>
                                        </Col>
                                    ))}
                                </Row>

                                <div className="text-center mt-4">
                                    {!isEditing ? (
                                        <Button 
                                            variant="primary" 
                                            onClick={handleEdit}
                                            className="me-2"
                                        >
                                            Chỉnh sửa
                                        </Button>
                                    ) : (
                                        <>
                                            {loading ? (
                                                <MySpinner />
                                            ) : (
                                                <>
                                                    <Button 
                                                        type="submit" 
                                                        variant="success" 
                                                        className="me-2"
                                                    >
                                                        Lưu thay đổi
                                                    </Button>
                                                    <Button 
                                                        variant="secondary" 
                                                        onClick={handleCancel}
                                                    >
                                                        Hủy
                                                    </Button>
                                                </>
                                            )}
                                        </>
                                    )}
                                    
                                </div>
                            </Form>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </>
    );
}

export default Chitietnguoidung;
