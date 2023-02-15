package be.bankero.bankero.service;

import be.bankero.bankero.model.BankAccount;
import be.bankero.bankero.model.MyUserDetails;
import be.bankero.bankero.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final BankAccountRepository bankAccountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BankAccount fetchedAccount = bankAccountRepo.findByIban(username).orElseThrow(() -> new UsernameNotFoundException("No such iban " + username));
        return new MyUserDetails(fetchedAccount);
    }
}
