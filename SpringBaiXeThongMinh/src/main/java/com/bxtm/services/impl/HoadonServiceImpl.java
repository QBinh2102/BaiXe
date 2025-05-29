/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.services.impl;

import com.bxtm.pojo.Hoadon;
import com.bxtm.repositories.HoadonRepository;
import com.bxtm.services.HoadonService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class HoadonServiceImpl implements HoadonService{
    @Autowired
    private HoadonRepository hoaDonRepo;

    @Override
    public List<Hoadon> getHoaDon(Map<String, String> params) {
        return this.hoaDonRepo.getHoaDon(params);
    }

    @Override
    public Hoadon createOrUpdate(Hoadon hoaDon) {
        return this.hoaDonRepo.createOrUpdate(hoaDon);
    }

    @Override
    public Hoadon getHoaDonById(int id) {
        return this.hoaDonRepo.getHoaDonById(id);
    }
    
}
