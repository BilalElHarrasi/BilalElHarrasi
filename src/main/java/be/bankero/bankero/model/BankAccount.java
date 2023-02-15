package be.bankero.bankero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String iban;
    @Column(nullable = false)
    private String pin;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private double balance;
    @Column(name = "holder_name", nullable = false)
    private String holderName;
    @OneToMany(mappedBy = "recipient")
    private List<Transaction> incomingTransactions = new ArrayList<>();
    @OneToMany(mappedBy = "sender")
    private List<Transaction> outgoingTransactions = new ArrayList<>();

    public List<Transaction> getAllTransactions(){
        return Stream.concat(incomingTransactions.parallelStream(), outgoingTransactions.parallelStream())
                .sorted(Comparator.comparing(Transaction::getTimestamp).reversed())
                .collect(Collectors.toList());
    }

    public void deposit(double amount){
        if (amount <= 0){
            throw new IllegalArgumentException("Deposit amount cannot be negative or zero");
        }
        balance += amount;
    }

    public void withdraw(double amount){
        if (amount <= 0){
            throw new IllegalArgumentException("Withdraw amount cannot be negative or zero");
        }
        if (amount > balance){
            throw new IllegalArgumentException("Withdraw amount cannot be higher than balance");
        }
        balance -= amount;
    }
}
