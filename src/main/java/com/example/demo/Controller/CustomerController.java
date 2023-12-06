package com.example.demo.Controller;

import com.example.demo.Models.Dto.CustomerDto;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

        private CustomerService customerService;

        @Autowired
        public CustomerController(CustomerService customerService){
        this.customerService=customerService;
        }

        @GetMapping
        public List<CustomerDto> getAllCustomers() {
            return customerService.getAllCustomers();
        }

        @GetMapping("/{id}")
        public CustomerDto getCustomerById(@PathVariable Long id) {
            return customerService.getCustomerById(id);
        }

        @PostMapping
        public CustomerDto addCustomer(@RequestBody CustomerDto customerDTO) {
            return customerService.addCustomer(customerDTO);
        }
    }

