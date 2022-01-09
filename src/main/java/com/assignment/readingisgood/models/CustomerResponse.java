package com.assignment.readingisgood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerResponse{
    private String status;
    private Object description;

    @Override
    public String toString() {
        return "CustomerResponse{" +
                "status='" + status + '\'' +
                ", description=" + description +
                '}';
    }
}
