/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bxtm.services;

import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author toquocbinh2102
 */
public interface VNPayService {
    String createPayment(String orderId, long amount, String bankCode, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    String hmacSHA512(String key, String data) throws NoSuchAlgorithmException;
}
