package org.example.streams;

import java.util.List;

public class UserOrders {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User(1, "John"),
                new User(2, "Jane"),
                new User(3, "Jim"),
                new User(4, "Jill"));

        List<Order> orders = List.of(
                new Order(1, 100),
                new Order(2, 200),
                new Order(2, 300),
                new Order(1, 400));

        System.out.println(findUsersWithoutOrders(users, orders));

    }

    public static List<User> findUsersWithoutOrders(List<User> users, List<Order> orders) {
        return users.stream()
                .filter(user -> orders.stream()
                        .noneMatch(order -> order.userId == user.id))
                .toList();
    }

    record User(int id, String username) {}
    record Order(int userId, int sum) {}
}
