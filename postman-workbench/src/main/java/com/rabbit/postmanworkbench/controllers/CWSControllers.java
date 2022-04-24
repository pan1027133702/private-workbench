package com.rabbit.postmanworkbench.controllers;

import com.alibaba.fastjson.JSONObject;
import com.rabbit.postmanworkbench.dto.cws.*;
import com.rabbit.postmanworkbench.util.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cws/count")
public class CWSControllers  {
    @GetMapping("/test")
    @ResponseBody
    public String test() throws Exception{
        String url="https://api.chaowanshang.51goshop.com/api/3.0.2/shang.php?act=main&start=0&count=6&types=1&big=";
        HttpClientUtil httpClientUtil=new HttpClientUtil();
        String response=httpClientUtil.doGet(url);
        ResultList resultList= JSONObject.parseObject(response,ResultList.class);
        List<Subjects> subjectsList=resultList.getSubjects();
        int count=1;
        List<MyFee> list=new ArrayList<>();
//        for (Subjects subjects:subjectsList){
//            MyFee myFee=countFee(subjects.getId(),count);
//            list.add(myFee);
//            count++;
//        }
        MyFee myFee=countFee(subjectsList.get(0).getId(),count);
        list.add(myFee);
        String str="";
        for(MyFee fee:list){
            System.out.println("第"+fee.getCount()+"个箱子的总费用是"+fee.getAllFee()+"，商品价值是："+fee.getItemFee()+";差值："+(fee.getAllFee()-fee.getItemFee()));
            str=str+"/n"+"第"+fee.getCount()+"个箱子的总费用是"+fee.getAllFee()+"，商品价值是："+fee.getItemFee()+";差值："+(fee.getAllFee()-fee.getItemFee());

        }
        return str;
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
