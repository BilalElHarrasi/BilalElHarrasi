package be.bankero.bankero.controller;

import be.bankero.bankero.dto.BankAccountRegisterDto;
import be.bankero.bankero.model.BankAccount;
import be.bankero.bankero.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final BankAccountService bankAccountService;

    @GetMapping("/login")
    public String fetchLoginPage() {
        return "auth/login-form";
    } //haha

    @GetMapping("/register")
    public String fetchRegisterPage(Model model) {
        model.addAttribute("bankAccount", new BankAccountRegisterDto());
        return "auth/register-form";
    }

    @PostMapping("/register")
    public ModelAndView fetchRegisterPage(@Valid BankAccountRegisterDto bankAccountRegisterDto, BindingResult br) {
        if (!br.hasErrors()) {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setPin(bankAccountRegisterDto.getPin());
            bankAccount.setHolderName(bankAccountRegisterDto.getHolderName());
            bankAccount.setEmail(bankAccountRegisterDto.getEmail());
            bankAccountService.registerNewBankAccount(bankAccount);
            return new ModelAndView("redirect:/login?register");
        }
        return new ModelAndView("auth/register-form");

    }
}
