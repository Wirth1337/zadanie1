package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        String initialHash = generateRandomHash();
        System.out.println("Initial hash: " + initialHash);

        List<Transaction> transactions = new ArrayList<>();

        String lastHash = initialHash;
        for (int i = 0; i < 5; i++) {
            double amount = 10 + (1000 - 10) * random.nextDouble();
            Transaction transaction = new Transaction(amount, lastHash);
            int nonce = findValidNonce(transaction);
            transaction.setNonce(nonce);

            String currentHash = transaction.getHash();
            transactions.add(transaction);

            System.out.println("Transaction " + (i + 1) + ": " + transaction);
            System.out.println("Generated hash: " + currentHash);
            System.out.println();

            lastHash = currentHash;
        }
    }

    // Funkcja do generowania losowego hasha
    private static String generateRandomHash() {
        byte[] array = new byte[16];
        random.nextBytes(array);
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Algorytm znajdowania odpowiedniego nonce
    private static int findValidNonce(Transaction transaction) {
        int nonce = 0;
        while (true) {
            transaction.setNonce(nonce);
            String hash = transaction.getHash();
            if (hash.endsWith("00000")) {
                return nonce;
            }
            nonce++;
        }
    }
}
