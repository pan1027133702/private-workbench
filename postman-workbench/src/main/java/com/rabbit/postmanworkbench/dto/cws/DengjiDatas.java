package com.rabbit.postmanworkbench.dto.cws;

import lombok.Data;

@Data
public class DengjiDatas {
    private int id;
    private int types;
    private String title;
    private int kucun;
    private int xiaoliang;
    private ProductDatas productDatas;
    private String probability;
}
