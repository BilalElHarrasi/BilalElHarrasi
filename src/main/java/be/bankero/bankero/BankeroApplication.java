package be.bankero.bankero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // @Configuration + @ComponentScan + @AutoConfiguration
public class BankeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankeroApplication.class, args);
    }

}
