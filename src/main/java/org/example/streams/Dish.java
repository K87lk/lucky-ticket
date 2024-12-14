package org.example.streams;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Dish {
    private String id;
    private DishCategory category;
    private String name;
    private BigDecimal price;
    private int calories;
    private boolean isVegetarian;
}
