/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.hibernate.HibernateUtils;
import com.bxtm.pojo.Danhgia;
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
public class DanhgiaRepositoryImpl {
    public List<Danhgia> getDanhGia(Map<String,String> params){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Danhgia> q = cb.createQuery(Danhgia.class);
            Root root = q.from(Danhgia.class);
            q.select(root);
            
            if(params!=null){
                List<Predicate> predicates = new ArrayList<>();
                
                String baiDo_id = params.get("idBaiDo");
                if(baiDo_id!=null&&!baiDo_id.isEmpty()){
                    predicates.add(cb.equal(root.get("idBaiDo").get("id"), baiDo_id));
                }
                
                q.where(predicates.toArray(Predicate[]::new));
            }
            
            Query query = s.createQuery(q);
            
            return query.getResultList();
        }
    }
    
    public Danhgia getDanhGiaById(int id){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            return s.get(Danhgia.class, id);
        }
    }
    
    public Danhgia createOrUpdate(Danhgia danhGia) {
        try (Session s = HibernateUtils.getFACTORY().openSession()) {
            if (danhGia.getId() == null)
                s.persist(danhGia);
            else
                s.merge(danhGia);
            
            s.refresh(danhGia);
        }
        
        return danhGia;
    }
    
    public void deleteDanhGia(int id) {
        try (Session s = HibernateUtils.getFACTORY().openSession()) {
            Danhgia danhGia = this.getDanhGiaById(id);
            s.remove(danhGia);
        }
    }
}
