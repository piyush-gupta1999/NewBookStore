package com.assignment.readingisgood.services;

import com.assignment.readingisgood.models.Statistics;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StatisticsService {
    List<Statistics> getStats(String year, String customerId);
}
