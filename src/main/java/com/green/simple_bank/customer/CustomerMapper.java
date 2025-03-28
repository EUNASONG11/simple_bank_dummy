package com.green.simple_bank.customer;

import com.green.simple_bank.customer.model.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
    int save(Customer customer);
    int clear();
}
