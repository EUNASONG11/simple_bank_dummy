package com.green.simple_bank.customer;

import com.green.simple_bank.Dummy;
import com.green.simple_bank.customer.model.Customer;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class CustomerDummy extends Dummy {

    final int ADD_ROW_COUNT = 1_000; // 기존의 데이터에서 더 추가하고 싶은 row count

    @Test
    void generate() {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class); // 이미 만들어진 CustomerMapper 주소값을 가져오는 것

        int maxId = customerMapper.findMaxId(); // 마지막 id 값 + 1
        int endRowCount = maxId + ADD_ROW_COUNT;

        for (int i = maxId + 1; i <= endRowCount; i++) {
            Customer customer = Customer.builder()
                    .customerId(i)
                    .name(koFaker.name().lastName() + koFaker.name().firstName())
                    .email(i + enFaker.internet().emailAddress())
                    .build();


            customerMapper.save(customer);
            sqlSession.flushStatements();
        }

    }
}
