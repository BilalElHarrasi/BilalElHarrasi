package be.bankero.bankero.controller;

import be.bankero.bankero.model.BankAccount;
import be.bankero.bankero.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller // specialisatie van @Component
@RequiredArgsConstructor
public class HomeController {
    private final BankAccountService bankAccountService;

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/cv")
    public String cvPage(){
        return "php-redirect/redirect.php";
    }
}
