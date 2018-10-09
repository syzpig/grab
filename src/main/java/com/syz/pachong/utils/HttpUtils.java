package com.syz.pachong.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/*
 * ========================================
 * @Author:       syz
 * @Description:  首先我们封装一个Http请求的工具类，用HttpURLConnection实现，当然你也可以用HttpClient,
  或者直接用Jsoup来请求（下面会讲到Jsoup）。
工具类实现比较简单，就一个get方法，读取请求地址的响应内容，这边我们用来抓取网页的内容，这边没有用代理，
在真正的抓取过程中，当你大量请求某个网站的时候，对方会有一系列的策略来禁用你的请求，这个时候代理就排上用场了，
通过代理设置不同的IP来抓取数据。
 * @Date:        Create in  2018-10-09 17:34:58
 * @Modify By:
 * ========================================
 **/
public class HttpUtils {
    public static String get(String url) {
        try {
            URL getUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) getUrl
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "*/*");
            connection
                    .setRequestProperty("User-Agent",
                            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA)");
            connection.setRequestProperty("Accept-Language", "zh-cn");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null){
                result.append(line);
            }
            reader.close();
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
