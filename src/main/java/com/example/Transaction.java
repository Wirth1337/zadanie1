package com.example;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Transaction {
    private double amount;
    private String lastTransaction;
    private int nonce;

    public Transaction(double amount, String lastTransaction) {
        this.amount = amount;
        this.lastTransaction = lastTransaction;
        this.nonce = 0;
    }

    // Gettery i settery
    public double getAmount() { return amount; }
    public String getLastTransaction() { return lastTransaction; }
    public int getNonce() { return nonce; }
    public void setNonce(int nonce) { this.nonce = nonce; }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", lastTransaction='" + lastTransaction + '\'' +
                ", nonce=" + nonce +
                '}';
    }

    // Metoda do hashowania obiektu
    public String getHash() {
        String data = this.toString();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(data.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
