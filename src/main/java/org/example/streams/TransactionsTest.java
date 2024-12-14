package org.example.streams;

import net.datafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TransactionsTest {
    public static void main(String[] args) {

        List<Transaction> transactions = getTransactions(10);

        var transactionsIn2011 = transactions.stream()
                .filter(tr -> tr.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .toList();

        var cityList = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .toList();

        var tradersFromCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .toList();

        var allNames = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .toList();

        var milanTrader = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        var allTransactionsSum = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .toList();

        var maxSum = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        var minSum = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);

        var anotherMinSum = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));

        var transactionsByCurrencies = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency));

        var transactions2 = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getTrader,
                        Collectors.groupingBy(Transaction::getCurrency, Collectors.counting())));

        var transactions3 = transactions.stream()
                        .collect(Collectors.groupingBy(
                                Transaction::getYear,
                                Collectors.mapping(t -> t.getCurrency().toString(), Collectors.joining(", "))
                        ));



        System.out.println(transactions3);
    }

    private static List<Transaction> getTransactions(int howMany) {
        Faker faker = new Faker();
        Random random = new Random();
        List<Transaction> transactions = new ArrayList<>();

        IntStream.range(0, howMany).forEach(i -> {
            Trader trader = new Trader();
            trader.setCity(faker.address().city());
            trader.setName(faker.name().fullName());

            Currency currency = Currency.values()[random.nextInt(Currency.values().length)];

            Transaction transaction = new Transaction();
            transaction.setTrader(trader);
            transaction.setYear(random.nextInt(123) + 1900);
            transaction.setCurrency(currency);
            transaction.setValue(random.nextInt(1000));

            transactions.add(transaction);
        });
        return transactions;
    }
}
