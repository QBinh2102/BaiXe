/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.hibernate.HibernateUtils;
import com.bxtm.pojo.Chodo;
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
public class ChodoRepositoryImpl {
    public List<Chodo> getChoDo(Map<String,String> params){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Chodo> q = cb.createQuery(Chodo.class);
            Root root = q.from(Chodo.class);
            q.select(root);
            
            if(params != null){
                List<Predicate> predicates = new ArrayList<>();
                
                String baiDo_id = params.get("idBaiDo");
                if(baiDo_id !=null && !baiDo_id.isEmpty())
                    predicates.add(cb.equal(root.get("idBaiDo").get("id").as(String.class), baiDo_id));
                
                String trangThai = params.get("trangThai");
                if(trangThai!=null && !trangThai.isEmpty())
                    predicates.add(cb.equal(root.get("trangThai").as(String.class), trangThai));
                
                q.where(predicates.toArray(Predicate[]::new));
            }
            
            Query query = s.createQuery(q);
            
            return query.getResultList();
        }
    }
    
    public Chodo createOrUpdate(Chodo choDo){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            if(choDo.getId() == null){
                s.persist(choDo);
            }else
                s.merge(choDo);
            
            s.refresh(choDo);
        }
        return choDo;
    }
    
}
