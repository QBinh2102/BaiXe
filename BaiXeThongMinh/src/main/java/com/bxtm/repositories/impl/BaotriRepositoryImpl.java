/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.hibernate.HibernateUtils;
import com.bxtm.pojo.Baotri;
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
 * @author toquocbinh2102
 */
public class BaotriRepositoryImpl {
    public List<Baotri> getBaoTri(Map<String,String> params){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Baotri> q = cb.createQuery(Baotri.class);
            Root root = q.from(Baotri.class);
            q.select(root);
            
            if(params!=null){
                List<Predicate> predicates = new ArrayList<>();
                
                String tinhTrang = params.get("tinhTrang");
                if(tinhTrang!=null && !tinhTrang.isEmpty())
                    predicates.add(cb.equal(root.get("tinhTrang").as(String.class), tinhTrang));
                
                q.where(predicates.toArray(Predicate[]::new));
            }
            Query query = s.createQuery(q);
            return query.getResultList();
        }
    }
    
    public Baotri createOrUpdate(Baotri baoTri){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            if(baoTri.getId() == null){
                s.persist(baoTri);
            }else
                s.merge(baoTri);
            
            s.refresh(baoTri);
        }
        return baoTri;
    }
}
