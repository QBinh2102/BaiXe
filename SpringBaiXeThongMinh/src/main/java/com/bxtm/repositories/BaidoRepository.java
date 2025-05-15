/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bxtm.repositories;

import com.bxtm.pojo.Baido;
import com.bxtm.pojo.Danhgia;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface BaidoRepository {
    List<Baido> getBaiDo(Map<String,String> params);
    Baido getBaiDoById(int id);
    Baido createOrUpdate(Baido baiDo);
}
