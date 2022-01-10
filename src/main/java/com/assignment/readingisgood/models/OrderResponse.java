package com.assignment.readingisgood.models;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderResponse implements Response{
    private String status;
    private Object description;

    @Override
    public String toString() {
        return "OrderResponse{" +
                "status='" + status + '\'' +
                ", description=" + description +
                '}';
    }
}
