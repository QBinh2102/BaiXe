import { useContext, useRef, useState } from "react";
import { Alert, Button, Form } from "react-bootstrap";
import Apis, { authApis, endpoints } from "../configs/Apis";
import MySpinner from "./layout/MySpinner";
import { useNavigate, useSearchParams } from "react-router-dom";
import cookie from 'react-cookies';
import { MyDispatchContext } from "../configs/Contexts";

const Dangnhap = () => {
    const info = [{
        label: "Tài khoản",
        field: "taiKhoan",
        type: "text"
    }, {
        label: "Mật khẩu",
        field: "matKhau",
        type: "password"
    }];

    const [user, setUser] = useState({});
    const [msg, setMsg] = useState();
    const [loading, setLoading] = useState(false);
    const nav = useNavigate();
    const dispatch = useContext(MyDispatchContext);
    const [q] = useSearchParams();

    const dangNhap = async (e) => {
        e.preventDefault();

        try {
            setLoading(true);
            let res = await Apis.post(endpoints['dangnhap'], { ...user });
            // cookie.save('token', res.data.token);
            localStorage.setItem('token', res.data.token);
            //  sessionStorage.setItem('token', res.data.token);

            let u = await authApis().get(endpoints['current-user']);
            console.info(u.data);
            dispatch({
                "type": "login",
                "payload": u.data
            });

            let next = q.get('next');
            if (next)
                nav(next);
            else
                nav('/');
        } catch (ex) {
            console.error(ex);
        } finally{
            setLoading(false);
        }
    }

    return (
        <>
            <h1 className="text-center text-success mt-2">Đăng nhập</h1>
            {msg && <Alert variant="warning" className="mt-1">{msg}</Alert>}
            <Form onSubmit={dangNhap}>
                {info.map(i => <Form.Group className="mb-3">
                    <Form.Control value={user[i.field]} onChange={e => setUser({...user, [i.field]: e.target.value})} type={i.type} placeholder={i.label} required />
                </Form.Group>)}

                <Form.Group className="mb-3 text-center">
                    {loading === true?<MySpinner/>:<Button type="submit" >Đăng nhập</Button>}
                </Form.Group>

            </Form>
        </>
    );
}

export default Dangnhap;