package com.example.demo.Service.Impl;

import com.example.demo.Models.Customer;
import com.example.demo.Models.Dto.CustomerDto;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Service.CustomerService;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    @Override
    public List<CustomerDto> getAllCustomers() {

        List<Customer> customers=customerRepository.findAll();
        return customers.stream()
                .map(this::mapCustomerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if (optionalCustomer.isPresent()){
            return mapCustomerToCustomerDTO(optionalCustomer.get());
        }
        else{
            throw new NotFoundException("Customer not found with id: " + id);
        }
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer=mapCustomerDTOToCustomer(customerDto);
        return mapCustomerToCustomerDTO(customerRepository.save(customer));
    }


    private Customer mapCustomerDTOToCustomer(CustomerDto customerDto){

        Customer customer=new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());

        return customer;
    }

    private CustomerDto mapCustomerToCustomerDTO(Customer customer){

        CustomerDto customerDto=new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setName(customer.getName());
        return customerDto;
    }






}
