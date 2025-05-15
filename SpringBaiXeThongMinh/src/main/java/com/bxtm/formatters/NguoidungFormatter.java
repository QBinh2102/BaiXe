/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.formatters;

import com.bxtm.pojo.Nguoidung;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author toquocbinh2102
 */
public class NguoidungFormatter implements Formatter<Nguoidung>{

    @Override
    public String print(Nguoidung nd, Locale locale) {
        return String.valueOf(nd.getId());
    }

    @Override
    public Nguoidung parse(String idNguoiDung, Locale locale) throws ParseException {
        Nguoidung nd = new Nguoidung();
        nd.setId(Integer.valueOf(idNguoiDung));
        return nd;
    }
    
}
