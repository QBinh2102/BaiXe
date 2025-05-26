/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.services.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiVNPayController {
    @Autowired
    private VNPayService vnpayService;
    
    @GetMapping("/create-payment")
    public ResponseEntity<Map<String, Object>> createPayment(
            @RequestParam("amount") long amount, // Rõ ràng yêu cầu param
            @RequestParam("bankCode") String bankCode,
            HttpServletRequest request) {

        try {
            String orderId = UUID.randomUUID().toString().replace("-", "").substring(0, 18);
            String paymentUrl = vnpayService.createPayment(orderId, amount, bankCode, request);

            return ResponseEntity.ok(Map.of(
                "code", "00",
                "message", "Success",
                "paymentUrl", paymentUrl
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "code", "99",
                "message", e.getMessage()
            ));
        }
    }

    // Endpoint xử lý kết quả trả về từ VNPay
    @GetMapping("/payment-result")
    public ResponseEntity<Map<String, String>> handlePaymentResult(
            @RequestParam Map<String, String> params) {
        
        String responseCode = params.get("vnp_ResponseCode");
        if ("00".equals(responseCode)) {
            return ResponseEntity.ok(Map.of(
                "code", "00",
                "message", "Payment successful",
                "transactionId", params.get("vnp_TransactionNo")
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                "code", responseCode,
                "message", "Payment failed: " + params.get("vnp_ResponseMessage")
            ));
        }
    }
}
