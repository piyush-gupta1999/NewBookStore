package com.assignment.readingisgood.repository;

import com.assignment.readingisgood.models.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics,String> {
    @Query(value = "select month(order_date),count(1) as orderCount ,sum(book_count) bookCount, sum(price) purchaseAmount from ORDERS where year(order_date) = :year and customer_id = :customer_id group by month(order_date) as Statistics",nativeQuery = true)
    List<Statistics> getStats(@Param("year") String year, @Param("customer_id") String customer_id);
}