package be.bankero.bankero.controller;

import be.bankero.bankero.model.Transaction;
import be.bankero.bankero.service.BankAccountService;
import be.bankero.bankero.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TransferController {
    private final TransactionService txService;
    private final BankAccountService accountService;

    @GetMapping("/transfer")
    public String fetchNewTransferFormPage(Model model){
        model.addAttribute("tx", new Transaction());
        return "/transfer/transfer-form";
    }
    @PostMapping("/transfer")
    public ModelAndView processNewTransfer(@Valid Transaction tx, BindingResult br, Principal principal){
        if (!br.hasErrors()){
            tx.setSender(accountService.getBankAccountByIban(principal.getName()));
            txService.transferMoney(tx);
            return new ModelAndView("redirect:/account?transferSuccess");
        } else {
            return new ModelAndView("transfer/transfer-form");
        }
    }
}
