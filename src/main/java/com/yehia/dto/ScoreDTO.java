package com.yehia.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScoreDTO {

    private String HomePlayerName;
    private String AwayPlayerName;
    private int ScorePlayerHome;
    private int ScorePlayerAway;

}
