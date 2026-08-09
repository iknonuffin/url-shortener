package org.example.urlshortener.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Base62ServiceTest {

    private final Base62Service base62Service = new Base62Service();

    @Test
    void encode_differentNumbers_returnsCorrectResult() {
        assertEquals("1", base62Service.encode(1));
        assertEquals("A", base62Service.encode(10));
        assertEquals("Z", base62Service.encode(35));
        assertEquals("a", base62Service.encode(36));
        assertEquals("z", base62Service.encode(61));
    }

    @Test
    void encode_largeNumber_returnsCorrectResult() {
        assertEquals("8M0kX", base62Service.encode(123456789));
    }
}
