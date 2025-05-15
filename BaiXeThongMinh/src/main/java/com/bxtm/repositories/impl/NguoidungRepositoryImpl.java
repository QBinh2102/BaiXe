/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.hibernate.HibernateUtils;
import com.bxtm.pojo.Nguoidung;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author GIGA
 */
public class NguoidungRepositoryImpl {
    public List<Nguoidung> getNguoiDung(Map<String,String> params){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Nguoidung> q = cb.createQuery(Nguoidung.class);
            Root root = q.from(Nguoidung.class);
            q.select(root);
            
            if(params!=null){
                List<Predicate> predicates = new ArrayList<>();
                
                String email = params.get("email");
                if(email!=null&&!email.isEmpty()){
                    predicates.add(cb.equal(root.get("email"), email));
                }
                
                String cccd = params.get("cccd");
                if(cccd!=null&&!cccd.isEmpty()){
                    predicates.add(cb.equal(root.get("cccd"), cccd));
                }
                
                String bienSo = params.get("bienSo");
                if(bienSo!=null&&!bienSo.isEmpty()){
                    predicates.add(cb.equal(root.get("bienSo"), bienSo));
                }
                
                String sdt = params.get("sdt");
                if(sdt!=null&&!sdt.isEmpty()){
                    predicates.add(cb.equal(root.get("sdt"), sdt));
                }
                
                String active = params.get("active");
                if(active!=null&&!active.isEmpty()){
                    predicates.add(cb.equal(root.get("active"), active));
                }
                
                q.where(predicates.toArray(Predicate[]::new));
            }
            
            Query query = s.createQuery(q);
            
            return query.getResultList();
        }
    }
    
    public Nguoidung createOrUpdate(Nguoidung nguoiDung){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            if(nguoiDung.getId() == null){
                s.persist(nguoiDung);
            }else
                s.merge(nguoiDung);
            
            s.refresh(nguoiDung);
        }
        return nguoiDung;
    }
}
