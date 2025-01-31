package concepts.recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.

 */
public class Fibonacci {
    private static Map<Integer, Integer> cache;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(printFibonaciIt(n));
        cache = new HashMap<Integer, Integer>();
        cache.put(0, 0);
        cache.put(0, 1);
        System.out.println(fiboRecusrion(n));
        scanner.close();
    }

    private static int printFibonaciIt(int N) {
        int first = 0;
        int second = 1;
        int sum = 1;
        if (N <= 1)
            return N;
        for (int i = 2; i <= N; i++) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return sum;
    }

    private static int fiboRecusrion(int n) {
        if (n <= 1) {
            return n;
        } else {
            int first, second;
            if (cache.get(n) != null) {
                return cache.get(n);
            } else {
                first = fiboRecusrion(n - 1);
                cache.put(n - 1, first);
                second = fiboRecusrion(n - 2);
                cache.put(n - 2, second);
            }
            return first + second;
        }
    }
}