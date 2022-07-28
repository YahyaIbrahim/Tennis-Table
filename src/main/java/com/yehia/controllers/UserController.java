package com.yehia.controllers;


import com.yehia.dto.UserDTO;
import com.yehia.entities.User;
import com.yehia.generator.Fixture;
import com.yehia.generator.FixtureGenerator;
import com.yehia.implementations.ScheduleService;
import com.yehia.implementations.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(value = "User APIs", tags = {"User Management"},
        description = "User operations")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation(value = "Submit a participant request")
    @PostMapping(value = "/request")
    public String request(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @ApiOperation(value = "Get a list of all participants")
    @GetMapping(value = "/get-all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "Group randomly participants into (n) groups")
    @GetMapping(value = "/group-randomly")
    public List<String> group(){
        List<User> users = userService.getAllUsers();

        List<String> teams = new LinkedList<>();

        List<String> mixedRound = new ArrayList<>();

        users.forEach(user -> teams.add(user.getName()));
        FixtureGenerator<String> fixtureGenerator = new FixtureGenerator();
        List<List<Fixture<String>>> rounds = fixtureGenerator.getFixtures(teams, true);
        List<String> homePlayers = new ArrayList<>();
        List<String> awayPlayers = new ArrayList<>();
        for (List<Fixture<String>> round : rounds) {
            for (Fixture<String> fixture : round) {
                mixedRound.add(fixture.getHomeTeam() + " vs " + fixture.getAwayTeam());
                homePlayers.add(fixture.getHomeTeam());
                awayPlayers.add(fixture.getAwayTeam());
            }
        }
        addMatches(homePlayers,awayPlayers);
        return mixedRound;
    }

    private void addMatches(List<String> home, List<String> away){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.DATE,1);
        int size = home.size();
        for(int day =1;day<size;day++) {
            for (int i = 0; i < 3; i++) {
                if(home.size() == 0)
                    break;
                scheduleService.saveSchedule(home.remove(0),away.remove(0),new Date(calendar.getTime().getTime()));
            }
            if(home.size() == 0)
                break;
            calendar.add(Calendar.DATE,1);
        }

    }
}

