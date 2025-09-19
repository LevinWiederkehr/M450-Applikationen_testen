package ch.tbz.m450.service;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    AddressRepository repo;

    @InjectMocks
    AddressService service;

    Address demo;

    @BeforeEach
    void init() {
        demo = new Address(1, "Ali", "Baker", "079", new Date(1_700_000_000_000L));
    }

    @Test
    void getAllDelegatesToRepo() {
        when(repo.findAll()).thenReturn(List.of(demo));
        assertEquals(1, service.getAll().size());
        verify(repo).findAll();
    }

    @Test
    void getAddressById() {
        when(repo.findById(1)).thenReturn(Optional.of(demo));
        assertTrue(service.getAddress(1).isPresent());
        verify(repo).findById(1);
    }

    @Test
    void createSaves() {
        when(repo.save(demo)).thenReturn(demo);
        assertEquals(demo, service.save(demo));
        verify(repo).save(demo);

    }
}
