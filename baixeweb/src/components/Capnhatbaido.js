import { useRef, useState, useContext, useEffect } from "react";
import { Alert, Button, Col, Form, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import { MyUserContext } from "../configs/Contexts";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "./layout/MySpinner";

const Capnhatbaido = () => {
  const info = [
    { label: "Tên bãi đỗ", field: "ten", type: "text" },
    { label: "Địa chỉ", field: "diaChi", type: "text" },
    { label: "Số lượng", field: "soLuong", type: "number" },
    { label: "Giá tiền", field: "giaTien", type: "number" },
    { label: "Tiện ích", field: "tienIch", type: "text", required: false },
  ];

  const anhBai = useRef();
  const [baiDo, setBaiDo] = useState({});
  const [preview, setPreview] = useState();
  const user = useContext(MyUserContext);
  const [msg, setMsg] = useState();
  const [loading, setLoading] = useState(false);
  const nav = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    const loadBaiDo = async () => {
      try {
        setLoading(true);
        let res = await Apis.get(`${endpoints["baidos"]}/${id}`);
        let data = res.data;
        setBaiDo(data);
        setPreview(data.anhBai);
      } catch (ex) {
        console.error("Lỗi khi tải bãi đỗ:", ex);
      } finally {
        setLoading(false);
      }
    };

    if (id) loadBaiDo();
  }, [id]);

  const validate = () => {
    if (!baiDo.ten || !baiDo.diaChi || !baiDo.soLuong || !baiDo.giaTien) {
      setMsg("Vui lòng điền đầy đủ các trường bắt buộc.");
      return false;
    }
    return true;
  };

  const handleFileChange = (e) => {
    if (e.target.files && e.target.files[0]) {
      const file = e.target.files[0];
      setPreview(URL.createObjectURL(file));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    let form = new FormData();
    for (let key in baiDo) {
      if (key !== "anhBai") {
        form.append(key, baiDo[key]);
      }
    }

    if (anhBai.current?.files[0]) {
      form.append("anhBai", anhBai.current.files[0]);
    }

    // ✅ In dữ liệu form trước khi gửi
    console.log("=== FormData chuẩn bị gửi ===");
    for (let pair of form.entries()) {
      console.log(pair[0] + ": ", pair[1]);
    }

    try {
      setLoading(true);
      let res;
      if (id) {
        res = await Apis.put(`${endpoints["baidos"]}/edit/${id}`, form, {
          headers: { "Content-Type": "multipart/form-data" },
        });
      } else {
        res = await Apis.post(endpoints["baidos"], form, {
          headers: { "Content-Type": "multipart/form-data" },
        });
      }

      if (res.status >= 200 && res.status < 300) {
        nav("/baidos");
      }
    } catch (ex) {
      console.error("Lỗi khi gửi dữ liệu:", ex);
      setMsg("Đã có lỗi xảy ra!");
    } finally {
      setLoading(false);
    }
  };

  if (!user || user.vaiTro !== "ROLE_ADMIN") {
    return (
      <Alert variant="danger" className="mt-2">
        Bạn không có quyền truy cập!
      </Alert>
    );
  }

  return (
    <>
      <h1 className="text-center text-primary mt-3">
        {id ? "Cập nhật bãi đỗ" : "Thêm bãi đỗ mới"}
      </h1>
      {msg && <Alert variant="warning">{msg}</Alert>}

    <Form onSubmit={handleSubmit} encType="multipart/form-data">
      {info.map((i) => (
        <Form.Group as={Row} className="mb-3" key={i.field} controlId={`input-${i.field}`}>
          <Form.Label column sm="2" className="text-end">
            {i.label}
          </Form.Label>
          <Col sm="10">
            <Form.Control
              type={i.type}
              placeholder={i.label}
              required={i.required !== false}
              value={baiDo[i.field] || ""}
              onChange={(e) => {
                // Không cho phép thay đổi số lượng (soLuong)
                if (i.field === "soLuong") return;
                setBaiDo({ ...baiDo, [i.field]: e.target.value });
              }}
              // hoặc dùng readonly/disabled cho soLuong
              {...(i.field === "soLuong" ? { readOnly: true } : {})}
            />
          </Col>
        </Form.Group>
      ))}

      <Form.Group as={Row} className="mb-3" controlId="input-anhBai">
        <Form.Label column sm="2" className="text-end">
          Ảnh bãi đỗ
        </Form.Label>
        <Col sm="10">
          {preview && (
            <div className="mb-2">
              <img
                src={preview}
                alt="Ảnh bãi đỗ"
                className="img-thumbnail"
                style={{ maxWidth: "300px", maxHeight: "200px" }}
              />
            </div>
          )}
          <Form.Control
            ref={anhBai}
            type="file"
            onChange={handleFileChange}
            {...(!id && { required: true })}
          />
        </Col>
      </Form.Group>

      <Form.Group className="mb-3 text-center">
        {loading ? (
          <MySpinner />
        ) : (
          <>
            <Button variant="success" type="submit" className="me-2">
              {id ? "Cập nhật" : "Thêm mới"}
            </Button>
            {id && (
              <Button variant="primary" onClick={() => nav(`/baidos/chodos/${id}`)}>
                Sửa chỗ đỗ
              </Button>
            )}
          </>
        )}
      </Form.Group>
    </Form>



    </>
  );
};

export default Capnhatbaido;
