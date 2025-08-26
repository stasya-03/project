package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTest {
    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        assertEquals(3_000, account.getBalance());
    }

    // выбрасывает исключение при отрицательной начальном балансе
    @Test
    public void shouldThrowExceptionIfInitialBalanceIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(-300, 7000, 15));
    }

    // выбрасывает исключение при отрицательной максимальной сумме, которую можно задолжать банку
    @Test
    public void shouldThrowExceptionIfCreditLimitIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(300, -7000, 15));
    }

    // выбрасывает исключение при отрицательной ставке
    @Test
    public void shouldThrowExceptionIfRateIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(300, 7000, -15));
    }

    // нулевая ставка
    @Test
    public void shouldTakeZeroRate() {

        CreditAccount account = new CreditAccount(300, 7000, 0);

        int expected = 0;
        int actual = account.getRate();

        Assertions.assertEquals(expected, actual);
    }


    // тесты для PAY
    // успешная оплата из положительного баланса
    @Test
    public void shouldPayIfEnoughBalance() {
        CreditAccount account = new CreditAccount(500, 7000, 15);

        boolean result = account.pay(100);

        int expected = 400;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // успешная оплата с использованием кредита в пределах лимита
    @Test
    void shouldPayUsingCreditIfWithinLimit() {
        CreditAccount account = new CreditAccount(200, 500, 15);

        boolean result = account.pay(600);

        assertTrue(result);
        int expected = -400;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // невозможность уйти за пределы лимита
    @Test
    void shouldNotPayIfExceedsCreditLimit() {
        CreditAccount account = new CreditAccount(200, 500, 15);

        boolean result = account.pay(800); // станет -600 → нельзя

        assertFalse(result);

        int expected = 200;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    //отказ при нуле или отрицательной сумме
    @Test
    void shouldNotPayIfAmountIsZeroOrNegative() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        assertFalse(account.pay(0));
        assertFalse(account.pay(-100));

        int expected = 1000;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }


    // тесты для ADD
    // корректное пополнение
    @Test
    void shouldAddMoneyToBalance() {
        CreditAccount account = new CreditAccount(500, 5000, 15);

        boolean result = account.add(300);

        assertTrue(result);
        int expected = 800;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

    // отказ при нуле или отрицательном значении
    @Test
    void shouldNotAddIfAmountIsZeroOrNegative() {
        CreditAccount account = new CreditAccount(500, 5000, 15);

        assertFalse(account.add(0));
        assertFalse(account.add(-200));

        int expected = 500;
        int actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
    }

}


