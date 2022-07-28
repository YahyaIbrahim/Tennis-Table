package com.yehia.controllers;


import com.yehia.dto.ScheduleDTO;
import com.yehia.dto.ScoreDTO;
import com.yehia.entities.Schedule;
import com.yehia.implementations.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Schedule APIs", tags = {"Schedule Management"},
        description = "Schedule operations")
@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;


    @ApiOperation(value = "request for a new match")
    @PostMapping(value = "/new-Match")
    public String newMatch(@RequestBody ScheduleDTO scheduleDTO){
        return scheduleService.saveSchedule(scheduleDTO);
    }

    @ApiOperation(value = "manual update player's scores and update the League Table")
    @PutMapping(value = "/update")
    public Schedule update(@RequestBody ScoreDTO scoreDTO){

        return scheduleService.update(scoreDTO);
    }

    @ApiOperation(value = "Auto update player's scores and update the League Table")
    @PutMapping(value = "/auto-update")
    public String autoUpdate(){
        List<Schedule> scheduleList = scheduleService.getAllSchedule();
        scheduleList.forEach(schedule -> {
            scheduleService.autoUpdate(schedule.getHomePlayerId(),schedule.getAwayPlayerId());
        });
        return "The Schedule and the League table have been updated";
    }



}
