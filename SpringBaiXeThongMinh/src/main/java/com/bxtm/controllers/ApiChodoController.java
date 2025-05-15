/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Chodo;
import com.bxtm.services.ChodoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
public class ApiChodoController {
    @Autowired
    private ChodoService choDoService;
    
    @GetMapping("/baidos/{idBaiDo}/chodos")
    public ResponseEntity<List<Chodo>> getChoDoByBaiDo(@PathVariable(value="idBaiDo") int id){
        Map<String,String> params = new HashMap<>();
        params.put("idBaiDo", String.valueOf(id));
        return new ResponseEntity<>(this.choDoService.getChoDo(params), HttpStatus.OK);
    }
}
