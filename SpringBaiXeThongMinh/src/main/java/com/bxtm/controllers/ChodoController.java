/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Baido;
import com.bxtm.pojo.Chodo;
import com.bxtm.services.BaidoService;
import com.bxtm.services.ChodoService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author huynh
 */
@Controller
@RequestMapping("/baidos")
public class ChodoController {

    @Autowired
    private BaidoService baiDoService;
    @Autowired
    private ChodoService choDoService;

    @GetMapping("/{idBaiDo}/choDo/")
    public String detailBaiDo(Model model,
            @PathVariable("idBaiDo") int id) {

        Baido baiDo = this.baiDoService.getBaiDoById(id);
        model.addAttribute("baido", baiDo);

        return "chiTietBaiDo";
    }

    @PostMapping("/{idBaiDo}/choDo/search")
    public String search(Model model,
            @PathVariable("idBaiDo") int idBaiDo,
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        Baido baiDo = this.baiDoService.getBaiDoById(idBaiDo);
        model.addAttribute("baido", baiDo);

        Map<String, String> params = new HashMap<>();
        params.put("idBaiDo", String.valueOf(idBaiDo));

        model.addAttribute("choTrong", choDoService.getChoDoTrong(params, startTime, endTime));
        return "chiTietBaiDo";
    }

    @GetMapping(("/admin/{idBaiDo}/choDo/"))
    public String getChodosByBaiDo(@PathVariable("idBaiDo") int idBaiDo, Model model) {
        Baido baiDo = baiDoService.getBaiDoById(idBaiDo);

        Map<String, String> params = new HashMap<>();
        params.put("idBaiDo", String.valueOf(idBaiDo));
        List<Chodo> choDos = choDoService.getChoDo(params);

        model.addAttribute("baido", baiDo);
        model.addAttribute("choDos", choDos);

        return "chodos";
    }

    @PostMapping("/admin/{idBaiDo}/choDo/update")
    public String updateChodos(@PathVariable("idBaiDo") int idBaiDo,
            @RequestParam("ids") List<Integer> ids,
            @RequestParam("viTris") List<String> viTris,
            @RequestParam("trangThais") List<String> trangThais) {

        for (int i = 0; i < ids.size(); i++) {
            Integer id = ids.get(i);
            String viTri = viTris.get(i);
            String trangThai = trangThais.get(i);

            Chodo choDo = choDoService.getChoDoById(id);
            if (choDo != null) {
                choDo.setViTri(viTri);
                choDo.setTrangThai(trangThai);
                choDoService.createOrUpdate(choDo);
            }
        }

        return "redirect:/baidos/admin/" + idBaiDo + "/choDo/";
    }

}
