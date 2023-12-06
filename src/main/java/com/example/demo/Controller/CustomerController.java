package com.example.demo.Controller;

import com.example.demo.Models.Dto.CustomerDto;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        @PutMapping("/{id}/update")
        public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable("id") Long id) {
            CustomerDto response = customerService.updateCustomer(customerDto, id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        @DeleteMapping("/{id}/delete")
        public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
            customerService.deleteCustomerId(id);
            return new ResponseEntity<>("Customer deleted", HttpStatus.OK);
        }

}

