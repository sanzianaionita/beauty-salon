package com.example.beautysalon.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "POSITION")
public class Position {

    @Id
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "FUNCTION_NAME")
    private String functionName;

    @Column(name = "FUNCTION_DESCRIPTION")
    private String functionDescription;

    @Column(name = "COR_CODE")
    private Integer corCode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    public Integer getCorCode() {
        return corCode;
    }

    public void setCorCode(Integer corCode) {
        this.corCode = corCode;
    }
}
