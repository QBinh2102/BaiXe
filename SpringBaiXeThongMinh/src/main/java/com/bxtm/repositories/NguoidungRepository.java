/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bxtm.repositories;

import com.bxtm.pojo.Nguoidung;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface NguoidungRepository {
    List<Nguoidung> getNguoiDung(Map<String,String> params);
    Nguoidung getNguoiDungById(int idNguoiDung);
    Nguoidung getNguoiDungByTaiKhoan(String taiKhoan);
    Nguoidung createOrUpdate(Nguoidung nguoiDung);
    boolean authenticate(String taiKhoan, String matKhau);
}
