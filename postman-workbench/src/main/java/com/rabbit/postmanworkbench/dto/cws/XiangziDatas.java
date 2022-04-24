package com.rabbit.postmanworkbench.dto.cws;

import lombok.Data;

import java.util.List;

@Data
public class XiangziDatas {
    private int id;
    private int code;
    private String title;
    private int kucun;
    private int xiaoliang;
    private int is_kucun1;
    private List<String> pic;
    private List<String> itemDatas;
    private List<DengjiDatas> dengjiDatas;
    private List<String> haomaDatas;
}
