package com.rabbit.postmanworkbench.dto.cws;

import lombok.Data;

import java.util.List;

@Data
public class AppConfig {
    private AppMain appMain;
    private Kefu kefu;
    private LoginMain loginMain;
    private AboutMain aboutMain;
    private Tabshop tabshop;
    private Yaoqing yaoqing;
    private TempCardDatas tempCardDatas;
    private Shopping shopping;
    private TemplateIds templateIds;
    private Xinxi xinxi;
    private String tixian_jine_mix;
    private String tixian_day_num_max;
    private String tixian_day_jine_max;
    private String tixian_content;
    private String shang_huishou_day;
    private String index_qun_url;
    private int news_id_payxieyi;
    private int news_id_payshuoming1;
    private int news_id_payshuoming2;
    private int news_id_gailv1;
    private int news_id_gailv2;
    private int news_id_huishoushuoming;
    private int news_id_fahuoshuoming;
    private int news_id_tixianxieyi;
    private int news_id_about;
    private int news_id_zhuli;
    private int news_id_product;
    private List<Shang1_big> shang1_big;
    private List<String> shang2_big;
    private String login_img;
    private Jifen jifen;
    private String tengxunMapKey;


}
