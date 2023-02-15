package be.bankero.bankero.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private BankAccount sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private BankAccount recipient;
    @Min(0)
    private double amount;
    private LocalDateTime timestamp;
}
