package org.example.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public class Base62Service {

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String encode(long number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number cannot be negative");
        }

        StringBuilder result = new StringBuilder();

        while (number > 0) {
            int remainder = (int) number % 62;

            result.append(ALPHABET.charAt(remainder));

            number /= 62;
        }

        return result.reverse().toString();
    }
}
