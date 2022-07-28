package com.yehia.controllers;

import com.yehia.implementations.LeagueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "League APIs", tags = {"League Management"},
        description = "League operations")
@RestController
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @ApiOperation(value = "get league champion")
    @GetMapping(value = "/the-champion")
    public String getChampion(){
        return "The champion is " + leagueService.theChampion();
    }


    @ApiOperation(value = "send Mail to the winner")
    @GetMapping(value = "/send-mail")
    public String sendMail(){
        leagueService.sendMail();
        return "Done, don't forget to check the Spam mails. Thank you ";
    }
}
