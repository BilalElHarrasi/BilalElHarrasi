package be.bankero.bankero.service;

import be.bankero.bankero.model.Transaction;

public interface TransactionService {
    Transaction transferMoney(Transaction tx);
    Transaction transferMoney(String senderIban, String recipientIban, double amount);
}
