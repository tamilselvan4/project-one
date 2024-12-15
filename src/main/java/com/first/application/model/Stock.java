package com.first.application.model;

public class Stock {
    private String name;
    private String symbol;
    private double currentPrice;
    private double todayReturnPrice;
    private double todayReturnPercent;

    public Stock(String name, String symbol, double currentPrice, double todayReturnPrice, double todayReturnPercent) {
        this.name = name;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.todayReturnPrice = todayReturnPrice;
        this.todayReturnPercent = todayReturnPercent;
    }

    // Getters
    public String getName() { return name; }
    public String getSymbol() { return symbol; }
    public double getCurrentPrice() { return currentPrice; }
    public double getTodayReturnPrice() { return todayReturnPrice; }
    public double getTodayReturnPercent() { return todayReturnPercent; }

    // Determine if return is positive or negative
    public boolean isPositive() {
        return todayReturnPrice >= 0;
    }
}

