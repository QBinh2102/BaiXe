import { BrowserRouter, Route, Routes } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from "./components/layout/Header";
import Footer from "./components/layout/Footer";
import Trangchu from "./components/Trangchu";
import Baido from "./components/Baido";
import Dangnhap from "./components/Dangnhap";
import Dangky from "./components/Dangky";

const App = () => {
  return (
    <BrowserRouter>
      <Header />

        <Routes>
          <Route path="/" element={<Trangchu/>}/>
          <Route path="/dangnhap" element={<Dangnhap/>}/>
          <Route path="/dangky" element={<Dangky/>}/>
          <Route path="/baidos" element={<Baido/>}/>
        </Routes>

      <Footer />
    </BrowserRouter>
  );
}

export default App;