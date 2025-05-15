/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.formatters;

import com.bxtm.pojo.Booking;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author toquocbinh2102
 */
public class BookingFormatter implements Formatter<Booking>{

    @Override
    public String print(Booking booking, Locale locale) {
        return String.valueOf(booking.getId());
    }

    @Override
    public Booking parse(String idBooking, Locale locale) throws ParseException {
        Booking booking = new Booking();
        booking.setId(Integer.valueOf(idBooking));
        return booking;
    }
    
}
