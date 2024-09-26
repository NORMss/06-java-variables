package com.eltex;

import java.util.InputMismatchException;
import java.util.Scanner;

enum Currencies {
    RUB("₽"),
    BYN("BYN"),
    KZT("₸");

    private final String currencySymbol;

    Currencies(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrency() {
        return currencySymbol;
    }

    @Override
    public String toString() {
        return currencySymbol;
    }
}

public class Main {

    static Currencies getClientCurrency() {
        final var scanner = new Scanner(System.in);
        System.out.println("Введите валюту: 1 – RUB, 2 – BYN, 3 – KZT");
        try {
            return switch (scanner.nextInt()) {
                case 1 -> Currencies.RUB;
                case 2 -> Currencies.BYN;
                case 3 -> Currencies.KZT;
                default -> throw new IllegalArgumentException("Ошибка: введите число от 1 до 3.");
            };
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Ошибка: введите целое число.");
        }
    }

    static int getClientYearlyPurchases() {
        final var scanner = new Scanner(System.in);
        System.out.println("Введите сумму покупок за прошлый год");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Ошибка: введите целое число.");
        }
    }

    static double calculateTotalDiscount(int clientYearlyPurchases, double discount, int discountStart) {
        if (clientYearlyPurchases >= discountStart) {
            return clientYearlyPurchases * discount;
        } else {
            return 0;
        }
    }

    static void printTotalDiscountOrPromoMessage(double totalDiscount, double discount, Currencies clientCurrency) {
        if (totalDiscount == 0) {
            System.out.printf("Попробуйте нашу новую подписку и сэкономьте %s %%\n", Math.round(discount * 100));
        } else {
            System.out.printf("За прошлый год вы бы сэкономили с подпиской %.2f %s\n", totalDiscount, clientCurrency);
        }
    }

    public static void main(String[] args) {
        final var discount = 0.02;
        final var discountStart = 3_000;

        try {
            final var clientCurrency = getClientCurrency();
            final var clientYearlyPurchases = getClientYearlyPurchases();
            final var totalDiscount = calculateTotalDiscount(clientYearlyPurchases, discount, discountStart);
            printTotalDiscountOrPromoMessage(totalDiscount, discount, clientCurrency);
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
