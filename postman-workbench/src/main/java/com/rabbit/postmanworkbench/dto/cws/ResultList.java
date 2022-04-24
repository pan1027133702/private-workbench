package com.rabbit.postmanworkbench.dto.cws;

import lombok.Data;

import java.util.List;

@Data
public class ResultList {

    private int code;

    private String msg;

    private List<Subjects> subjects;
}
