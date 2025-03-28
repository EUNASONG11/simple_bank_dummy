package com.green.simple_bank.account;

import com.green.simple_bank.Dummy;
import com.green.simple_bank.account.model.Acc;
import com.green.simple_bank.customer.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountDummy extends Dummy {
    final int ADD_ROW_COUNT = 20;

    @Autowired AccountMapper accountMapper;
    @Autowired CustomerMapper customerMapper;

    @Test
    void generate() {
        String[] accountTypeList = {"01", "02", "03", "04"};

        List<Integer> customerIdList = customerMapper.findIdAll();
        System.out.printf("customerIdList size: %d\n", customerIdList.size());

        int maxId = accountMapper.findMaxId(); // 마지막 id 값 + 1
        int endRowCount = maxId + ADD_ROW_COUNT;

        for (int i = maxId + 1; i <= endRowCount; i++) {
            String accountType = accountTypeList[enFaker.random().nextInt(accountTypeList.length - 1)];

            Acc acc = Acc.builder()
                    .accountId(i)
                    .accountType(accountType)
                    .balance(enFaker.random().nextDouble() + enFaker.random().nextInt(9999999))
                    .customerId(enFaker.random().nextInt(customerIdList.size() - 1))
                    .build();

            accountMapper.save(acc);
        }
    }
}