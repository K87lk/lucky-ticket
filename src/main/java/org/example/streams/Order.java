package org.example.streams;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String id;
    private List<Dish> dishes;
    private BigDecimal totalPrice;
    private BigDecimal discountPercentage;
    private BigDecimal taxPercentage;
}
