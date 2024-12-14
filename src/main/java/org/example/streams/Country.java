package org.example.streams;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Country {
    UK("United Kingdom"),
    US("United States"),
    FR("France"),
    DE("Germany"),
    ES("Spain"),
    IT("Italy"),
    NL("Netherlands"),
    BE("Belgium"),
    PL("Poland"),
    CZ("Czech Republic"),
    HU("Hungary"),
    RO("Romania"),
    SK("Slovakia"),
    SI("Slovenia"),
    AT("Austria"),
    CH("Switzerland"),
    SE("Sweden"),
    DK("Denmark"),
    NO("Norway"),
    FI("Finland"),
    LT("Lithuania"),
    LV("Latvia"),
    EE("Estonia"),
    LU("Luxembourg"),
    IE("Ireland");

    private final String name;

}
