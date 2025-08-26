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

}