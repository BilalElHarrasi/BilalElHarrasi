package be.bankero.bankero.service;

import java.util.Random;
import java.util.function.Supplier;

public enum CountrySpecificIbanAlgorithm {
    BE(() -> {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append(generateRandomDigit());
        }
        return sb.toString();
    }),
    FR(() -> {
        StringBuilder sb = new StringBuilder();
        final String nationalCode = "06";
        for (int i = 0; i < 21; i++) {
            sb.append(generateRandomDigit());
        }
        sb.append(nationalCode);
        return sb.toString();
    });

    // ==========================================

    Supplier<String> ibanFormattingAlgorithm;
    private static final Random RNG = new Random();

    CountrySpecificIbanAlgorithm(Supplier<String> ibanFormattingAlgorithm) {
        this.ibanFormattingAlgorithm = ibanFormattingAlgorithm;
    }

    public String generateIban() {
        return name() + generateCheckDigits() + ibanFormattingAlgorithm.get();
    }

    private static int generateRandomDigit() {
        return RNG.nextInt(10);
    }

    private static String generateCheckDigits(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            sb.append(generateRandomDigit());
        }
        return sb.toString();
    }
}
