package be.bankero.bankero.service;

import be.bankero.bankero.model.BankAccount;
import be.bankero.bankero.model.Transaction;
import be.bankero.bankero.repository.BankAccountRepository;
import be.bankero.bankero.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository txRepo;
    private final BankAccountService accountService;

    @Override
    @Transactional
    public Transaction transferMoney(Transaction tx) {
        BankAccount fetchedSender = accountService.getBankAccountByIban(tx.getSender().getIban());
        BankAccount fetchedRecipient = accountService.getBankAccountByIban(tx.getRecipient().getIban());

        fetchedSender.withdraw(tx.getAmount());
        fetchedRecipient.deposit(tx.getAmount());

        tx.setSender(fetchedSender);
        tx.setRecipient(fetchedRecipient);
        tx.setTimestamp(LocalDateTime.now());
        return txRepo.save(tx);
    }

    @Override
    @Transactional
    public Transaction transferMoney(String senderIban, String recipientIban, double amount) {
        BankAccount fetchedSender = accountService.getBankAccountByIban(senderIban);
        BankAccount fetchedRecipient = accountService.getBankAccountByIban(recipientIban);

        Transaction tx = new Transaction();
        tx.setSender(fetchedSender);
        tx.setRecipient(fetchedRecipient);
        tx.setAmount(amount);
        return transferMoney(tx);
    }
}
