package be.bankero.bankero.service;

import be.bankero.bankero.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IbanGeneratorService {
    private final BankAccountRepository accountRepo;

    public String generateRandomIban(){
        return generateRandomIban(CountrySpecificIbanAlgorithm.BE);
    }
    public String generateRandomIban(CountrySpecificIbanAlgorithm countrySpecificIbanAlgorithm){
        String iban;
        do {
            iban = countrySpecificIbanAlgorithm.generateIban();
        } while (accountRepo.existsByIban(iban));
        return iban;
    }
}
