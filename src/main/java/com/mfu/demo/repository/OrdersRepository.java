package com.mfu.demo.repository;

import com.mfu.demo.entity.OrdersEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<OrdersEntity,Integer> {



}
