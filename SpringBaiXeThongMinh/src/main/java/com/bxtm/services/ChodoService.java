/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bxtm.services;

import com.bxtm.pojo.Chodo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface ChodoService {
    List<Chodo> getChoDo(Map<String,String> params);
    Chodo createOrUpdate(Chodo choDo);
    List<Chodo> getChoDoTrong(Map<String,String> params, LocalDateTime startTime, LocalDateTime endTime);
    public Chodo getChoDoById(int id);
}
