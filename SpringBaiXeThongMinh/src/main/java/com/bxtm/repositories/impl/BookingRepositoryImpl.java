/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.pojo.Booking;
import com.bxtm.repositories.BookingRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class BookingRepositoryImpl implements BookingRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Booking> getBookings(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Booking> q = cb.createQuery(Booking.class);
        Root root = q.from(Booking.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String baiDo_id = params.get("idBaiDo");
            if (baiDo_id != null && !baiDo_id.isEmpty()) {
                predicates.add(cb.equal(root.get("idBaiDo").get("id").as(String.class), baiDo_id));
            }

            String nguoiDung_id = params.get("idNguoiDung");
            if (nguoiDung_id != null && !nguoiDung_id.isEmpty()) {
                predicates.add(cb.equal(root.get("idNguoiDung").get("id").as(String.class), nguoiDung_id));
            }

            String choDoXe_id = params.get("idChoDo");
            if (choDoXe_id != null && !choDoXe_id.isEmpty()) {
                predicates.add(cb.equal(root.get("idChoDo").get("id").as(String.class), choDoXe_id));
            }

            String trangThai = params.get("trangThai");
            if (trangThai != null && !trangThai.isEmpty()) {
                predicates.add(cb.equal(root.get("trangThai").as(String.class), trangThai));
            }

            String startTimeString = params.get("startTime");
            String endTimeString = params.get("endTime");
            if (startTimeString != null && endTimeString != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime startTime = LocalDateTime.parse(startTimeString, formatter);
                LocalDateTime endTime = LocalDateTime.parse(endTimeString, formatter);

                Predicate timePredicate = cb.and(
                        cb.equal(root.get("trangThai"), "da_dat"),
                        cb.or(
                                cb.between(root.get("thoiGianBatDau"), startTime, endTime),
                                cb.between(root.get("thoiGianKetThuc"), startTime, endTime),
                                cb.and(
                                        cb.lessThanOrEqualTo(root.get("thoiGianBatDau"), startTime),
                                        cb.greaterThanOrEqualTo(root.get("thoiGianKetThuc"), endTime)
                                )
                        )
                );
                predicates.add(timePredicate);
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public Booking getBookingById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Booking.class, id);
    }

    @Override
    public Booking createOrUpdate(Booking booking) {
        Session s = this.factory.getObject().getCurrentSession();
        if (booking.getId() != null) {
            s.merge(booking);
        } else {
            s.persist(booking);
        }

        s.refresh(booking);

        return booking;
    }

    @Override
    public List<Booking> getBookingByUserID(int idNguoiDung) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Booking> q = cb.createQuery(Booking.class);
        Root root = q.from(Booking.class);
        q.select(root);

        Predicate predicate = cb.equal(root.get("idNguoiDung").get("id"), idNguoiDung);
        q.where(predicate);

        Query query = s.createQuery(q);
        return query.getResultList();
    }

}
