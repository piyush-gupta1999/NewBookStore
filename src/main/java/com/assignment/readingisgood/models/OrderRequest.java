package com.assignment.readingisgood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OrderRequest {
    private final String customer_id;
    private final List<BookQuantity> bookList;
}
