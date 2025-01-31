package topQuestions.wayfair;

import java.util.*;

/*
https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
F

LeetCode company workers use key-cards to unlock office doors. Each time a worker uses their key-card, the security
system saves the worker's name and the time when it was used. The system emits an alert if any worker uses the key-card
three or more times in a one-hour period. You are given a list of strings keyName and keyTime where
[keyName[i], keyTime[i]] corresponds to a person's name and the time when their key-card was used in a single day.

Access times are given in the 24-hour time format "HH:MM", such as "23:51" and "09:49".
Return a list of unique worker names who received an alert for frequent keycard use.
Sort the names in ascending order alphabetically.
Notice that "10:00" - "11:00" is considered to be within a one-hour period, while "22:51" - "23:52"
is not considered to be within a one-hour period.
 */

public class AlertUsingSameKeyCardThreeORMoreTimesInAOneHourPeriod {

    public static void main(String[] args) {
        String[] key = new String[]{"daniel", "daniel", "daniel", "luis", "luis", "luis", "luis"};
        String[] time = new String[]{"10:00", "10:40", "11:00", "09:00", "11:00", "13:00", "15:00"};
        System.out.println(alertNames(key, time));

        key = new String[]{"alice", "alice", "alice", "bob", "bob", "bob", "bob"};
        time = new String[]{"12:01", "12:00", "18:00", "21:00", "21:20", "21:30", "23:00"};
        System.out.println(alertNames(key, time));

        key = new String[]{"john", "john", "john"};
        time = new String[]{"23:58", "23:59", "00:01"};
        System.out.println(alertNames(key, time));

        key = new String[]{"leslie", "leslie", "leslie", "clare", "clare", "clare", "clare"};
        time = new String[]{"13:00", "13:20", "14:00", "18:00", "18:51", "19:30", "19:49"};
        System.out.println(alertNames(key, time));


        key = new String[]{"a", "a", "a", "a", "b", "b", "b", "b", "b", "b", "c", "c", "c", "c"};
        time = new String[]{"01:35", "08:43", "20:49", "00:01", "17:44", "02:50", "18:48", "22:27", "14:12", "18:00", "12:38", "20:40", "03:59", "22:24"};
        System.out.println(alertNames(key, time));

    }

    public static List<String> alertNames(String[] keyName, String[] keyTime) {
        List<String> sol = new ArrayList<>();
        Map<String, PriorityQueue<Integer>> map = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            PriorityQueue<Integer> pq = map.get(name);
            if (pq == null) pq = new PriorityQueue<>();
            String[] timeArr = time.split(":");
            int timeInt = Integer.parseInt(timeArr[0] + timeArr[1]);
            pq.add(timeInt);
            map.put(name, pq);
        }

        //Go through list and check for each employee
        for (String name : map.keySet()) {
            PriorityQueue<Integer> pq = map.get(name);
            int first, second, third;
            if (pq.size() < 3) continue;
            first = pq.poll();
            second = pq.poll();
            while (!pq.isEmpty()) {
                third = pq.poll();
                if (second - first <= 100 && third - first <= 100) {
                    sol.add(name);
                    break;
                }
                first = second;
                second = third;
            }
        }
        Collections.sort(sol);
        return sol;
    }


}
