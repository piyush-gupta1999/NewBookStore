package com.assignment.readingisgood.repository;

import com.assignment.readingisgood.models.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics,String> {
    @Query(value = "SELECT month(o.order_date) month_no,COUNT(1) count,sum(book_count) book_count,sum(price) price FROM ORDERS o WHERE year(o.order_date) = :year AND o.customer_id = :customer_id GROUP BY month(o.order_date)",nativeQuery = true)
    List<Statistics> getStats(@Param("customer_id") String customer_id,@Param("year") String year);
}