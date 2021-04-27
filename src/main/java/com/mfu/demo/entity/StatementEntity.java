package com.mfu.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "statement")
public class StatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="statement_id")
    private int statementId;

    @Column(name="statement_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Bangkok")
    private Date statementDate;

    @Column(name="statement_type")
    private String statementType;

    @Column(name="statement_amount")
    private double statementAmount;

    @Column(name="statement_desc")
    private String statementDesc;

    public StatementEntity(){

    }

}
