package com.endava.tmd;

import java.util.concurrent.Callable;

public class Divisors implements Callable<Integer> {
    private int startRange;
    private int endRange;

    public Divisors(int startRange, int endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }


    public int countDivisors(int n) {
        int divisors = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                divisors++;
            }
        }
        return divisors;
    }

    public int maxDivisorsNumber() {
        int max = 0;
        int number = 0;
        for (int i = startRange; i <= endRange; i++) {
            int divisorsNumber = countDivisors(i);
            if (max <= divisorsNumber) {
                max = divisorsNumber;
                number = i;
            }
        }
        return number;
    }

    @Override
    public Integer call() throws Exception {
        return maxDivisorsNumber();
    }
}
