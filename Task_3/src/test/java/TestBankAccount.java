import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.params.provider.MethodSource;

import org.junit.jupiter.params.provider.ValueSource;

import student.anatoliy.BankAccount;

import student.anatoliy.IllegalAmountException;



import java.math.BigDecimal;

import java.util.stream.Stream;



public class TestBankAccount {

    @BeforeEach

    public void setUp() {

        System.out.println("\ntest started:");

    }



    @AfterEach

    public void tearDown() {

        System.out.println("test ended");

    }



    @Test

    public void TestSmoke() {

        BankAccount bankAccount = new BankAccount(1L, BigDecimal.ZERO);

        Assertions.assertNotNull(bankAccount);

    }



    @Test

    public void TestDeposit_Positive() {

        BankAccount bankAccount = new BankAccount(2L, BigDecimal.ZERO);

        bankAccount.deposit(BigDecimal.valueOf(1000));



        Assertions.assertEquals(BigDecimal.valueOf(1000), bankAccount.getBalance());

    }



    @Test

    public void TestWithdraw_Positive() {

        BankAccount bankAccount = new BankAccount(3L, BigDecimal.valueOf(1000));

        bankAccount.withdraw(BigDecimal.valueOf(500));



        Assertions.assertEquals(BigDecimal.valueOf(500), bankAccount.getBalance());

    }



    @Test

    public void TestDepositBoundaryOne_Positive() {

        BankAccount bankAccount = new BankAccount(4L, BigDecimal.ZERO);

        Assertions.assertTrue(bankAccount.deposit(BigDecimal.valueOf(1)));

    }



    @Test

    public void TestDepositBoundaryZero_Negative() {

        BankAccount bankAccount = new BankAccount(5L, BigDecimal.ZERO);



        Assertions.assertThrows(IllegalAmountException.class, () -> bankAccount.deposit(BigDecimal.ZERO));

    }



    @Test

    public void TestDepositBoundary_NegativeAmount() {

        BankAccount bankAccount = new BankAccount(6L, BigDecimal.valueOf(1000));



        Assertions.assertThrows(IllegalAmountException.class, () -> bankAccount.deposit(BigDecimal.valueOf(-19)));

    }



    @Test

    public void TestWithdraw_NotEnoughCurrency() {

        BankAccount bankAccount = new BankAccount(7L, BigDecimal.valueOf(400));



        Assertions.assertThrows(IllegalAmountException.class, () -> bankAccount.withdraw(BigDecimal.valueOf(450)));

    }



    @ParameterizedTest

    @ValueSource(strings = {"1", "100", "0.9999999999"})

    void TestTransactions_Positive(BigDecimal amount) {

        BankAccount bankAccount = new BankAccount(7L, BigDecimal.ZERO);

        Assertions.assertTrue(bankAccount.deposit(amount));

        Assertions.assertTrue(bankAccount.withdraw(amount));

    }



    @ParameterizedTest

    @CsvSource({"0", "-60"})

    void TestTransactions_Negative(BigDecimal amount) {

        BankAccount bankAccount = new BankAccount(8L, BigDecimal.ZERO);

        Assertions.assertThrows(IllegalAmountException.class, () -> bankAccount.deposit(amount));

        Assertions.assertThrows(IllegalAmountException.class, () -> bankAccount.withdraw(amount));

    }



    @ParameterizedTest

    @MethodSource("provideBigDecimals")

    void TestTransactionsViaMethodSource_Positive(BigDecimal amount) {

        BankAccount bankAccount = new BankAccount(9L, BigDecimal.valueOf(924_999_999_999L));// Dude is fucking Elon Musk! LMFAO!!! >=<

        Assertions.assertTrue(bankAccount.deposit(amount));

        Assertions.assertTrue(bankAccount.withdraw(amount));

    }



    private static Stream<Arguments> provideBigDecimals() {

        return Stream.of(Arguments.of(BigDecimal.valueOf(0.0000001))/* small steps also counts ;) */, Arguments.of(BigDecimal.valueOf(0.99)), Arguments.of(BigDecimal.valueOf(1)), Arguments.of(BigDecimal.valueOf(1000)), Arguments.of(BigDecimal.valueOf(924_999_999_999L))// LET`S take money from this ugly bastard >=<

        );

    }

//P.S. jokes are jokes, Dude pls, don't take it close to your heart ;) of course if you have one >=<!!

}