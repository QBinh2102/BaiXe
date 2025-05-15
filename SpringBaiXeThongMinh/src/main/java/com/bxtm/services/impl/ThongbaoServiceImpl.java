/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.services.impl;

import com.bxtm.pojo.Thongbao;
import com.bxtm.repositories.ThongbaoRepository;
import com.bxtm.services.ThongbaoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class ThongbaoServiceImpl implements ThongbaoService{
    @Autowired
    private ThongbaoRepository thongBaoRepo;

    @Override
    public List<Thongbao> getThongBao(Map<String, String> params) {
        return this.thongBaoRepo.getThongBao(params);
    }
    
    @Override
    public Thongbao getThongBaoById(int id) {
        return this.thongBaoRepo.getThongBaoById(id);
    }

    @Override
    public Thongbao createOrUpdate(Thongbao thongBao) {
        return this.thongBaoRepo.createOrUpdate(thongBao);
    }
}
