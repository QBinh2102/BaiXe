/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.hibernate.HibernateUtils;
import com.bxtm.pojo.Booking;
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
public class BookingRepositoryImpl {
    public List<Booking> getBookings(Map<String,String> params){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Booking> q = cb.createQuery(Booking.class);
            Root root = q.from(Booking.class);
            q.select(root);
            
            if(params!=null){
                List<Predicate> predicates = new ArrayList<>();
                
                String nguoiDung_id = params.get("idNguoiDung");
                if(nguoiDung_id!=null&&!nguoiDung_id.isEmpty()){
                    predicates.add(cb.equal(root.get("idNguoiDung").get("id").as(String.class), nguoiDung_id));
                }
                
                String choDoXe_id = params.get("idChoDo");
                if(choDoXe_id!=null&&!choDoXe_id.isEmpty()){
                    predicates.add(cb.equal(root.get("idChoDo").get("id").as(String.class), choDoXe_id));
                }
                
                String trangThai = params.get("trangThai");
                if(trangThai!=null&&!trangThai.isEmpty()){
                    predicates.add(cb.equal(root.get("trangThai").as(String.class), trangThai));
                }
                
                q.where(predicates.toArray(Predicate[]::new));
            }
            Query query = s.createQuery(q);
            
            return query.getResultList();
        }
    }
    
    public Booking createOrUpdate(Booking booking){
        try(Session s = HibernateUtils.getFACTORY().openSession()){
            if(booking.getId() != null){
                s.merge(booking);
            }else
                s.persist(booking);
            
            s.refresh(booking);
        }
        
        return booking;
    }
}
