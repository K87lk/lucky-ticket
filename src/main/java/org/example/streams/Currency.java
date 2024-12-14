package org.example.streams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {
    USD("United States Dollar"),
    EUR("Euro"),
    GBP("British Pound Sterling"),
    JPY("Japanese Yen"),
    CHF("Swiss Franc"),
    CAD("Canadian Dollar"),
    AUD("Australian Dollar"),
    NZD("New Zealand Dollar"),
    SGD("Singapore Dollar"),
    HKD("Hong Kong Dollar"),
    SEK("Swedish Krona"),
    NOK("Norwegian Krone"),
    DKK("Danish Krone"),
    INR("Indian Rupee"),
    CNY("Chinese Yuan"),
    BRL("Brazilian Real"),
    ZAR("South African Rand"),
    RUB("Russian Ruble"),
    MXN("Mexican Peso"),
    KRW("South Korean Won");

    private final String fullName;
}
