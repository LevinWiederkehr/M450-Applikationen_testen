package ch.tbz.m450.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private Address a;

    @BeforeEach
    void setUp() {
        a = new Address(1, "Ali", "Baker", "0790000000", new Date(1_700_000_000_000L));
    }

    @Test
    void ctorAndGetters() {
        assertEquals(1, a.getId());
        assertEquals("Ali", a.getFirstname());
        assertEquals("Baker", a.getLastname());
        assertEquals("0790000000", a.getPhonenumber());
        assertNotNull(a.getRegistrationDate());
    }

    @Test
    void settersWork() {
        a.setLastname("Zeta");
        assertEquals("Zeta", a.getLastname());
    }
}
