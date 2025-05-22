import { useContext, useEffect, useRef, useState } from "react";
import { Alert, Button, Col, Form, Row } from "react-bootstrap";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "./layout/MySpinner";
import { useNavigate } from "react-router-dom";
import { MyUserContext } from "../configs/Contexts";

const Dangky = () => {
    const info = [{
        label: "Tài khoản",
        field: "taiKhoan",
        type: "text"
    }, {
        label: "Mật khẩu",
        field: "matKhau",
        type: "password"
    }, {
        label: "Xác nhận mật khẩu",
        field: "confirm",
        type: "password"
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

    const [user, setUser] = useState({});
    const [msg, setMsg] = useState();
    const [loading, setLoading] = useState(false);
    const nav = useNavigate();

    const validate = () => {
        if (!user.matKhau || user.matKhau !== user.confirm) {
            setMsg("Mật khẩu không khớp");
            return false;
        }
            
        return true;
    }

    useEffect(() => {
        if (current_user !== null) {
            setUser(current_user);
        }
    }, [current_user]);

    const dangKy = async (e) => {
        e.preventDefault();
        if (validate()) {
            let form = new FormData();
            for (let key in user)
                if (key !== 'confirm') {
                    form.append(key, user[key]);
                }
            
            const hasAnhXe = anhXe.current?.files[0] !== undefined;
            const hasAvatar = avatar.current?.files[0] !== undefined;

            if (hasAvatar) {
                form.append('avatar', avatar.current.files[0]);
            }
            if (hasAnhXe) {
                form.append('anhXe', anhXe.current.files[0]);
            }

            // for (let pair of form.entries()) {
            //     console.info(`${pair[0]}:`, pair[1]);
            // }

            if(current_user===null) {
                try {
                    setLoading(true);
                    let res = await Apis.post(endpoints['dangky'], form, {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    });

                    if (res.status === 201)
                        nav('/dangnhap');
                } catch (ex) {
                    console.error(ex);
                } finally{
                    setLoading(false);
                }
            } else {
                if (hasAnhXe && hasAvatar) {
                    try {
                        setLoading(true);
                        let res = await Apis.patch(endpoints['capnhat'](current_user.id), form, {
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        });
                    } catch (ex) {
                        console.error(ex);
                    } finally{
                        setLoading(false);
                    }
                } else if (hasAnhXe && !hasAvatar) {
                    try {
                        setLoading(true);
                        let res = await Apis.patch(endpoints['capnhatanhxe'](current_user.id), form, {
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        });
                    } catch (ex) {
                        console.error(ex);
                    } finally{
                        setLoading(false);
                    }
                } else if (!hasAnhXe && hasAvatar) {
                    try {
                        setLoading(true);
                        let res = await Apis.patch(endpoints['capnhatavatar'](current_user.id), form, {
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        });
                    } catch (ex) {
                        console.error(ex);
                    } finally{
                        setLoading(false);
                    }
                } else {
                    try {
                        setLoading(true);
                        let res = await Apis.patch(endpoints['capnhatkhonganh'](current_user.id), form, {
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        });
                    } catch (ex) {
                        console.error(ex);
                    } finally{
                        setLoading(false);
                    }
                }
            }
        }
    }

    return (
        <>
            {current_user === null ? <>
                <h1 className="text-center text-success mt-2">Đăng ký người dùng</h1>
            </>:<>
                <h1 className="text-center text-success mt-2">Thông tin người dùng</h1>
            </>}
            
            {msg && <Alert variant="warning" className="mt-1">{msg}</Alert>}
            <Form onSubmit={dangKy}>
                {info.map(i => <Form.Group className="mb-3">
                    <Form.Control value={user[i.field]} onChange={e => setUser({...user, [i.field]: e.target.value})} type={i.type} placeholder={i.label} required />
                </Form.Group>)}

                <Form.Group as={Row} className="mb-3">
                    <Form.Label column sm="1">Ảnh xe</Form.Label>
                    <Col sm="11">
                        {current_user ? <>
                            <div className="mb-2">
                                <img
                                src={current_user.anhXe}
                                alt="Ảnh xe"
                                style={{ maxHeight: '150px', borderRadius: '8px' }}
                                />
                            </div>
                            <Form.Control ref={anhXe} type="file" />
                        </>:<>
                            <Form.Control ref={anhXe} type="file" required />
                        </> 
                        }
                        
                    </Col>
                </Form.Group>

                <Form.Group as={Row} className="mb-3">
                    <Form.Label column sm="1">Avatar</Form.Label>
                    <Col sm="11">
                        {current_user ? <>
                            <div className="mb-2">
                                <img
                                src={current_user.avatar}
                                alt="Avatar"
                                style={{ maxHeight: '150px', borderRadius: '8px' }}
                                />
                            </div>
                            <Form.Control ref={avatar} type="file" />
                        </>:<>
                            <Form.Control ref={avatar} type="file" required />
                        </> 
                        }
                    </Col>
                </Form.Group>

                <Form.Group className="mb-3 text-center">
                    {current_user ? <>
                        {loading === true?<MySpinner/>:<Button type="submit" >Cập nhật</Button>}
                    </>:<>
                        {loading === true?<MySpinner/>:<Button type="submit" variant="danger" >Đăng ký</Button>}
                    </>}
                    
                </Form.Group>

            </Form>
        </>
    );
}

export default Dangky;