package be.bankero.bankero.kickstart;

import be.bankero.bankero.model.BankAccount;
import be.bankero.bankero.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("kickstart")
@RequiredArgsConstructor
public class Kickstart {
    private final BankAccountService bankAccountService;

    @PostConstruct
    public void init(){
        System.out.println("Kickstarting initial fake data...");
        kickstartBankAccounts();
        System.out.println("Finished kickstarting!");
    }

    private void kickstartBankAccounts() {
        BankAccount account = new BankAccount();
        account.setIban("BE1");
        account.setPin("1234");
        account.setEmail("james.barnes@gmail.com");
        account.setHolderName("James Barnes");
        bankAccountService.registerNewBankAccount(account);
    }
}
