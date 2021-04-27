package com.mfu.demo.repository;

import com.mfu.demo.entity.StatementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends CrudRepository<StatementEntity,Integer> {



}
