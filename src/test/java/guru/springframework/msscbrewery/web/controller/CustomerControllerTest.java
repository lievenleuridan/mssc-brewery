package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCustomerById() throws Exception {
        mockMvc.perform(get("/api/v1/customer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void saveCustomer() throws Exception {
        CustomerDto customerDto = CustomerDto.builder().customerName("New drunk guy").build();
        String customerDtoToJson = objectMapper.writeValueAsString(customerDto);

        when(customerService.saveCustomer(any(CustomerDto.class))).thenReturn(customerDto);

        mockMvc.perform(post("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoToJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCustomer() throws Exception {
        CustomerDto customerDto = CustomerDto.builder().customerName("Existing drunk guy").build();
        String customerDtoToJson = objectMapper.writeValueAsString(customerDto);

        when(customerService.saveCustomer(any(CustomerDto.class))).thenReturn(customerDto);

        mockMvc.perform(put("/api/v1/customer/"+UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoToJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/api/v1/customer/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
    }
}