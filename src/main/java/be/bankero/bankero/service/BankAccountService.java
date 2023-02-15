package be.bankero.bankero.service;

import be.bankero.bankero.model.BankAccount;

public interface BankAccountService {
    BankAccount registerNewBankAccount(BankAccount bankAccount);
    BankAccount getBankAccountByIban(String iban);
    void changePinByIban(String oldPin, String newPin, String iban);
}
