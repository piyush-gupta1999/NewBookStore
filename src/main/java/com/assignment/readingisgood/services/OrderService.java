package com.assignment.readingisgood.services;

import com.assignment.readingisgood.exceptions.BookNotFound;
import com.assignment.readingisgood.exceptions.InvalidQuantity;
import com.assignment.readingisgood.exceptions.OrderNotFound;
import com.assignment.readingisgood.exceptions.OutOfStockException;
import com.assignment.readingisgood.models.Order;
import com.assignment.readingisgood.models.OrderRequest;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface OrderService {
    @Transactional
    String bookOrder(OrderRequest orderRequest) throws InvalidQuantity, BookNotFound, OutOfStockException;
    Order getOrderById(String order_id) throws OrderNotFound;
    List<Order> getOrderByDate(Date startDate, Date endDate);
    List<Order> getCustomerOrders(String customerId);
}
