/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.hibernate.HibernateUtils;
import com.bxtm.pojo.Soden;
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
public class SodenRepositoryImpl {
    public List<Soden> getBaiDo(Map<String,String> params){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Soden> q = cb.createQuery(Soden.class);
            Root root = q.from(Soden.class);
            q.select(root);
            
            if(params != null){
                List<Predicate> predicates = new ArrayList<>();
                
                String nguoiDung_id = params.get("idNguoiDung");
                if(nguoiDung_id!=null && !nguoiDung_id.isEmpty())
                    predicates.add(cb.equal(root.get("idNguoiDung").get("id").as(String.class), nguoiDung_id));
                
                q.where(predicates.toArray(Predicate[]::new));
            }
            
            Query query = s.createQuery(q);
            
            return query.getResultList();
        }
    }
    
    public Soden createOrUpdate(Soden soDen){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            if(soDen.getId() == null){
                s.persist(soDen);
            }else
                s.merge(soDen);
            
            s.refresh(soDen);
        }
        return soDen;
    }
}
