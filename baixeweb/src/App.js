import { useReducer, useEffect } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import Trangchu from "./components/Trangchu";
import Baido from "./components/Baido";
import Dangnhap from "./components/Dangnhap";
import Dangky from "./components/Dangky";
import ChiTietBaiDo from "./components/ChiTietBaiDo";
import Capnhatbaido from "./components/Capnhatbaido"
import Chinhsuachodo from "./components/Chinhsuachodo"
import VNPayResult from "./components/VNPayResult";
import Lichsugiaodich from "./components/Lichsugiaodich";
import Quanlygiaodich from "./components/Quanlygiaodich";
import { Container } from "react-bootstrap";
import { MyDispatchContext, MyUserContext } from "./configs/Contexts";
import MyUserReducer from "./reducers/MyUserReducer";
import { authApis, endpoints } from "./configs/Apis";




const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, null);

  useEffect(() => {
    const checkLogin = async () => {
      const token = localStorage.getItem("token");
      if (token !== null) {
        try {
          let res = await authApis().get(endpoints['current-user']);
          dispatch({
            type: 'login',
            payload: res.data
          });
        } catch (err) {
          console.error("Lỗi kiểm tra đăng nhập:", err);
          localStorage.removeItem("token"); 
        }
      }
    };

    checkLogin();
  }, []);

  return (
    <MyUserContext.Provider value={user}>
      <MyDispatchContext.Provider value={dispatch}>
        <BrowserRouter>
          <Header />

          <Container>
            <Routes>
              <Route path="/" element={<Trangchu/>}/>
              <Route path="/dangnhap" element={<Dangnhap/>}/>
              <Route path="/dangky" element={<Dangky/>}/>
              <Route path="/thongtin" element={<Dangky/>}/>
              <Route path="/baidos" element={<Baido/>}/>
              <Route path="/baidos/:idBaiDo" element={<ChiTietBaiDo/>}/>
              <Route path="/baidos/add" element={<Capnhatbaido/>} />
              <Route path="/baidos/edit/:id" element={<Capnhatbaido />} />
              <Route path="/baidos/chodos/:id" element={<Chinhsuachodo />} />
              <Route path="/vnpay-result" element={<VNPayResult />} />
              <Route path="/lichsugiaodich" element={<Lichsugiaodich />} />
              <Route path="/quanlygiaodich" element={<Quanlygiaodich />} />
               
            </Routes>
          </Container>

          <Footer />
        </BrowserRouter>
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>
  );
}

export default App;