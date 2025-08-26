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
}


