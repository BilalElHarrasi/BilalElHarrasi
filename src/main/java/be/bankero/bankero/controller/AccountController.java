package be.bankero.bankero.controller;

import be.bankero.bankero.dto.BankAccountChangePinDto;
import be.bankero.bankero.model.BankAccount;
import be.bankero.bankero.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final BankAccountService bankAccountService;

    @GetMapping("/account")
    public String fetchAccountPage(Model model, Principal principal) {
        BankAccount loggedInAccount = bankAccountService.getBankAccountByIban(principal.getName());
        model.addAttribute("account", loggedInAccount);
        return "account/account-detail";
    }

    @GetMapping("/account/pin")
    public String fetchChangePinPage(Model model) {
        model.addAttribute("account", new BankAccountChangePinDto());
        return "account/change-pin-form";
    }

    @PostMapping("/account/pin")
    public ModelAndView processNewPin(@Valid @ModelAttribute("account") BankAccountChangePinDto dto, BindingResult br, Principal principal, Model model) {
        if (!br.hasErrors()) {
            try {
                bankAccountService.changePinByIban(dto.getOldPin(), dto.getNewPin(), principal.getName());
                return new ModelAndView("redirect:/account?changePinSuccess");
            } catch (IllegalArgumentException e) {
                br.addError(new ObjectError("oldPin", e.getMessage()));
            }
        }
        return new ModelAndView("account/change-pin-form");

    }
}
