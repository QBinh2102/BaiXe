/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bxtm.repositories;

import com.bxtm.pojo.Hoadon;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface HoadonRepository {
    List<Hoadon> getHoaDon(Map<String,String> params);
    Hoadon createOrUpdate(Hoadon hoaDon);
}
