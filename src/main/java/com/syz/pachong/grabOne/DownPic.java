package com.syz.pachong.grabOne;

import com.syz.pachong.utils.HttpUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static com.syz.pachong.grabOne.TestGrab.getImageSrc;

/*
 * ========================================
 * @Author:       syz
 * @Description:  通过上面的地址我们就可以将图片下载到本地了，下面我们写个图片下载的方法：
 *
 * 这样就很简单的实现了一个抓取并且提取图片的功能了，看起来还是比较麻烦哈，要写正则之类的 ，
 * 下面给大家介绍一种更简单的方式，如果你熟悉jQuery的话对提取元素就很简单了，这个框架就是Jsoup。
 * @Date:        Create in  2018-10-09 17:38:24
 * @Modify By:
 * ========================================
 **/
public class DownPic {
    public static void main(String[] args) throws MalformedURLException, IOException {
        String url = "https://www.toutiao.com/a6568327638044115460/";
        String html = HttpUtils.get(url);
        List<String> imgUrls = getImageSrc(html);
        for (String imgSrc : imgUrls) {
            Files.copy(new URL(imgSrc).openStream(), Paths.get("./img/"+ UUID.randomUUID()+".png"));
        }
    }
}
