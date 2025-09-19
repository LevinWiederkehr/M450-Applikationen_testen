package ch.tbz.m450.controller;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressController.class)
class AddressControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    AddressService service;

    @Test
    void getAllReturns200() throws Exception {
        when(service.getAll()).thenReturn(List.of(new Address(1,"Ali","Baker","079", new Date(1_700_000_000_000L))));
        mvc.perform(get("/address").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value("Ali"));
    }

    @Test
    void getByIdFoundReturns200() throws Exception {
        when(service.getAddress(1)).thenReturn(Optional.of(new Address(1,"Ali","Baker","079", new Date(1_700_000_000_000L))));
        mvc.perform(get("/address/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastname").value("Baker"));
    }

    @Test
    void getByIdNotFoundReturns404() throws Exception {
        when(service.getAddress(99)).thenReturn(Optional.empty());
        mvc.perform(get("/address/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
