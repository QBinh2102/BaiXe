import axios from "axios";
import cookie from 'react-cookies';

const BASE_URL = "http://localhost:8080/SpringBaiXeThongMinh/api";

export const endpoints = {
    'baidos': '/baidos/',
    'baido': (id) => `/baidos/${id}/`,
    'chodos': '/chodos/',
    'searchChoDo': (idBaiDo) => `/baidos/${idBaiDo}/search/`,
    'baotris': '/baotris/',
    'bookings': '/secure/bookings/',
    'bookingNguoiDung': '/secure/me/bookings/',
    'hoadons': '/hoadons/',
    'dangky': '/nguoidungs/',
    'dangnhap': '/login',
    'current-user': '/secure/profile',
    'capnhat': `/secure/me/nguoidungs/`,
    'thongtinnguoidung': (idNguoiDung) => `/nguoidungs/${idNguoiDung}`,
    'danhgias': (idBaiDo) => `/secure/me/baidos/${idBaiDo}/danhgias/`,
    'loadDanhGia': (idBaiDo) => `/baidos/${idBaiDo}/danhgias/`,
    'hoadonByBookingId': (idBooking) => `/secure/me/hoadons/?idBooking=${idBooking}`,
    
}

export const authApis = () => {
  return axios.create({
    baseURL: BASE_URL,
    headers: {
      'Authorization': `Bearer ${cookie.load('token')}`
    }
  });
};

export default axios.create ({
    baseURL: BASE_URL
})