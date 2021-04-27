package com.mfu.demo.service;

import com.mfu.demo.entity.StatementEntity;
import com.mfu.demo.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatementService {

    @Autowired
    StatementRepository statementRepository;

    public StatementEntity createStatement(StatementEntity item){
        try {
            return statementRepository.save(item);
        } catch (Exception e) {
            throw e;
        }
    }

    public StatementEntity updateStatement(StatementEntity item) {
        try {
            Optional<StatementEntity> statementEntityOptional = statementRepository.findById(item.getStatementId());
            if(statementEntityOptional.isPresent()){
                return statementRepository.save(item);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean deleteStatement(int customerId){
        try {
            statementRepository.deleteById(customerId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public List<StatementEntity> findAllStatement(){
        return (List<StatementEntity>) statementRepository.findAll();
    }

}
