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
    // ===== ТЕСТЫ КОНСТРУКТОРА =====
    @Test
    public void shouldCalculateYearChangeCorrectly() {
        SavingAccount account = new SavingAccount(150, 0, 1000, 10);

        int expected = 15; // Ожидаемый результат: 150 * 10% = 15
        int actual = account.yearChange();


        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldThrowExceptionIfRateIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new SavingAccount(1000, 0, 5000, -5));
    }

    @Test
    public void shouldThrowExceptionIfMinBalanceIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new SavingAccount(1000, -100, 5000, 5));
    }

    @Test
    public void shouldThrowExceptionIfMinBalanceGreaterThanMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new SavingAccount(1000, 2000, 1000, 5));
    }

    @Test
    public void shouldThrowExceptionIfInitialBalanceBelowMin() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new SavingAccount(500, 1000, 5000, 5));
    }

    @Test
    public void shouldThrowExceptionIfInitialBalanceAboveMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new SavingAccount(6000, 1000, 5000, 5));
    }

    @Test
    public void shouldCreateAccountWithValidParameters() {
        Assertions.assertDoesNotThrow(() ->
                new SavingAccount(3000, 1000, 5000, 5));
    }

    // ===== ТЕСТЫ МЕТОДА PAY =====

    @Test
    public void shouldPaySuccessfullyWithinLimits() {
        SavingAccount account = new SavingAccount(3000, 1000, 5000, 5);

        boolean result = account.pay(1500);

        Assertions.assertTrue(result);
        Assertions.assertEquals(1500, account.getBalance());
    }

    @Test
    public void shouldNotPayIfAmountNegative() {
        SavingAccount account = new SavingAccount(3000, 1000, 5000, 5);

        boolean result = account.pay(-500);

        Assertions.assertFalse(result);
        Assertions.assertEquals(3000, account.getBalance());
    }

    @Test
    public void shouldNotPayIfAmountZero() {
        SavingAccount account = new SavingAccount(3000, 1000, 5000, 5);

        boolean result = account.pay(0);

        Assertions.assertFalse(result);
        Assertions.assertEquals(3000, account.getBalance());
    }

    @Test
    public void shouldNotPayIfBalanceWouldGoBelowMin() {
        SavingAccount account = new SavingAccount(1500, 1000, 5000, 5);

        boolean result = account.pay(600);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1500, account.getBalance());
    }

    @Test
    public void shouldPayExactToMinBalance() {
        SavingAccount account = new SavingAccount(1500, 1000, 5000, 5);

        boolean result = account.pay(500);

        Assertions.assertTrue(result);
        Assertions.assertEquals(1000, account.getBalance());
    }

    // ===== ТЕСТЫ МЕТОДА ADD =====

    @Test
    public void shouldAddSuccessfullyWithinLimits() {
        SavingAccount account = new SavingAccount(2000, 1000, 5000, 5);

        boolean result = account.add(2000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(4000, account.getBalance());
    }

    @Test
    public void shouldNotAddIfAmountNegative() {
        SavingAccount account = new SavingAccount(2000, 1000, 5000, 5);

        boolean result = account.add(-500);

        Assertions.assertFalse(result);
        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void shouldNotAddIfAmountZero() {
        SavingAccount account = new SavingAccount(2000, 1000, 5000, 5);

        boolean result = account.add(0);

        Assertions.assertFalse(result);
        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void shouldNotAddIfBalanceWouldExceedMax() {
        SavingAccount account = new SavingAccount(4500, 1000, 5000, 5);

        boolean result = account.add(600);

        Assertions.assertFalse(result);
        Assertions.assertEquals(4500, account.getBalance());
    }

    @Test
    public void shouldAddExactToMaxBalance() {
        SavingAccount account = new SavingAccount(4500, 1000, 5000, 5);

        boolean result = account.add(500);

        Assertions.assertTrue(result);
        Assertions.assertEquals(5000, account.getBalance());
    }

    // ===== ТЕСТЫ МЕТОДА YEARCHANGE =====



    @Test
    public void shouldReturnZeroForZeroBalance() {
        SavingAccount account = new SavingAccount(0, 0, 1000, 15);

        int result = account.yearChange();

        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldReturnZeroForZeroRate() {
        SavingAccount account = new SavingAccount(500, 0, 1000, 0);

        int result = account.yearChange();

        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldCalculateCorrectlyWithLargeNumbers() {
        SavingAccount account = new SavingAccount(10000, 0, 20000, 10);

        int result = account.yearChange();

        Assertions.assertEquals(1000, result); // 10000 * 10% = 1000
    }

    @Test
    public void shouldCalculateCorrectlyWithMinBalance() {
        SavingAccount account = new SavingAccount(100, 100, 1000, 20);

        int result = account.yearChange();

        Assertions.assertEquals(20, result); // 100 * 20% = 20
    }

    // ===== ТЕСТЫ ГЕТТЕРОВ =====

    @Test
    public void shouldReturnCorrectMinBalance() {
        SavingAccount account = new SavingAccount(1500, 1000, 5000, 5);

        Assertions.assertEquals(1000, account.getMinBalance());
    }

    @Test
    public void shouldReturnCorrectMaxBalance() {
        SavingAccount account = new SavingAccount(1500, 1000, 5000, 5);

        Assertions.assertEquals(5000, account.getMaxBalance());
    }

    @Test
    public void shouldReturnCorrectRate() {
        SavingAccount account = new SavingAccount(1500, 1000, 5000, 5);

        Assertions.assertEquals(5, account.getRate());
    }

    @Test
    public void shouldReturnCorrectBalance() {
        SavingAccount account = new SavingAccount(1500, 1000, 5000, 5);

        Assertions.assertEquals(1500, account.getBalance());
    }

    // ===== ТЕСТЫ ГРАНИЧНЫХ ЗНАЧЕНИЙ =====

    @Test
    public void shouldWorkWithEqualMinMaxBalance() {
        SavingAccount account = new SavingAccount(2500, 2500, 2500, 5);

        Assertions.assertFalse(account.pay(0)); // Оплата 0 должна возвращать false
        Assertions.assertFalse(account.add(0)); // Пополнение 0 должно возвращать false
        Assertions.assertEquals(2500, account.getBalance()); // Баланс не должен измениться
    }

    @Test
    public void shouldHandleMinimumValues() {
        SavingAccount account = new SavingAccount(0, 0, 1, 0);

        Assertions.assertTrue(account.add(1));
        Assertions.assertEquals(1, account.getBalance());
    }
}
