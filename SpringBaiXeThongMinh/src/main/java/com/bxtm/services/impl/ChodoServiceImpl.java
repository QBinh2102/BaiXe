/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.services.impl;

import com.bxtm.pojo.Chodo;
import com.bxtm.pojo.Booking;
import com.bxtm.repositories.ChodoRepository;
import com.bxtm.services.BookingService;
import com.bxtm.services.ChodoService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class ChodoServiceImpl implements ChodoService {

    @Autowired
    private ChodoRepository choDoRepo;

    @Autowired
    private BookingService bookingService;

    @Override
    public List<Chodo> getChoDo(Map<String, String> params) {
        return this.choDoRepo.getChoDo(params);
    }

    @Override
    public Chodo getChoDoById(int id) {
        return this.choDoRepo.getChoDoById(id);
    }

    @Override
    public Chodo createOrUpdate(Chodo choDo) {
        return this.choDoRepo.createOrUpdate(choDo);
    }

    // tạo thêm GetChoDoTrong 
    @Override
    public List<Chodo> getChoDoTrong(Map<String, String> params, LocalDateTime startTime, LocalDateTime endTime) {

        List<Chodo> choDoByID = this.choDoRepo.getChoDo(params);
        Map<String, String> paramsBooking = new HashMap();
        paramsBooking.put("idBaiDo", params.get("idBaiDo"));
        paramsBooking.put("startTime", String.valueOf(startTime));
        paramsBooking.put("endTime", String.valueOf(endTime));
        //paramsBooking.put("trangThai", "Đang đặt");
        List<Booking> bookingDaDat = this.bookingService.getBookings(paramsBooking);

//        for(int i = 0 ; i<bookingDaDat.size();i++){
//            for(int j = 0;j<choDoByID.size();j++){
//                if(choDoByID.get(j).equals(bookingDaDat.get(i).getIdChoDo())){
//                    choDoByID.remove(j);
//                    break;
//                }
//            }
//        }
        Set<Integer> idChoDoDaDat = bookingDaDat.stream()
                .map(booking -> booking.getIdChoDo().getId())
                .collect(Collectors.toSet());

        choDoByID.removeIf(choDo -> idChoDoDaDat.contains(choDo.getId()));

        return choDoByID;
    }
}
