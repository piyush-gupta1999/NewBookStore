package com.assignment.readingisgood.controllers;

import com.assignment.readingisgood.exceptions.BookNotFound;
import com.assignment.readingisgood.exceptions.OrderNotFound;
import com.assignment.readingisgood.exceptions.OutOfStockException;
import com.assignment.readingisgood.models.Order;
import com.assignment.readingisgood.models.OrderRequest;
import com.assignment.readingisgood.models.OrderResponse;
import com.assignment.readingisgood.models.Response;
import com.assignment.readingisgood.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders/book",method = RequestMethod.POST)
    @ResponseBody
    public Response createBooking(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse;
        try{
            orderResponse = new OrderResponse("Success",orderService.bookOrder(orderRequest));
        } catch (Exception exception) {
            orderResponse = new OrderResponse("Fail",exception.getMessage());
        }
        return orderResponse;
    }

    @RequestMapping(value = "/orders/{orderId}",method = RequestMethod.GET)
    @ResponseBody
    public Response getOrderById(@PathVariable String orderId) {
        OrderResponse orderResponse;
        try{
            Order order = orderService.getOrderById(orderId);
            orderResponse = new OrderResponse("Success",order.toString());
        } catch (OrderNotFound exception) {
            orderResponse = new OrderResponse("Fail",exception.getMessage());
        }
        return orderResponse;
    }

    @RequestMapping(value = "/orders/{sDate}/{eDate}",method = RequestMethod.GET)
    @ResponseBody
    public Response getOrderByDate(@PathVariable String sDate, @PathVariable String eDate) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        Date endDate;
        OrderResponse orderResponse;
        try {
            startDate = format.parse(sDate);
            endDate = format.parse(eDate);
            orderResponse = new OrderResponse("Success",orderService.getOrderByDate(startDate,endDate));

        } catch ( ParseException e) {
            orderResponse = new OrderResponse("Fail",e.getMessage());
        }
        return orderResponse;
    }
}
