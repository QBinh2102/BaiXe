/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bxtm.services;

import com.bxtm.pojo.Hoadon;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface HoadonService {
    List<Hoadon> getHoaDon(Map<String,String> params);
    Hoadon createOrUpdate(Hoadon hoaDon);
    Hoadon getHoaDonById(int id);
    Hoadon getHoaDonByBookingId(int idBooking);
}
