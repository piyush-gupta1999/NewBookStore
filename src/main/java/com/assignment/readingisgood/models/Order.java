package com.assignment.readingisgood.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    private String id;
    private String customer_id;
    private Date order_date;
    private Integer book_count;
    private double price;

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", order_date=" + order_date +
                ", book_count=" + book_count +
                ", price=" + price +
                '}';
    }
}
