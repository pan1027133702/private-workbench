package com.rabbit.postmanworkbench.dto.cws;

import lombok.Data;

@Data
public class ResultCWS {
    private int code;

    private String msg;

    private AppConfig appConfig;

    private Subjects subjects;



}
