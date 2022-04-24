package com.rabbit.postmanworkbench.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpClientUtil
{
    public String doPost(String url,String auToken1, JSONObject json, String charset)
    {
        /*
         * 利用HttpClient进行post请求的工具类
         */
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try
        {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            StringEntity se = new StringEntity(json.toString(),"UTF-8");
            se.setContentType("application/json");
            se.setContentEncoding(new BasicHeader("text/json", "application/json"));
            httpPost.setEntity(se);
            httpPost.setHeader("Host", "api.airmart.top");
            httpPost.setHeader("Connection", "keep-alive");
            httpPost.setHeader("app_version", "1.10.0");
            httpPost.setHeader("chan_data", "icode=&chan_id=&scene=1007");
            httpPost.setHeader("client_type", "wechat_mini");
            httpPost.setHeader("authorization", auToken1);
            httpPost.setHeader("content-type", "application/json");
            httpPost.setHeader("Accept-Encoding", "gzip,compress,br,deflate");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 14_2_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/8.0.18(0x18001234) NetType/WIFI Language/zh_CN");
            httpPost.setHeader("Referer", "https://servicewechat.com/wxc2f42496fb5f5b91/37/page-frame.html");
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null)
            {
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null)
                {
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
    public String doGet(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        // 设置类型 "application/x-www-form-urlencoded" "application/json"
        httpGet.setHeader("Host", "api.chaowanshang.51goshop.com");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("mapInfo", "{\"info\":\"\"}");
        httpGet.setHeader("userToken", "d9f2b170ec17f5966fe1e3507ee13c2f");
        httpGet.setHeader("content-type", "application/json");
        httpGet.setHeader("Accept-Encoding", "gzip,compress,br,deflate");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 14_2_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/8.0.18(0x1800123f) NetType/4G Language/zh_CN");
        httpGet.setHeader("Referer", "https://servicewechat.com/wx3ac86234c5a7708a/10/page-frame.html");

        System.out.println("调用URL: " + httpGet.getURI());
        // httpClient实例化
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 执行请求并获取返回
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        System.out.println("返回状态码：" + response.getStatusLine());
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        String line = null;
        StringBuffer responseSB = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            responseSB.append(line);
        }
        reader.close();
        httpClient.close();
        return responseSB.toString();

    }




}
