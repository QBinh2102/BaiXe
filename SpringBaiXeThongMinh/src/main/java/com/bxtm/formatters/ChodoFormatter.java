/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.formatters;

import com.bxtm.pojo.Chodo;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author toquocbinh2102
 */
public class ChodoFormatter implements Formatter<Chodo>{
    @Override
    public String print(Chodo cd, Locale locale) {
        return String.valueOf(cd.getId());
    }

    @Override
    public Chodo parse(String idChoDo, Locale locale) throws ParseException {
        Chodo cd = new Chodo();
        cd.setId(Integer.valueOf(idChoDo));
        return cd;
    }
}
