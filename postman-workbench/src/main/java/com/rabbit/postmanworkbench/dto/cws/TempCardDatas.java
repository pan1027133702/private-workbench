package com.rabbit.postmanworkbench.dto.cws;

import lombok.Data;

import java.util.List;

@Data
public class TempCardDatas {
    private int num;
    private String title;
    private String jian;
    private String button1;
    private String button2;
    private List<String> list;
}
