package com.rabbit.postmanworkbench.dto.cws;

import lombok.Data;

@Data
public class MyFee {
    private double itemFee;
    private double allFee;
    private int count;
    private int id;

    public MyFee(double itemFee,double allFee,int count){
        this.allFee=allFee;
        this.itemFee=itemFee;
        this.count=count;
    }
}
