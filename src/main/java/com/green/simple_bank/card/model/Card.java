package com.green.simple_bank.card.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Card {
    private int cardId;
    private String cardNumber;
    private String expirationDate;
    private int customerId;
}
