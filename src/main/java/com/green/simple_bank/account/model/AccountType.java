package com.green.simple_bank.account.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    Savings("01"),
    Checking("02"),
    FixedDeposit("03"),
    Investment("04");

    private final String code;

}
