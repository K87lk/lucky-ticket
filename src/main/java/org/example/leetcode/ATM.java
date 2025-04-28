package org.example.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ATM {
    private Map<Integer, Integer> nominalMap;

    public ATM(Map<Integer, Integer> nominalMap) {
        this.nominalMap = nominalMap;
    }

    public List<Integer> withdraw(int amount) {
        List<Integer> result = new ArrayList<>();
        if (amount % 50 != 0) {
            throw new IllegalArgumentException("Cannot withdraw odd amount");
        }
        if (amount > getAvailableATMCash()) {
            throw new IllegalArgumentException("Cannot withdraw. Amount is bigger than existing ATM cash");
        }
        int[] nominals = {5000, 1000, 500, 100, 50};
        for (int nominal : nominals) {
            Integer existingBanknotes = nominalMap.get(nominal);
            if (existingBanknotes != null && existingBanknotes > 0) {
                int requiredBanknotes = Math.min(amount / nominal, existingBanknotes);
                nominalMap.put(nominal, existingBanknotes - requiredBanknotes);
                result.addAll(Collections.nCopies(requiredBanknotes, nominal));
                amount -= requiredBanknotes * nominal;
            }
        }

        if (amount > 0) {
            throw new IllegalArgumentException("Cannot withdraw. Not enough banknotes for this amount");
        }
        return result;
    }

    public void deposit(List<Integer> banknotes) {
        for (Integer banknote : banknotes) {
            nominalMap.merge(banknote, 1, Integer::sum);
        }
    }

    public int getAvailableATMCash() {
        return nominalMap.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey() * entry.getValue())
                .sum();
    }
}