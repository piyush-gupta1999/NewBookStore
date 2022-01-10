package com.assignment.readingisgood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookResponse implements Response {
    private String status;
    private Object description;

    @Override
    public String toString() {
        return "BookResponse{" +
                "status='" + status + '\'' +
                ", description=" + description +
                '}';
    }
}
