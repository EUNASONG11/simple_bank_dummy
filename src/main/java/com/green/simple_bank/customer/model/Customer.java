package com.green.simple_bank.customer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Customer {
    private int customerId;
    private String name;
    private String email;
}
