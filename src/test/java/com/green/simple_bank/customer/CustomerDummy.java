package com.green.simple_bank.customer;

import com.green.simple_bank.customer.model.Customer;
import net.datafaker.Faker;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.Locale;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CustomerDummy {

    @Autowired SqlSessionFactory sqlSessionFactory;

    Faker koFaker = new Faker(new Locale("ko"));
    Faker enFaker = new Faker(new Locale("en"));

    final int addRowCount = 10_000; // 기존의 데이터에서 더 추가하고 싶은 row count

    @Test
    void generate() {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);

        int startId = 10; // 마지막 id 값 + 1

        int endRowCount = startId + addRowCount;

        for (int i = startId; i <= endRowCount; i++) {
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
