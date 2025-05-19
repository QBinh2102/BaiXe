import axios from "axios";
import cookie from 'react-cookies';

const BASE_URL = "http://localhost:8080/SpringBaiXeThongMinh/api";

export const endpoints = {
    'baidos': '/baidos',
    'chodos': '/chodos',
    'baotris': '/baotris',
    'bookings': '/bookings',
    'hoadons': '/hoadons',
    'dangky': '/nguoidungs',
    'dangnhap': '/login',
    'current-user': '/secure/profile',
}

export const authApis = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': `Bearer ${cookie.load('token')}`
        }
    })
}

export default axios.create ({
    baseURL: BASE_URL
})