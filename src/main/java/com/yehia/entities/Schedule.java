package com.yehia.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "schedule")
public class Schedule extends AuditModel{


    @Column(name = "home_player_id")
    private int HomePlayerId;

    @Column(name = "away_player_id")
    private int AwayPlayerId;

    @Column(name = "score_player_home")
    private int ScorePlayerHome;

    @Column(name = "score_player_away")
    private int ScorePlayerAway;

    @Column(name = "date")
    private Date date;

    public Schedule(int homePlayerId, int awayPlayerId, int scorePlayerHome, int scorePlayerAway, Date date) {
        HomePlayerId = homePlayerId;
        AwayPlayerId = awayPlayerId;
        ScorePlayerHome = scorePlayerHome;
        ScorePlayerAway = scorePlayerAway;
        this.date = date;
    }

    public Schedule() {

    }
}
