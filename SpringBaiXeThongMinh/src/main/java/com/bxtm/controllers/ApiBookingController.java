/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Baido;
import com.bxtm.pojo.Booking;
import com.bxtm.pojo.Chodo;
import com.bxtm.pojo.Nguoidung;
import com.bxtm.services.BaidoService;
import com.bxtm.services.BookingService;
import com.bxtm.services.ChodoService;
import com.bxtm.services.NguoidungService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiBookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private BaidoService baiDoService;
    @Autowired
    private ChodoService choDoService;
    @Autowired
    private NguoidungService nguoiDungService;

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getBookings(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.bookingService.getBookings(params), HttpStatus.OK);
    }

    @GetMapping("/bookings/{idBooking}")
    public ResponseEntity<Booking> getBookingById(@PathVariable(value = "idBooking") int id) {
        return new ResponseEntity<>(this.bookingService.getBookingById(id), HttpStatus.OK);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> create(@RequestParam Map<String, String> params) {
        Baido baiDo = this.baiDoService.getBaiDoById(Integer.parseInt(params.get("idBaiDo")));
        Chodo choDo = this.choDoService.getChoDoById(Integer.parseInt(params.get("idChoDo")));
        Nguoidung nguoiDung = this.nguoiDungService.getNguoiDungById(Integer.parseInt(params.get("idNguoiDung")));
        Booking newBooking = new Booking();
        newBooking.setIdBaiDo(baiDo);
        newBooking.setIdChoDo(choDo);
        newBooking.setIdNguoiDung(nguoiDung);
        newBooking.setThanhTien(BigDecimal.valueOf(Double.parseDouble(params.get("thanhTien"))));
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newBooking.setThoiGianDat(formatter.parse(params.get("thoiGianDat")));
            newBooking.setThoiGianBatDau(formatter.parse(params.get("thoiGianBatDau")));
            newBooking.setThoiGianKetThuc(formatter.parse(params.get("thoiGianKetThuc")));
        } catch (ParseException e) {
            // Xử lý lỗi
        }
        newBooking.setTrangThai("Đang đặt");
        return new ResponseEntity<>(this.bookingService.createOrUpdate(newBooking), HttpStatus.CREATED);
    }

    @PostMapping("/bookings/update-status")
    public ResponseEntity<?> updateBookingStatus(@RequestParam Map<String, String> params) {
        try {
            int idBooking = Integer.parseInt(params.get("idBooking"));
            String newStatus = params.get("trangThai");

            Booking booking = this.bookingService.getBookingById(idBooking);
            if (booking == null) {
                return new ResponseEntity<>("Không tìm thấy booking", HttpStatus.NOT_FOUND);
            }

            booking.setTrangThai(newStatus);
            this.bookingService.createOrUpdate(booking);
            return new ResponseEntity<>("Cập nhật trạng thái thành công", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Lỗi cập nhật trạng thái", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bookings/user/{idNguoiDung}")
    public List<Booking> getBookingsByUserId(@PathVariable("idNguoiDung") int idNguoiDung) {
        return this.bookingService.getBookingByUserID(idNguoiDung);
    }
}
