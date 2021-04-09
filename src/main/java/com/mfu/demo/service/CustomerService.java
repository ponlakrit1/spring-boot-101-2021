package com.mfu.demo.service;

import com.mfu.demo.entity.CustomerEntity;
import com.mfu.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerEntity createCustomer(CustomerEntity item){
        try {
            return customerRepository.save(item);
        } catch (Exception e) {
            throw e;
        }
    }

    public CustomerEntity updateCustomer(CustomerEntity item) {
        try {
            Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(item.getCustomerId());
            if(customerEntityOptional.isPresent()){
                return customerRepository.save(item);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean deleteCustomer(int customerId){
        try {
            customerRepository.deleteById(customerId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public List<CustomerEntity> findAllCustomer(){
        return (List<CustomerEntity>) customerRepository.findAll();
    }

    public Optional<CustomerEntity> findById(int customerId){
        return customerRepository.findById(customerId);
    }

    public List<CustomerEntity> findByCustomerName(String customerName){
        return customerRepository.findByCustomerName(customerName);
    }

    public List<CustomerEntity> findByCustomerNameAndAddress(String customerName, String address){
        return customerRepository.findByCustomerNameAndAddress(customerName, address);
    }

    public List<CustomerEntity> findByActiveFlag(String activeFlag){
        return customerRepository.findByActiveFlag(activeFlag);
    }

    public List<CustomerEntity> findByBirthdateBetween(Date fromDate, Date toDate){
        return customerRepository.findByBirthdateBetween(fromDate, toDate);
    }

}
