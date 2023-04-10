package com.h2;

import java.text.DecimalFormat;

public class MortgageCalculator {
    private final long loanAmount;
    private final int termInYears;
    private final float annualRate;
    private double monthlyPayment;

    public MortgageCalculator(long loanAmount, int termInYears, float annualRate) {
        this.loanAmount = loanAmount;
        this.termInYears = termInYears;
        this.annualRate = annualRate;
    }

    private int getNumberOfPayment() {
        return termInYears * 12;
    }

    public float getMonthlyInterestRate() {
        float interestRate = annualRate / 100;
        return interestRate / 12;
    }

    public void calculateMonthlyPayment() {
        float r = getMonthlyInterestRate();
        int n = getNumberOfPayment();

        this.monthlyPayment = loanAmount * (((r * Math.pow(1 + r, n))) / ((Math.pow((1 + r), n)) - 1));
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");
        return "monthlyPayment: " + decimalFormat.format(monthlyPayment);
    }

    public static void main(String[] args) {
        long loanAmount = Utilities.getLongValue(args[0]);
        int termInYears = Utilities.getIntValue(args[1]);
        float annualRate = Utilities.getFloatValue(args[2]);

        MortgageCalculator calculator = new MortgageCalculator(loanAmount, termInYears, annualRate);
        calculator.calculateMonthlyPayment();
        System.out.println(calculator.toString());
    }
