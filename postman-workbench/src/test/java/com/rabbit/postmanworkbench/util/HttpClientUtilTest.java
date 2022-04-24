package com.rabbit.postmanworkbench.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbit.postmanworkbench.dto.cws.*;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HttpClientUtilTest {

    @Test
    void doPost() {
        String body="[{\"ua\":\"Mozilla/5.0 (iPhone; CPU iPhone OS 14_2_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/8.0.18(0x18001235) NetType/WIFI Language/zh_CN miniProgram/wx2c348cf579062e56\",\"sdk_ver\":\"4.19.1\",\"ch\":\"wxappshare\",\"sc\":\"390*844\",\"uuid\":\"8e43bc65-3d29-413b-af24-e950f29d1482\",\"lxcuid\":\"17f82108287c8-0bd2dbb660d9c-11d4c50-505c8-17f82108287c8\",\"ct\":\"i\",\"utm\":{\"utm_campaign\":\"wmsq-15758\",\"utm_source\":\"wxappshare\"},\"msid\":\"17f8804465c-7be-a36-021\",\"appnm\":\"waimai_wxapp\",\"category\":\"data_sdk_firework\",\"cid\":\"c_cakhs7q\",\"uid\":\"262892757\",\"mvDelay\":1,\"lch\":\"1007\",\"sdk_env\":\"online\",\"evs\":[{\"nm\":\"MC\",\"tm\":1647254751480,\"nt\":6,\"isauto\":7,\"val_cid\":\"c_cakhs7q\",\"req_id\":\"17f88044739-22122-54759\",\"seq\":27,\"lx_inner_data\":{\"path\":\"https://market.waimai.meituan.com/gd/single.html\",\"isHeadless\":1100,\"labv\":10006,\"cv\":\"prod\",\"web\":1,\"proxy\":1,\"btoa\":true,\"atob\":true,\"stime\":213.0000000000001,\"pvid\":\"pvid-9636061-7775307\",\"m_msid\":\"mem_17f88044659-907-d90-c7e\",\"m_seq\":27,\"ht\":false},\"val_bid\":\"b_khgf7vzg\",\"val_lab\":{\"componentInfo\":{\"name\":\"@gdc/wm-limit-time-grab-red-envelope\",\"instanceID\":\"16423389094640.5632507256338901\",\"version\":\"0.0.5\",\"type\":\"component\",\"cTemp\":\"\",\"statisEdition\":1},\"gd_page_id\":245214,\"activity_id\":\"240561\",\"tenant\":\"gundam\"},\"tag\":{\"waimai\":{\"c_cakhs7q\":{\"bid\":\"b_waimai_u6bmx6y1_mc\",\"gd_page_id\":245214,\"activity_id\":\"240561\"}}}}],\"lx_dict\":\"false\"}]";
//        JSONObject json1 = new JSONObject();
//        json1= JSONObject.parseObject(body);

        System.out.println("11");
    }

    @Test
    public void testDoGet() throws Exception{
        String url="https://api.chaowanshang.51goshop.com/api/3.0.2/shang.php?act=info&detailId=399";
        HttpClientUtil httpClientUtil=new HttpClientUtil();
        String response=httpClientUtil.doGet(url);
        System.out.println("开始打印结果：----------------------------------");

        System.out.println("打印结束：----------------------------------");

        ResultCWS resultCWS=JSONObject.parseObject(response,ResultCWS.class);
//        System.out.println("开始打印结果："+resultCWS.getMsg());
        Subjects subjects=resultCWS.getSubjects();
        List<XiangziDatas> XiangziDataslists=resultCWS.getSubjects().getXiangziDatas();
        XiangziDatas xiangziDatas=XiangziDataslists.get(0);
        List<DengjiDatas> DengjiDataslists=resultCWS.getSubjects().getXiangziDatas().get(0).getDengjiDatas();
        Double itemFee=0d;
        Double allFee=(subjects.getXiangzi_kucun()-subjects.getXiaoliang())*subjects.getPrice();
        System.out.println("购买总费用："+allFee);
        for(DengjiDatas dengjiDatas:DengjiDataslists){
            itemFee=itemFee+(dengjiDatas.getKucun()-dengjiDatas.getXiaoliang())*dengjiDatas.getProductDatas().getPrice();
        }
        System.out.println("商品总价值："+itemFee);
    }
    @Test
    public void testDoGet2() throws Exception{
        String url="https://api.chaowanshang.51goshop.com/api/3.0.2/shang.php?act=main&start=0&count=6&types=1&big=";
        HttpClientUtil httpClientUtil=new HttpClientUtil();
        String response=httpClientUtil.doGet(url);
        ResultList resultList=JSONObject.parseObject(response,ResultList.class);
        List<Subjects> subjectsList=resultList.getSubjects();
        int count=1;
        List<MyFee> list=new ArrayList<>();
        for (Subjects subjects:subjectsList){
            MyFee myFee=countFee(subjects.getId(),count);
            list.add(myFee);
            count++;
        }
//        MyFee myFee=countFee(subjectsList.get(0).getId(),count);
//        list.add(myFee);
        for(MyFee fee:list){
            System.out.println("第"+fee.getCount()+"个箱子的总费用是"+fee.getAllFee()+"，商品价值是："+fee.getItemFee()+";差值："+(fee.getAllFee()-fee.getItemFee()));
        }
    }

    public MyFee countFee(int id,int count) throws Exception {
        String url="https://api.chaowanshang.51goshop.com/api/3.0.2/shang.php?act=info&detailId="+id;
        HttpClientUtil httpClientUtil=new HttpClientUtil();
        String response=httpClientUtil.doGet(url);
//        System.out.println("开始打印第"+count+"个箱子结果：----------------------------------");
        ResultCWS resultCWS=JSONObject.parseObject(response,ResultCWS.class);
//        System.out.println("开始打印结果："+resultCWS.getMsg());
        Subjects subjects=resultCWS.getSubjects();
        List<XiangziDatas> XiangziDataslists=resultCWS.getSubjects().getXiangziDatas();
        XiangziDatas xiangziDatas=XiangziDataslists.get(0);
        List<DengjiDatas> DengjiDataslists=resultCWS.getSubjects().getXiangziDatas().get(0).getDengjiDatas();
        Double itemFee=0.0d;
        Double allFee=(subjects.getXiangzi_kucun()-subjects.getXiaoliang())*subjects.getPrice();

//        System.out.println("购买总费用："+allFee);
        for(DengjiDatas dengjiDatas:DengjiDataslists){
            itemFee=itemFee+(dengjiDatas.getKucun()-dengjiDatas.getXiaoliang())*dengjiDatas.getProductDatas().getPrice();
            System.out.println(dengjiDatas.getTitle()+"的商品数量为："+(dengjiDatas.getKucun()-dengjiDatas.getXiaoliang())+"单价为:"+dengjiDatas.getProductDatas().getPrice());

        }
//        System.out.println("商品总价值："+itemFee);
        return new MyFee(itemFee,allFee,count);
    }

}
