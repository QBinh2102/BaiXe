/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.services.impl;

import com.bxtm.pojo.Danhgia;
import com.bxtm.repositories.DanhgiaRepository;
import com.bxtm.services.DanhgiaService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class DanhgiaServiceImpl implements DanhgiaService{
    @Autowired
    private DanhgiaRepository danhGiaRepo;

    @Override
    public List<Danhgia> getDanhGia(Map<String, String> params) {
        return this.danhGiaRepo.getDanhGia(params);
    }

    @Override
    public Danhgia getDanhGiaById(int id) {
        return this.danhGiaRepo.getDanhGiaById(id);
    }

    @Override
    public Danhgia createOrUpdate(Danhgia danhGia) {
        return this.danhGiaRepo.createOrUpdate(danhGia);
    }

    @Override
    public void deleteDanhGia(int id) {
        this.danhGiaRepo.deleteDanhGia(id);
    }
    
}
