package com.syz.pachong.grabTwo;

import com.syz.pachong.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/*
 * ========================================
 * @Author:       syz
 * @Description:  使用jsoup之后提取的代码只需要简单的几行即可：
 * @Date:        Create in  2018-10-09 17:42:52
 * @Modify By:
 * ========================================
 **/
public class JsoupGrab {
    public static void main(String[] args) throws MalformedURLException, IOException {
        String url = "https://www.toutiao.com/a6568327638044115460/";
        String html = HttpUtils.get(url);
        Document doc = Jsoup.parse(html);
        Elements imgs = doc.getElementsByTag("img");
        for (Element img : imgs) {
            String imgSrc = img.attr("src");
            if (imgSrc.startsWith("//")) {
                imgSrc = "http:" + imgSrc;
            }
            Files.copy(new URL(imgSrc).openStream(), Paths.get("./img/"+ UUID.randomUUID()+".png"));
        }
    }

    /*通过Jsoup.parse创建一个文档对象，然后通过getElementsByTag的方法提取出所有的图片标签，循环遍历，通过attr方法获取图片的src属性,然后下载图片。

    Jsoup使用起来非常简单，当然还有很多其他解析网页的操作，大家可以去看看资料学习一下。*/
}
