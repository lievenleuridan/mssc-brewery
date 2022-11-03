package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(customerId)
                .customerName("Drunk Dude")
                .build();
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDTO) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .customerName(customerDTO.getCustomerName())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        // update Customer
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        // delete Customer
    }
}
