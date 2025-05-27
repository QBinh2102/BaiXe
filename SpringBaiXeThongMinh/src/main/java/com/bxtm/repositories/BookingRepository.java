/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bxtm.repositories;

import com.bxtm.pojo.Booking;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface BookingRepository {
    List<Booking> getBookings(Map<String, String> params);
    Booking getBookingById(int id);
    Booking createOrUpdate(Booking booking);
    List<Booking> getBookingByUserID(int idNguoiDung);
}
