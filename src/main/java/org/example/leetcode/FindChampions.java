package org.example.leetcode;

import java.util.*;

/*
 Необходимо определить userIds участников, которые прошли наибольшее количество шагов steps за все дни, не пропустив ни одного дня соревнований.

 Пример 1
 ввод:
 statistics = [
         [{ userId: 1, steps: 1000 }, { userId: 2, steps: 1500 }],
         [{ userId: 2, steps: 1000 }]
 ]

 вывод:
 champions = { userIds: [2], steps: 2500 }

 Пример 2
 ввод:
 statistics = [
         [{ userId: 1, steps: 2000 }, { userId: 2, steps: 1500 }],
         [{ userId: 2, steps: 4000 }, { userId: 1, steps: 3500 }]
 ]

 вывод:
 champions = { userIds: [1, 2], steps: 5500 }
 * */
public class FindChampions {
    public static void main(String[] args) {


    }

    public static Map<String, Object> findChampions(List<List<Map<String, Integer>>> statistics) {
        Map<Integer, Integer> stepsPerUser = new HashMap<>();
        Map<Integer, Integer> participantsCount = new HashMap<>();
        int totalDays = statistics.size();

        for (List<Map<String, Integer>> dailyStats : statistics) {
            Set<Integer> dailyParticipants = new HashSet<>();
            for (Map<String, Integer> entry : dailyStats) {
                int userId = entry.get("userId");
                int steps = entry.get("steps");

                stepsPerUser.put(userId, stepsPerUser.getOrDefault(userId, 0) + steps);
                dailyParticipants.add(userId);
            }

            for (int userId : dailyParticipants) {
                participantsCount.put(userId, participantsCount.getOrDefault(userId, 0) + 1);
            }
        }

        Set<Integer> qualifiedUsers = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : participantsCount.entrySet()) {
            if (entry.getValue() == totalDays) {
                qualifiedUsers.add(entry.getKey());
            }
        }

        int maxSteps = 0;
        List<Integer> champions = new ArrayList<>();
        for (int userId : qualifiedUsers) {
            int totalSteps = stepsPerUser.get(userId);
            if (totalSteps > maxSteps) {
                maxSteps = totalSteps;
                champions.clear();
                champions.add(userId);
            } else if (totalSteps == maxSteps) {
                champions.add(userId);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("userIds", champions);
        result.put("steps", maxSteps);
        return result;
    }
}
