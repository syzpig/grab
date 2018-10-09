package com.syz.pachong.grabOne;

import com.syz.pachong.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * ========================================
 * @Author:       syz
 * @Description:  接下来我们随便找一个有图片的网页，来试试抓取功能
 * 首先将网页的内容抓取下来，然后用正则的方式解析出网页的标签，再解析img的地址。执行程序我们可以得到下面的内容：
 * @Date:        Create in  2018-10-09 17:37:07
 * @Modify By:
 * ========================================
 **/
public class TestGrab {
    public static void main(String[] args) {
        String url = "https://www.toutiao.com/a6568327638044115460/";
        String html = HttpUtils.get(url);
        List<String> imgUrls = getImageSrc(html);
        for (String imgSrc : imgUrls) {
            System.out.println(imgSrc);
        }
    }
    public static List<String> getImageSrc(String html) {
        // 获取img标签正则
        String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
        // 获取src路径的正则
        String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);
        List<String> listImgUrl = new ArrayList<String>();
        while (matcher.find()) {
            Matcher m = Pattern.compile(IMGSRC_REG).matcher(matcher.group());
            while (m.find()) {
                listImgUrl.add(m.group().substring(0, m.group().length() - 1));
            }
        }
        return listImgUrl;
    }
}
