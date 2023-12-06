package com.example.demo.Service;

import com.example.demo.Models.Dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto addCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto, Long id);

    void deleteCustomerId(Long id);

}
