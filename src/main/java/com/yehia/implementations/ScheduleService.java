package com.yehia.implementations;

import com.yehia.dto.ScheduleDTO;
import com.yehia.dto.ScoreDTO;
import com.yehia.entities.League;
import com.yehia.entities.Schedule;
import com.yehia.entities.User;
import com.yehia.repositories.LeagueRepository;
import com.yehia.repositories.ScheduleRepository;
import com.yehia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    public List<Schedule> getAllSchedule(){
        return scheduleRepository.findAll();
    }

    public String saveSchedule(ScheduleDTO scheduleDTO){


        User homePlayer = userRepository.findUserByName(scheduleDTO.getHomePlayerName());
        User awayPlayer = userRepository.findUserByName(scheduleDTO.getAwayPlayerName());

        Schedule schedule = new Schedule(homePlayer.getId(),awayPlayer.getId(),0,0,scheduleDTO.getDate());

        scheduleRepository.save(schedule);

        return "The Match in " + schedule.getDate();
    }

    public String saveSchedule(String home, String away, Date date){

        User homePlayer = userRepository.findUserByName(home);
        User awayPlayer = userRepository.findUserByName(away);

        Schedule schedule = new Schedule(homePlayer.getId(),awayPlayer.getId(),0,0,date);

        scheduleRepository.save(schedule);

        return "The Match in " + schedule.getDate();
    }


    public Schedule update(ScoreDTO scoreDTO){
        User homePlayer = userRepository.findUserByName(scoreDTO.getHomePlayerName());
        User awayPlayer = userRepository.findUserByName(scoreDTO.getAwayPlayerName());
        Schedule schedule = scheduleRepository.findAllByHomePlayerIdAndAwayPlayerId(homePlayer.getId(),awayPlayer.getId());
        schedule.setScorePlayerHome(scoreDTO.getScorePlayerHome());
        schedule.setScorePlayerAway(scoreDTO.getScorePlayerAway());

        return scheduleRepository.save(schedule);
    }

    public void autoUpdate(int homePlayer,int awayPlayer){
        int minPoint = 0;
        int maxPoint = 60;
        Schedule schedule = scheduleRepository.findAllByHomePlayerIdAndAwayPlayerId(homePlayer,awayPlayer);

        int homePlayerScore = (int)(Math.random()*(maxPoint-minPoint+1)+minPoint);
        int awayPlayerScore = (int)(Math.random()*(maxPoint-minPoint+1)+minPoint);
        schedule.setScorePlayerHome(homePlayerScore);
        schedule.setScorePlayerAway(awayPlayerScore);

        scheduleRepository.save(schedule);

        if(homePlayerScore > awayPlayerScore){
            //Home Player win
            League leagueWinner = leagueRepository.findFirstByPlayer_id(homePlayer);
            int totalPoint = leagueWinner.getPts();
            int totalWin = leagueWinner.getWin();
            int totalGD = leagueWinner.getGd();

            leagueWinner.setWin(totalWin +1);
            leagueWinner.setPts(totalPoint + 3);
            leagueWinner.setGd(totalGD + (homePlayerScore - awayPlayerScore));

            leagueRepository.save(leagueWinner);

            League leagueLoser = leagueRepository.findFirstByPlayer_id(awayPlayer);

            int totalLose = leagueLoser.getLose();
            int totalGd = leagueLoser.getGd();

            leagueLoser.setGd(totalGd + (homePlayerScore - awayPlayerScore));
            leagueLoser.setLose(totalLose +1);
            leagueRepository.save(leagueLoser);

        }
        else if(homePlayerScore < awayPlayerScore){
            //Away Player win
            League leagueWinner = leagueRepository.findFirstByPlayer_id(awayPlayer);
            int totalPoint = leagueWinner.getPts();
            int totalWin = leagueWinner.getWin();
            int totalGD = leagueWinner.getGd();

            leagueWinner.setWin(totalWin +1);
            leagueWinner.setPts(totalPoint + 3);
            leagueWinner.setGd(totalGD + (homePlayerScore - awayPlayerScore));

            leagueRepository.save(leagueWinner);

            League leagueLoser = leagueRepository.findFirstByPlayer_id(homePlayer);

            int totalLose = leagueLoser.getLose();
            int totalGd = leagueLoser.getGd();

            leagueLoser.setGd(totalGd + (homePlayerScore - awayPlayerScore));
            leagueLoser.setLose(totalLose +1);
            leagueRepository.save(leagueLoser);

        }
        else{
            //Draw
            League leagueHome = leagueRepository.findFirstByPlayer_id(homePlayer);
            int totalPointHome = leagueHome.getPts();
            int totalDrawHome = leagueHome.getDraw();
            int totalGDHome = leagueHome.getGd();

            leagueHome.setDraw(totalDrawHome +1);
            leagueHome.setPts(totalPointHome + 1);
            leagueHome.setGd(totalGDHome + (homePlayerScore - awayPlayerScore));

            leagueRepository.save(leagueHome);

            League leagueAway = leagueRepository.findFirstByPlayer_id(awayPlayer);

            int totalDrawAway = leagueAway.getDraw();
            int totalGDAway = leagueAway.getGd();
            int totalPointAway = leagueAway.getPts();

            leagueAway.setPts(totalPointAway +1);
            leagueAway.setGd(totalGDAway + (homePlayerScore - awayPlayerScore));
            leagueAway.setDraw(totalDrawAway +1);

            leagueRepository.save(leagueAway);
        }



    }

}
