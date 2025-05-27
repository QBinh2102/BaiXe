/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.services.impl;

import com.bxtm.pojo.Booking;
import com.bxtm.repositories.BookingRepository;
import com.bxtm.services.BookingService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookingRepo;

    @Override
    public List<Booking> getBookings(Map<String, String> params) {
        return this.bookingRepo.getBookings(params);
    }

    @Override
    public Booking getBookingById(int id) {
        return this.bookingRepo.getBookingById(id);
    }
    
    @Override
    public Booking createOrUpdate(Booking booking) {
        return this.bookingRepo.createOrUpdate(booking);
    }
    
     @Override
    public List<Booking> getBookingByUserID(int idNguoiDung) {
        return this.bookingRepo.getBookingByUserID(idNguoiDung);
    }
}
