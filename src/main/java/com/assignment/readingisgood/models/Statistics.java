package com.assignment.readingisgood.models;

import com.sun.istack.NotNull;
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
    @NotNull
    @Id
    private Integer month_no;
    private Integer count;
    private Integer book_count;
    private Double price;
}
