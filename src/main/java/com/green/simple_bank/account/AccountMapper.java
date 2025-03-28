package com.green.simple_bank.account;

import com.green.simple_bank.account.model.Acc;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    int save(Acc acc);
    int findMaxId();
}
