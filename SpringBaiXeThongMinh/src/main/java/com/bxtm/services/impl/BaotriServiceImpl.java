/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.services.impl;

import com.bxtm.pojo.Baotri;
import com.bxtm.repositories.BaotriRepository;
import com.bxtm.services.BaotriService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class BaotriServiceImpl implements BaotriService{
    @Autowired
    private BaotriRepository baoTriRepo;

    @Override
    public List<Baotri> getBaoTri(Map<String, String> params) {
        return this.baoTriRepo.getBaoTri(params);
    }
    
    @Override
    public Baotri getBaoTriById(int id) {
        return this.baoTriRepo.getBaoTriById(id);
    }
    

    @Override
    public Baotri createOrUpdate(Baotri baoTri) {
        return this.baoTriRepo.createOrUpdate(baoTri);
    }
}
