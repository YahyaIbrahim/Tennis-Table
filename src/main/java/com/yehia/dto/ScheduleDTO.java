package com.yehia.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Data
public class ScheduleDTO {

    private String HomePlayerName;
    private String AwayPlayerName;
    private Date date;




}
