/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bxtm.services;

import com.bxtm.pojo.Danhgia;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface DanhgiaService {
    List<Danhgia> getDanhGia(Map<String,String> params);
    Danhgia getDanhGiaById(int id);
    Danhgia createOrUpdate(Danhgia danhGia);
    void deleteDanhGia(int id);
}
