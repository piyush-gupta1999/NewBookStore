package com.assignment.readingisgood.services;

import com.assignment.readingisgood.models.Statistics;
import com.assignment.readingisgood.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class StatisticsServiceImpl implements StatisticsService{

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public List<Statistics> getStats(String year, String customerId) {
        return statisticsRepository.getStats(year, customerId);
    }
}
