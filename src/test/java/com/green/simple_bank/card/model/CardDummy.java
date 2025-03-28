package com.green.simple_bank.card.model;

import com.green.simple_bank.Dummy;
import com.green.simple_bank.card.CardMapper;
import com.green.simple_bank.customer.CustomerMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

class CardDummy extends Dummy {
    final int ADD_ROW_COUNT = 10_000;

    @Autowired CustomerMapper customerMapper;
    @Autowired CardMapper cardMapper;

    @Test
    void generate() {
        List<Integer> customerIdList = customerMapper.findIdAll();
        System.out.printf("customerIdList size: %d\n", customerIdList.size());

        int maxId = cardMapper.findMaxId(); // 마지막 id 값 + 1
        int endRowCount = maxId + ADD_ROW_COUNT;

        for (int i = maxId + 1; i <= endRowCount; i++) {
            String cardNumber = String.format("%04d%04d%04d%04d", enFaker.random().nextInt(10_000)
                                                                , enFaker.random().nextInt(10_000)
                                                                , enFaker.random().nextInt(10_000)
                                                                , enFaker.random().nextInt(10_000));

            Card card = Card.builder()
                    .cardId(i)
                    .cardNumber(cardNumber)
                    .expirationDate(enFaker.date().future(enFaker.random().nextInt(1000) + 1, TimeUnit.HOURS, "YYYY-MM-dd"))
                    //.expirationDate("2025-03-29")
                    .customerId(customerIdList.get(enFaker.random().nextInt(customerIdList.size() - 1)))
                    .build();
            try {
                cardMapper.save(card);
            } catch(Exception e) {
                i--;
            }
        }
    }
}