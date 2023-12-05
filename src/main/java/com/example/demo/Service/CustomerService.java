package com.example.demo.Service;

import com.example.demo.Models.Customer;
import com.example.demo.Models.Dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto addCustomer(CustomerDto customerDto);


}
