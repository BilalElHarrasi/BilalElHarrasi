package be.bankero.bankero.controller;

import be.bankero.bankero.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ExchangeController {
    private final BankAccountService bankAccountService;

    @GetMapping("/exchange")
    public String fetchExchangePage() {
        return "exchange/exchange";
    }



}
