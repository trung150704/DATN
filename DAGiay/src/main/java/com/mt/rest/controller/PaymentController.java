package com.mt.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mt.DTO.OrderRequest;
import com.mt.service.VNPaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private VNPaymentService paymentService;

    // Endpoint để tạo mã QR
    @PostMapping("/generate-qr")
    public ResponseEntity<String> generateQRCode(@RequestBody OrderRequest orderRequest) {
        String qrCodeUrl = paymentService.generateVnpayQRCode(orderRequest);
        return ResponseEntity.ok(qrCodeUrl);
    }

    // Endpoint để xử lý phản hồi sau khi thanh toán
    @PostMapping("/return")
    public ResponseEntity<String> handleVnpayReturn(@RequestParam Map<String, String> allParams) {
        // Xác thực mã hash của VNPay và kiểm tra trạng thái giao dịch
        boolean isValid = paymentService.validateVnpayReturn(allParams);
        if (isValid) {
            // Xử lý khi thanh toán thành công
            return ResponseEntity.ok("Thanh toán thành công");
        } else {
            // Xử lý khi thanh toán thất bại
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thanh toán thất bại");
        }
    }
}
