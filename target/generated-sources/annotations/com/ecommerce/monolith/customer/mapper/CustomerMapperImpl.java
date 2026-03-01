package com.ecommerce.monolith.customer.mapper;

import com.ecommerce.monolith.customer.dto.CreateCustomerRequest;
import com.ecommerce.monolith.customer.dto.CustomerDTO;
import com.ecommerce.monolith.customer.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-01T22:27:08+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDto(Customer c) {
        if ( c == null ) {
            return null;
        }

        Long id = null;
        String fullName = null;
        String email = null;

        CustomerDTO customerDTO = new CustomerDTO( id, fullName, email );

        return customerDTO;
    }

    @Override
    public Customer fromCreate(CreateCustomerRequest req) {
        if ( req == null ) {
            return null;
        }

        Customer customer = new Customer();

        return customer;
    }
}
