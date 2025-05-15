import axios from "axios";

const BASE_URL = "http://localhost:8080/SpringBaiXeThongMinh/api";

export const endpooints = {
    'baidos': '/baidos',
    'chodos': '/chodos',
    'baotris': '/baotris',
    'bookings': '/bookings',
    'hoadons': '/hoadons',
    'dangky': '/nguoidungs',
    'dangnhap': '/login'
}

export default axios.create ({
    baseURL: BASE_URL
})