package be.bankero.bankero.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class BankAccountRegisterDto {
    @NotBlank
    private String holderName;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "Pin code must only be numbers")
    @Length(min = 4, max = 4, message = "Pin code must be 4 digits")
    private String pin;
    @NotBlank
    @Email
    private String email;
    @Min(0)
    private double balance;
}
