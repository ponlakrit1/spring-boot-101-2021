package com.mfu.demo.repository;

import com.mfu.demo.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity,Integer> {

    public List<CustomerEntity> findByCustomerName(String customerName);

    public List<CustomerEntity> findByCustomerNameAndAddress(String customerName, String address);

    @Query(	value = "select * from customer c where c.active_flag = ?1 order by c.customer_name ", nativeQuery= true)
    public List<CustomerEntity> findByActiveFlag(String activeFlag);

    public List<CustomerEntity> findByBirthdateBetween(Date fromDate, Date toDate);

}
