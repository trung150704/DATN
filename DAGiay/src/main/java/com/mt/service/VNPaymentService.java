package com.mt.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mt.DTO.OrderRequest;

@Service
public class VNPaymentService {

    // Thông tin từ tài khoản VNPay
    private static final String VNP_VERSION = "2.1.0";
    private static final String VNP_COMMAND = "pay";
    private static final String VNP_TMNCODE = "G1JFP6JX";
    private static final String VNP_HASH_SECRET = "FBFU8D7TOPMJDKIQ6TESCKE5YRQFYUD4";
    private static final String VNP_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    private static final String VNP_RETURNURL = "http://localhost:8080/api/payment/return";

    public String generateVnpayQRCode(OrderRequest orderRequest) {
        // Cài đặt thông tin đơn hàng
        String vnp_Amount = String.valueOf(orderRequest.getAmount() * 100); // Đơn vị là VND
        String vnp_OrderInfo = "Thanh toan don hang " + orderRequest.getOrderId();
        String vnp_Locale = "vn";
        
        // Thời gian tạo mã giao dịch
        String vnp_TxnRef = String.valueOf(System.currentTimeMillis());
        String vnp_IpAddr = "127.0.0.1";
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNP_VERSION);
        vnp_Params.put("vnp_Command", VNP_COMMAND);
        vnp_Params.put("vnp_TmnCode", VNP_TMNCODE);
        vnp_Params.put("vnp_Amount", vnp_Amount);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", "billpayment");
        vnp_Params.put("vnp_Locale", vnp_Locale);
        vnp_Params.put("vnp_ReturnUrl", VNP_RETURNURL);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        // Tạo mã băm để bảo mật giao dịch
        String queryUrl = VNP_URL + "?" + generateQueryUrl(vnp_Params);
        return queryUrl;
    }

    public boolean validateVnpayReturn(Map<String, String> allParams) {
        // Lấy mã hash của VNPay và kiểm tra tính hợp lệ
        String vnp_SecureHash = allParams.get("vnp_SecureHash");
        allParams.remove("vnp_SecureHash");
        String generatedHash = generateHash(allParams);
        return vnp_SecureHash.equals(generatedHash);
    }

    private String generateQueryUrl(Map<String, String> params) {
        // Sắp xếp tham số và tạo chuỗi query
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (query.length() > 0) query.append("&");
            query.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return query.toString();
    }

    private String generateHash(Map<String, String> params) {
        // Tạo chuỗi hash từ các tham số và key bí mật
        String hash = ""; // Hàm tạo hash (SHA256) sẽ được tích hợp tại đây
        return hash;
    }
}
