package com.mt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private String paymentMethod; 
}

