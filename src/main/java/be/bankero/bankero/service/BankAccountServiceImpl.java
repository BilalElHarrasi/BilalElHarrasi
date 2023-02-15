package be.bankero.bankero.service;

import be.bankero.bankero.model.BankAccount;
import be.bankero.bankero.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepo;
    private final PasswordEncoder passwordEncoder;
    private final IbanGeneratorService ibanGeneratorService;

    @Override
    public BankAccount registerNewBankAccount(BankAccount bankAccount) {
        bankAccount.setIban(ibanGeneratorService.generateRandomIban());
        if (bankAccountRepo.existsByIban(bankAccount.getIban())){
            throw new EntityExistsException("IBAN already exists " + bankAccount.getIban());
        }
        // Encode password before saving
        bankAccount.setPin(passwordEncoder.encode(bankAccount.getPin()));
        // All new bank accounts start with $0
        bankAccount.setBalance(0);
        return bankAccountRepo.save(bankAccount);
    }

    @Override
    public BankAccount getBankAccountByIban(String iban) {
        return bankAccountRepo.findByIban(iban).orElseThrow(() -> new EntityNotFoundException("IBAN not found " + iban));
    }

    @Override
    @Transactional
    public void changePinByIban(String oldPin, String newPin, String iban) {
        BankAccount account = getBankAccountByIban(iban);
        if (passwordEncoder.matches(oldPin, account.getPin())){
            account.setPin(passwordEncoder.encode(newPin));
            bankAccountRepo.save(account);
        } else {
            throw new IllegalArgumentException("Old pin did not match");
        }
    }
}
