package com.example.demo.Service.Impl;

import com.example.demo.Models.Customer;
import com.example.demo.Models.Dto.CustomerDto;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Service.CustomerService;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
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

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long id) {

        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer could be not updated"));

        customer.setName(customerDto.getName());

        Customer updateCustomer = customerRepository.save(customer);

        return mapCustomerToCustomerDTO(updateCustomer);
    }

    @Override
    public void deleteCustomerId(Long id) {

        Customer customer = customerRepository.findById(id).orElseThrow(() ->new NotFoundException("Customer could be not delete"));

        customerRepository.delete(customer);
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
