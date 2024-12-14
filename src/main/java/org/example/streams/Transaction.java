package org.example.streams;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    private Trader trader;
    private int year;
    private int value;
    private Currency currency;
}
