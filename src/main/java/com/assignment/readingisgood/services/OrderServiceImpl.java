package com.assignment.readingisgood.services;

import com.assignment.readingisgood.exceptions.*;
import com.assignment.readingisgood.models.Book;
import com.assignment.readingisgood.models.BookQuantity;
import com.assignment.readingisgood.models.Order;
import com.assignment.readingisgood.models.OrderRequest;
import com.assignment.readingisgood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Component
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerServices customerServices;

    @Autowired
    private BookService bookService;

    @Override
    public String bookOrder(OrderRequest orderRequest) throws BookNotFound, OutOfStockException, UserNotExist, InvalidInput {
        validateInputs(orderRequest);
        List<BookQuantity> bookList = orderRequest.getBookList();
        String customer_id = orderRequest.getCustomer_id();
        if(customerServices.validateCustomer(customer_id)){
            for(BookQuantity b:bookList){
                Integer actualQuantity = bookService.getQuantity(b.getBookId());
                if(actualQuantity < b.getQuantity()){
                    throw new OutOfStockException("Book id: " + b.getBookId() + " is having " + actualQuantity+ " quantity and you wanted "+ b.getQuantity()+ " quantity.");
                }
            }
            double price = 0.0;
            int quantity = 0;
            for(BookQuantity b:bookList){
                Book book = bookService.getBook(b.getBookId());
                price += book.getPrice()*b.getQuantity();
                quantity = b.getQuantity();
                bookService.updateQuantity(b.getBookId(),-b.getQuantity());
            }
            String order_id = UUID.randomUUID().toString();
            Order order = new Order(order_id,orderRequest.getCustomer_id(), new Date(),quantity,price);
            orderRepository.save(order);
            return order_id;
        }else{
            throw new UserNotExist("Customer id: " + customer_id +" not found.");
        }

    }

    @Override
    public Order getOrderById(String order_id) throws OrderNotFound {
        if(orderRepository.existsById(order_id)){
            return orderRepository.getById(order_id);
        }else {
            throw new OrderNotFound("Order not found with id:"+order_id);
        }
    }

    @Override
    public List<Order> getOrderByDate(Date startDate, Date endDate) {
        return orderRepository.findByDate(startDate,endDate);
    }

    @Override
    public List<Order> getCustomerOrders(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    private void validateInputs(OrderRequest orderRequest) throws InvalidInput {
        List<BookQuantity> bookList = orderRequest.getBookList();
        for(BookQuantity b:bookList){
            if(b.getQuantity() <= 0)
                throw new InvalidInput("Book id: " + b.getBookId() + " is having an invalid quantity.");
        }

    }

}
