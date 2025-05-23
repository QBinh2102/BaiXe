
import { useEffect, useState } from "react";
import { Alert, Button, Form, Table } from "react-bootstrap";
import { useParams, useNavigate } from "react-router-dom";
import Apis, { endpoints } from "../configs/Apis";
import MySpinner from "./layout/MySpinner";

const Chinhsuachodo = () => {
  const { id } = useParams(); 
  const [choDos, setChoDos] = useState([]);
  const [baiDo, setBaiDo] = useState({});
  const [loading, setLoading] = useState(false);
  const [msg, setMsg] = useState();
  const nav = useNavigate();

  useEffect(() => {
    const loadData = async () => {
      try {
        setLoading(true);

     
        let baiDoRes = await Apis.get(`${endpoints["baidos"]}/${id}`);
        setBaiDo(baiDoRes.data);

       
        let choDoRes = await Apis.get(`${endpoints["chodos"]}?baiDoId=${id}`);
        setChoDos(choDoRes.data);
      } catch (err) {
        console.error(err);
        setMsg("Không thể tải dữ liệu.");
      } finally {
        setLoading(false);
      }
    };

    loadData();
  }, [id]);

  const handleChange = (index, field, value) => {
    const updated = [...choDos];
    updated[index][field] = value;
    setChoDos(updated);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      const payload = choDos.map(({ id, viTri, trangThai }) => ({
        id,
        viTri,
        trangThai,
      }));

    
      await Apis.put(`${endpoints["chodos"]}/update/${id}`, payload);

      nav("/baidos");
    } catch (err) {
      console.error(err);
      setMsg("Đã xảy ra lỗi khi lưu!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <h2 className="text-center text-info mt-3">
        CHỈNH SỬA CHỖ ĐỖ - {baiDo.ten}
      </h2>

      {msg && <Alert variant="danger">{msg}</Alert>}

      {loading ? (
        <MySpinner />
      ) : (
        <Form onSubmit={handleSubmit}>
          <Table striped bordered hover className="mt-3">
            <thead>
              <tr>
                <th>#</th>
                <th>Vị trí</th>
                <th>Trạng thái</th>
              </tr>
            </thead>
            <tbody>
              {choDos.map((c, idx) => (
                <tr key={c.id}>
                  <td>{idx + 1}</td>
                  <td>
                    <Form.Control
                      type="text"
                      value={c.viTri}
                      onChange={(e) => handleChange(idx, "viTri", e.target.value)}
                    />
                  </td>
                  <td>
                    <Form.Select
                      value={c.trangThai}
                      onChange={(e) =>
                        handleChange(idx, "trangThai", e.target.value)
                      }
                    >
                      <option value="Bình thường">Bình thường</option>
                      <option value="Bảo trì">Bảo trì</option>
                    </Form.Select>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>

          <div className="text-center">
            <Button type="submit" variant="success">
              Lưu thay đổi
            </Button>
          </div>
        </Form>
      )}
    </>
  );
};

export default Chinhsuachodo;
