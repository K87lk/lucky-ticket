package org.example.streams;

import lombok.*;

import java.math.BigDecimal;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Account {
    private UUID accountId;
    private UUID clientId;
    private AccountType accountType;
    private String accountNumber;
    private BigDecimal balance;
    private boolean isActive;
    private Currency currency;
}
