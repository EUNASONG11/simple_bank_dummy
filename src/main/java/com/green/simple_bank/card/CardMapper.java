package com.green.simple_bank.card;

import com.green.simple_bank.card.model.Card;
import com.green.simple_bank.customer.model.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMapper {
    int save(Card card);
    int findMaxId();
}
