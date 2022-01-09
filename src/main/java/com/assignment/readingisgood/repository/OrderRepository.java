package com.assignment.readingisgood.repository;

import com.assignment.readingisgood.models.Order;
import com.assignment.readingisgood.models.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Query(value = "SELECT * FROM ORDERS o where o.ORDER_DATE BETWEEN :start_date AND :end_date", nativeQuery = true)
    List<Order> findByDate(@Param("start_date") Date start_date, @Param("end_date") Date end_date);

    @Query(value = "SELECT * FROM ORDERS o where o.customer_id = :customer_id", nativeQuery = true)
    List<Order> findByCustomerId(@Param("customer_id") String customer_id);
}
