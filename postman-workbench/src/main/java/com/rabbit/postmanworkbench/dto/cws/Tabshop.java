package com.rabbit.postmanworkbench.dto.cws;

import lombok.Data;

import java.util.List;

@Data
public class Tabshop {
    private boolean is_tabshop;
    private List<String> is_tabshopPage;
    private int is_tabshopSecond;
    private int is_tabshopSecondFirst;
    private int is_tabshopSecondClose;
    private List<String> subjects;
}
