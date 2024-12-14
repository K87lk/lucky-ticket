package org.example.streams;

import net.datafaker.Faker;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static net.datafaker.providers.base.Text.*;

public class ClientsAccounts {
    public static void main(String[] args) {

        List<Client> clients = createClients(40, 20);
        var clientAccounts = clients.stream()
                .collect(toMap(Function.identity(), Client::getAccounts));

        var filteredActiveAccounts = clients.stream()
                .flatMap(c -> c.getAccounts().stream())
                .filter(Account::isActive)
                .collect(toMap(Account::getAccountId, Function.identity(), (a, b) -> a));

        var totalBalanceOfALlClients = clients.stream()
                .flatMap(c -> c.getAccounts().stream())
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalBalanceOfEachClient = clients.stream()
                .map(c -> {
                    return c.getAccounts().stream()
                            .map(Account::getBalance)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                }).toList();

        var clientsWithAtLeastOneActiveAccount = clients.stream()
                .filter(c -> c.getAccounts().stream().anyMatch(Account::isActive))
                .toList();

        var richestAccount = clients.stream()
                .flatMap(c -> c.getAccounts().stream())
                .max(Comparator.comparing(Account::getBalance))
                .orElseThrow(() -> new RuntimeException("No richest account"));

        var accountsByType = clients.stream()
                .flatMap(c -> c.getAccounts().stream())
                .collect(groupingBy(Account::getAccountType, Collectors.counting())); // {CHECKING=13, SAVINGS=16, CREDIT_CARD=8}

        var accountsByCurrency = clients.stream()
                .flatMap(c -> c.getAccounts().stream())
                .collect(groupingBy(Account::getCurrency, Collectors.counting()));

        var clientsWithUSD = clients.stream()
                .filter(c -> c.getAccounts().stream().anyMatch(a -> a.getCurrency() == Currency.USD))
                .toList();

        var clientsBalances = clients.stream()
                .collect(toMap(Client::getFullName, c -> c.getAccounts().stream()
                        .map(Account::getBalance)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))); // Allen Zboncak=118356796.295276163

        var hasZeroBalance = clients.stream()
                .flatMap(c -> c.getAccounts().stream())
                .anyMatch(a -> a.getBalance().compareTo(BigDecimal.ZERO) == 0);

        var groupedClientsByNumberOfAccounts = clients.stream()
                .collect(groupingBy(c -> c.getAccounts().size()));

        var totalBalancesByCurrency = clients.stream()
                .flatMap(c -> c.getAccounts().stream())
                .collect(groupingBy(Account::getCurrency,
                        mapping(Account::getBalance, reducing(BigDecimal.ZERO, BigDecimal::add)))); // JPY=158369272.976681318, NOK=502469089.035508896

        var accountsGroupedByType = clients.stream()
                .flatMap(c -> c.getAccounts().stream())
                .collect(groupingBy(Account::getAccountType));

        var onlyCreditCardAccounts = accountsGroupedByType.values().stream()
                .flatMap(Collection::stream)
                .filter(a -> a.getAccountType() == AccountType.CREDIT_CARD)
                .toList();

        var highestBalanceByCurrency = clients.stream()
                .flatMap(c -> c.getAccounts().stream())
                .collect(groupingBy(Account::getCurrency,
                        maxBy(Comparator.comparing(Account::getBalance))));

        var clientsGroupedByNameAndAccountCount = clients.stream()
                .collect(groupingBy(Client::getFullName, summarizingLong(c -> c.getAccounts().size())));

        var accountsGroupedByCurrency = clients.stream()
                .flatMap(c -> c.getAccounts().stream())
                .collect(groupingBy(Account::getCurrency, counting()));

        var clientsAndNumberOfTheirAccounts = clients.stream()
                .collect(toMap(Client::getFullName, c -> c.getAccounts().size()));

        var sumOfAccounts = clients.stream().mapToInt(c -> c.getAccounts().size()).sum();
        System.out.println(sumOfAccounts);
    }

    private static List<Client> createClients(int numAccounts, int numClients) {
        List<Account> accounts = new ArrayList<>();
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < numAccounts; i++) {
            Account account = new Account();
            account.setAccountId(UUID.randomUUID());
            account.setAccountType(AccountType.values()[random.nextInt(AccountType.values().length)]);
            account.setAccountNumber(faker.bothify("####-??##-####-??##-???#"));
            account.setActive(random.nextBoolean());
            account.setBalance(BigDecimal.valueOf(random.nextDouble(100_000_000)));
            account.setCurrency(Currency.values()[random.nextInt(Currency.values().length)]);
            accounts.add(account);
        }

        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < numClients; i++) {
            Client client = new Client();
            client.setClientId(UUID.randomUUID());
            client.setFullName(faker.name().fullName());
            client.setEmail(faker.internet().emailAddress());
            client.setAddress(faker.address().fullAddress());
            client.setPhoneNumber(faker.phoneNumber().phoneNumber());
            client.setPassword(faker.text().text(TextSymbolsBuilder.builder().len(16)
                    .with(EN_LOWERCASE, 3)
                    .with(EN_UPPERCASE, 5).with(DIGITS, 4)
                    .with(DEFAULT_SPECIAL, 4)
                    .build()));

            int numAccountsForClients = random.nextInt(Math.min(accounts.size(), 3)) + 1;
            List<Account> accountsForClients = new ArrayList<>();
            for (int j = 0; j < numAccountsForClients; j++) {
                Account randomAccount = accounts.get(random.nextInt(accounts.size()));
                if (!accountsForClients.contains(randomAccount)) {
                    accountsForClients.add(randomAccount);
                    client.setAccounts(accountsForClients);
                    randomAccount.setClientId(client.getClientId());
                }
            }
            clients.add(client);
        }
        return clients;
    }
}