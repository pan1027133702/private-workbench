package com.rabbit.postmanworkbench.dto.cws;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
public class Subjects {
    private int id;
    private int types;
    private int states;
    private String states_txt;
    private String states4time;
    private String title;
    private String picurl;
    private List<String> pic;
    private double price;
    private String unit;
    private String unit2;
    private int xiangzi_num;
    private String xiangzi_num_txt;
    private int xiangzi_stock;
    private int xiangzi_kucun;
    private int kucun;
    private int xiaoliang;
    private String pay_num;
    private Date date;
    private String endtime;
    private List<SimpleArr> simpleArr;
    private boolean pay_num0;
    private boolean pay_num1;
    private boolean pay_num5;
    private boolean pay_num10;
    private boolean pay_num50;
    private boolean pay_num100;
    private boolean rm_collect;
    private List<XiangziDatas> xiangziDatas;
    private int xiangzi_id;
}
