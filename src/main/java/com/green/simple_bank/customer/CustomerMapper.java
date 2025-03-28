package com.green.simple_bank.customer;

import com.green.simple_bank.customer.model.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {
    int save(Customer customer);
    int findMaxId();
    List<Integer> findIdAll();
}
