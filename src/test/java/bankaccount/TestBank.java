package bankaccount;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bankaccount.BankAccount;

public class TestBank {
    BankAccount banco;

    @BeforeEach
    void setUp(){
        banco = new BankAccount(100);
    }

    @Test
    @DisplayName("Withdrawing less money than the balance")
    void withdraw_MoneyLessThanBalance_ReturnTrue() {
        int amount = 50;
        
        assert(banco.withdraw(amount));
    }

    @Test
    @DisplayName("Withdrawing more money than the balance")
    void withdraw_MoneyMoreThanBalance_ReturnFalse() {
        int amount = 110;
        
        assertFalse(banco.withdraw(amount));
    }

    @Test
    @DisplayName("Withdrawing a negative amount")
    void withdraw_NegativeAmount_ThrowsIllegalArgumentException(){
        int amount = -10;
        //He modificado el código original para que tenga control de errores
        assertThrows(IllegalArgumentException.class, () -> banco.withdraw(amount));
    }

    @Test
    @DisplayName("Deposit of a postive amount")
    void deposit_PositiveAmount_ReturnBalancePlusAmount(){
        int amount = 50;
        int initialBalance = banco.getBalance();

        banco.deposit(amount);

        assertEquals(banco.getBalance(), initialBalance+amount);
    }

    @Test
    @DisplayName("Deposit of a negative amount")
    void deposit_NegativeAmount_ThrowsIllegalArgumentException(){
        int amount = -10;
        
        assertThrows(IllegalArgumentException.class, () -> banco.deposit(amount));
    }

    @Test
    @DisplayName("Testing the getBalance()")
    void getBalance_Return100(){
        int amount;

        amount = banco.getBalance();

        assertEquals(amount, 100);
    }

    @Test
    @DisplayName("Testing the payment()")
    void payment_ReturnCorrectValue(){
        double amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        double expected = 838.7599255697181;

        double result = banco.payment(amount, interest, npayments);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Testing the payment for negative values")
    void payment_NegativeValues_ThrowsIllegalArgumentException(){
        double amount = -10000;
        double interest = -0.001;
        int npayments = -12;
        //He modificado el código original para que tenga control de errores
        assertThrows(IllegalArgumentException.class, () -> banco.payment(amount, interest, npayments));
    }

    @Test
    @DisplayName("Testing the pending() for 0 months")
    void pending_For0Months_ReturnAmount(){
        double amount = 100;
        double interest = 0.2;
        int npayments = 12;
        int month = 0;

        double result = banco.pending(amount, interest, npayments, month);

        assertEquals(amount, result);
    }

    @Test
    @DisplayName("Testing the pending() for more than 0 months")
    void pending_ForMoreThan0Months_ReturnCorrectValue(){
        double amount = 10000;
        double interest = 0.001;
        int npayments = 12;
        int month = 2;
        double expected = 8341.651388934994;

        double result = banco.pending(amount, interest, npayments, month);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Testing the pending() for negative values")
    void pending_NegativeValues_ThrowsIllegalArgumentException(){
        double amount = -10000;
        double interest = -0.001;
        int npayments = -12;
        int month = -2;
        //He modificado el código original para que tenga control de errores
        assertThrows(IllegalArgumentException.class, () -> banco.pending(amount, interest, npayments, month));
    }
}
