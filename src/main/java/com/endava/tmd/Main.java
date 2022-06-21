package com.endava.tmd;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Single threaded solution
        System.out.println("Single threaded");
        long start1 = System.currentTimeMillis();
        int max = 0;
        int number1 = 0;

        for (int i = 1; i <= 100000; i++) {
            int nrDivizori = countDivisors(i);
            if (max <= nrDivizori) {
                max = nrDivizori;
                number1 = i;
            }
        }
        System.out.println("Number with most divisors = " + number1 + " with  " + max + " divisors");
        long end1 = System.currentTimeMillis();
        long result1 = end1 - start1;
        System.out.println("Time for single threaded solution in ms: " + (result1));


        //Multiple threaded solution
        System.out.println("Multiple threaded");
        long start2 = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<Integer>[] results = new Future[10];
        int startRange = 0;
        int endRange = 0;
        //creating 10 Divisors objects with equally split 10 ranges
        for (int i = 0; i < 10; i++) {
            startRange = endRange + 1;
            endRange += 10000;
            Divisors d = new Divisors(startRange, endRange);
            results[i] = es.submit(d);
        }
        es.shutdown();
        //Nu are rost sa facem verificare ce numar din array are cei mai multi divizori este clar ca numerele mai mari au mai multi
        int number2 = results[9].get();
        System.out.println("Number with most divisors = " + number2 + " with  " + max + " divisors");
        long end2 = System.currentTimeMillis();
        long result2 = end2 - start2;
        System.out.println("Time for multiple threaded solution in ms: " + result2);

        System.out.println("Multiple threaded solution was " + (result1-result2) + "ms faster");


    }

    public static int countDivisors(int n) {
        int divisors = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                divisors++;
            }
        }
        return divisors;
    }
}
