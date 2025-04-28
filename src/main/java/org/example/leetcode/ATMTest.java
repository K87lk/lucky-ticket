package org.example.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATMTest {
    public static void main(String[] args) {
        Map<Integer, Integer> nominals = new HashMap<>();

        nominals.put(50, 0);
        nominals.put(100, 0);
        nominals.put(500, 100);
        nominals.put(1000, 100);
        nominals.put(5000, 100);

        ATM atm = new ATM(nominals);

        System.out.println(atm.getAvailableATMCash());
        atm.deposit(List.of(1000, 1000, 500, 500, 500, 500, 500));
        System.out.println(atm.getAvailableATMCash());
        System.out.println(atm.withdraw(11500));
        System.out.println(atm.getAvailableATMCash());
    }
}
