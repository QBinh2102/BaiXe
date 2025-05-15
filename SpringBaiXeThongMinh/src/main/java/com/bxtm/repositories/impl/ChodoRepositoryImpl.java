/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.pojo.Chodo;
import com.bxtm.repositories.ChodoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author GIGA
 */
@Repository
@Transactional
public class ChodoRepositoryImpl implements ChodoRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Chodo> getChoDo(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Chodo> q = cb.createQuery(Chodo.class);
        Root root = q.from(Chodo.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String baiDo_id = params.get("idBaiDo");
            if (baiDo_id != null && !baiDo_id.isEmpty()) {
                predicates.add(cb.equal(root.get("idBaiDo").get("id").as(String.class), baiDo_id));
            }

            String trangThai = params.get("trangThai");
            if (trangThai != null && !trangThai.isEmpty()) {
                predicates.add(cb.equal(root.get("trangThai").as(String.class), trangThai));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);

        return query.getResultList();
    }
    
    @Override
    public Chodo getChoDoById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Chodo.class, id);
    }

    @Override
    public Chodo createOrUpdate(Chodo choDo) {
        Session s = this.factory.getObject().getCurrentSession();
        if (choDo.getId() == null) {
            s.persist(choDo);
        } else {
            s.merge(choDo);
        }

        s.refresh(choDo);
        return choDo;
    }

    @Override
    public void deleteChoDo(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Chodo p = this.getChoDoById(id);
        s.remove(p);

    }
//   @Override
//    public List<Chodo> getChoDoTrong(int idBaiDo, LocalDateTime start, LocalDateTime end) {
//        Session session = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Chodo> cq = cb.createQuery(Chodo.class);
//        Root<Chodo> root = cq.from(Chodo.class);
//        cq.select(root);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//      
//        predicates.add(cb.equal(root.get("idBaiDo").get("id"), idBaiDo));
//        predicates.add(cb.equal(root.get("trangThai"), "Bình thường"));
//
//     
//        Subquery<Long> subquery = cq.subquery(Long.class);
//        Root<com.bxtm.pojo.Booking> bookingRoot = subquery.from(com.bxtm.pojo.Booking.class);
//        subquery.select(cb.literal(1L));
//        subquery.where(
//            cb.equal(bookingRoot.get("idChoDo").get("id"), root.get("id")),
//            cb.notEqual(bookingRoot.get("trangThai"), "Hủy"),
//            cb.lessThanOrEqualTo(bookingRoot.get("thoiGianBatDau"), end),
//            cb.greaterThanOrEqualTo(bookingRoot.get("thoiGianKetThuc"), start)
//        );
//
//        predicates.add(cb.not(cb.exists(subquery)));
//
//        cq.where(predicates.toArray(Predicate[]::new));
//        System.out.println("Check ID BaiDo: " + idBaiDo);
//        System.out.println("Start time: " + start);
//        System.out.println("End time: " + end);
//
//        List<Chodo> result = session.createQuery(cq).getResultList();
//        System.out.println("Số chỗ trống tìm được: " + result.size());
//
//
//
//        return session.createQuery(cq).getResultList();
//    }


}
