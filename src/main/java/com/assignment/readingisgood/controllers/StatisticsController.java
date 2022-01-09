package com.assignment.readingisgood.controllers;

import com.assignment.readingisgood.models.Response;
import com.assignment.readingisgood.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/statistics/filter={customerId}&{year}",method = RequestMethod.GET)
    @ResponseBody
    public Response getStatistics(@PathVariable String customerId, @PathVariable String year) {
        try {
            return new Response("Success",statisticsService.getStats(year, customerId));
        }catch(Exception e) {
            return new Response("Fail",e.getMessage());
        }
    }
}
