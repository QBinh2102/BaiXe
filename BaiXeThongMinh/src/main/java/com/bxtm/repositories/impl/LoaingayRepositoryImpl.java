/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.hibernate.HibernateUtils;
import com.bxtm.pojo.Loaingay;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author toquocbinh2102
 */
public class LoaingayRepositoryImpl {
    public List<Loaingay> getAllLoaiNgay(){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            Query query = s.createQuery("FROM LoaiNgay", Loaingay.class);
            return query.getResultList();
        }
    }
    
    public Loaingay createOrUpdate(Loaingay loaiNgay){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            if(loaiNgay.getId() == null){
                s.persist(loaiNgay);
            }else
                s.merge(loaiNgay);
            s.refresh(loaiNgay);
        }
        return loaiNgay;
    }
}
