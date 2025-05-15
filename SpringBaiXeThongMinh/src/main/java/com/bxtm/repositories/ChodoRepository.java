/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bxtm.repositories;

import com.bxtm.pojo.Chodo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toquocbinh2102
 */
public interface ChodoRepository {
    List<Chodo> getChoDo(Map<String,String> params);
    Chodo getChoDoById(int id);
    Chodo createOrUpdate(Chodo choDo);
    void deleteChoDo(int id);
}
