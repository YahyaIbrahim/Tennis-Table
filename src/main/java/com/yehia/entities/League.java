package com.yehia.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "league")
public class League extends AuditModel{

    @Column(name = "win")
    private int win;

    @Column(name = "lose")
    private int lose;

    @Column(name = "draw")
    private int draw;

    @Column(name = "gd")
    private int gd;

    @Column(name = "pts")
    private int pts;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private User player_id;

    public League(int win, int lose, int draw, int gd, int pts, User player_id) {
        this.win = win;
        this.lose = lose;
        this.draw = draw;
        this.gd = gd;
        this.pts = pts;
        this.player_id = player_id;
    }

    public League() {

    }
}
