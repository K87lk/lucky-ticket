package org.example.streams;

import net.datafaker.Faker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Streams {
    public static void main(String[] args) {
//        var streamOfLists = Stream.of(List.of(12, 22, 31), List.of(14, 52, 16), List.of(72, 81, 19));
//        var index = new AtomicInteger(0);
//
//       var flatMappedNumbers = streamOfLists.mapMulti(Iterable::forEach).map(Object::toString)
//               .collect(Collectors.toMap(e -> index.getAndIncrement(), Integer::valueOf, (a, b) -> a,  LinkedHashMap::new));

        List<Dish> dishes = createDishes(20);
        List<Order> orders = createOrders(20);

        var opt = dishes.stream().filter(Dish::isVegetarian).findFirst().orElseGet(Dish::new);
        var opt2 = dishes.stream().anyMatch(dish -> dish.getId().startsWith("1"));

        var lowCaloricDishes = dishes.stream()
                .filter(dish -> dish.getCalories() < 1000)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .toList();

        var dishesByType = dishes.stream()
                .collect(Collectors.groupingBy(Dish::getCategory));

        var threeHighCaloricDishes = dishes.stream()
                .filter(dish -> dish.getCalories() > 1000)
                .map(Dish::getName)
                .limit(3)
                .toList();

        var vegetarianDishes = dishes.stream()
                .filter(Dish::isVegetarian)
                .toList();

        Optional<Dish> vegetarianMenu = dishes.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        var calories = dishes.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);

        var calories2 = dishes.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        var maxCalories = dishes.stream()
                .mapToInt(Dish::getCalories)
                .max()
                .orElse(0);

        var pythagoreanTriples = IntStream.rangeClosed(0, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(0, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        var dishesFromOrders = orders.stream()
                .map(Order::getDishes)
                .toList();

        var dishesByOrderId = orders.stream()
                .collect(Collectors.groupingBy(Order::getId));

        var dishesByCategory = orders.stream()
                .flatMap(order -> order.getDishes().stream())
                .collect(Collectors.groupingBy(Dish::getCategory));

        var ordersByIdAndListOfDishes = orders.stream()
                .collect(Collectors.toMap(Order::getId, Order::getDishes));

        var orders2 = orders.stream()
                .collect(Collectors.groupingBy(Order::getTotalPrice));

        System.out.println(orders2);

    }

    private static List<Dish> createDishes(int howMany) {
        Faker faker = new Faker();
        Random random = new Random();
        List<Dish> dishes = new ArrayList<>();

        IntStream.range(0, howMany).forEach(i -> {
            Dish dish = new Dish();
            dish.setId(UUID.randomUUID().toString());
            dish.setName(faker.food().dish());
            dish.setCategory(DishCategory.values()[random.nextInt(DishCategory.values().length)]);
            dish.setPrice(BigDecimal.valueOf(random.nextInt(5000)));
            dish.setCalories(random.nextInt(1200));
            dish.setVegetarian(random.nextBoolean());

            dishes.add(dish);
        });
        return dishes;
    }

    private static List<Order> createOrders(int howMany) {
        List<Order> orders = new ArrayList<>();
        Random random = new Random();

        IntStream.range(0, howMany).forEach(i -> {
            Order order = new Order();
            order.setId(UUID.randomUUID().toString());
            order.setDishes(createDishes(random.nextInt(10)));
            order.setTotalPrice(order.getDishes().stream().map(Dish::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));

            if(order.getTotalPrice().intValue() >= 2000) {
                order.setDiscountPercentage(new BigDecimal("10"));
                order.setTaxPercentage(new BigDecimal("5"));
            }
            if(order.getTotalPrice().intValue() >= 5000) {
                order.setDiscountPercentage(new BigDecimal("20"));
                order.setTaxPercentage(new BigDecimal("10"));
            }
            if(order.getTotalPrice().intValue() >= 10000) {
                order.setDiscountPercentage(new BigDecimal("30"));
                order.setTaxPercentage(new BigDecimal("15"));
            }
            if(order.getTotalPrice().intValue() < 2000) {
                order.setDiscountPercentage(new BigDecimal("0"));
                order.setTaxPercentage(new BigDecimal("0"));
            }
            order.setTotalPrice(calculateTotal(order));
            orders.add(order);
        });
        return orders;
    }
    private static BigDecimal calculateTotal(Order order) {
        var subtotal = order.getTotalPrice();

        var discount = subtotal
                .multiply(order.getDiscountPercentage())
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

        var totalAfterDiscount = subtotal.subtract(discount);

        var tax = totalAfterDiscount.multiply(order.getTaxPercentage())
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

        return totalAfterDiscount.add(tax);
    }
}