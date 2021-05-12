package com.mfu.demo.controller;

import com.mfu.demo.entity.StatementEntity;
import com.mfu.demo.service.StatementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("statement")
public class StatementController {

    @Autowired
    StatementService statementService;

    @PostMapping
    public ResponseEntity<?> createStatement(@RequestBody @Validated StatementEntity item){
        Optional<StatementEntity> statementObj = Optional.ofNullable(statementService.createStatement(item));
        return ResponseEntity.status(HttpStatus.CREATED).body(statementObj);
    }

    @PutMapping
    public ResponseEntity<?> updateStatement(@RequestBody @Validated StatementEntity item){
        Optional<StatementEntity> statementObj = Optional.ofNullable(statementService.updateStatement(item));
        if(!statementObj.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statementObj);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteStatement(@PathVariable int id){
        if(!statementService.deleteStatement(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/")
    public List<StatementEntity> findAll(){
        return statementService.findAllStatement();
    }

    @GetMapping(path = "/find/type/{statementType}")
    public List<StatementEntity> findByActiveFlag(@PathVariable String statementType){
        return statementService.findByStatementType(statementType);
    }

}
