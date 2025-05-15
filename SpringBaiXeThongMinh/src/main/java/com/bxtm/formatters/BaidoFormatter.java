/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.formatters;

import com.bxtm.pojo.Baido;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author toquocbinh2102
 */
public class BaidoFormatter implements Formatter<Baido>{

    @Override
    public String print(Baido bd, Locale locale) {
        return String.valueOf(bd.getId());
    }

    @Override
    public Baido parse(String idBaiDo, Locale locale) throws ParseException {
        Baido bd = new Baido();
        bd.setId(Integer.valueOf(idBaiDo));
        return bd;
    }
}
