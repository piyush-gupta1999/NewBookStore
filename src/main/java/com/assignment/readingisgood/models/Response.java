package com.assignment.readingisgood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response {
    private String status;
    private Object description;
}