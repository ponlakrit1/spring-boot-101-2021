package com.mfu.demo.controller;

import com.mfu.demo.entity.CustomerEntity;
import com.mfu.demo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody @Validated CustomerEntity item){
        Optional<CustomerEntity> consumptionObj = Optional.ofNullable(customerService.createCustomer(item));
        return ResponseEntity.status(HttpStatus.CREATED).body(consumptionObj);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody @Validated CustomerEntity item){
        Optional<CustomerEntity> consumptionObj = Optional.ofNullable(customerService.updateCustomer(item));
        if(!consumptionObj.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(consumptionObj);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id){
        if(!customerService.deleteCustomer(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/")
    public List<CustomerEntity> findAll(){
        return customerService.findAllCustomer();
    }

    @GetMapping(path = "/find/{customerId}")
    public Optional<CustomerEntity> findById(@PathVariable int customerId){
        return customerService.findById(customerId);
    }

    @GetMapping(path = "/find/name/{customerName}")
    public List<CustomerEntity> findByCustomerName(@PathVariable String customerName){
        return customerService.findByCustomerName(customerName);
    }

    @GetMapping(path = "/find/name/{customerName}/address/{address}")
    public List<CustomerEntity> findByCustomerNameAndAddress(@PathVariable String customerName, @PathVariable String address){
        return customerService.findByCustomerNameAndAddress(customerName, address);
    }

    @GetMapping(path = "/find/flag/{activeFlag}")
    public List<CustomerEntity> findByActiveFlag(@PathVariable String activeFlag){
        return customerService.findByActiveFlag(activeFlag);
    }

    @GetMapping(path = "/find/date/{fromDate}/{toDate}")
    public List<CustomerEntity> findByBirthdateBetween(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
                                                       @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate){
        return customerService.findByBirthdateBetween(fromDate, toDate);
    }

}
