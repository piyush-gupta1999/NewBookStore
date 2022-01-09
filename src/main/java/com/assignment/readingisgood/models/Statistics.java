package com.assignment.readingisgood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Statistics {
    @Id
    private String month;
    private Integer orderCount;
    private Integer bookCount;
    private Double purchaseAmount;
}
