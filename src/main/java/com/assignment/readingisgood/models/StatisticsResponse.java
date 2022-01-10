package com.assignment.readingisgood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatisticsResponse implements Response{
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
