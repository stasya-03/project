package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }
    @Test
    public void shouldCalculateYearChangeCorrectly() {
        SavingAccount account = new SavingAccount(150, 0, 1000, 10);

        int expected = 15; // Ожидаемый результат: 150 * 10% = 15
        int actual = account.yearChange();


        Assertions.assertEquals(expected, actual);

    }
    @Test
    public void testPayWhenBalanceWouldBecomeLessThanMinBalance() {
        int initialBalance = 5000;
        int minBalance = 1000;
        int maxBalance = 10000;
        int rate = 10;

        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        // Пытаемся списать сумму, которая приведёт к балансу ниже минимального
        int paymentAmount = 4500;


        boolean result = account.pay(paymentAmount);

        // Проверяем, что операция возвращает false
        Assertions.assertFalse(result);

        // Проверяем, что баланс НЕ изменился
        Assertions.assertEquals(initialBalance, account.getBalance());
    }

}