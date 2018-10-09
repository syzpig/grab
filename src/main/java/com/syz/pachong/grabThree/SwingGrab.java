package com.syz.pachong.grabThree;

import com.syz.pachong.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/*
 * ========================================
 * @Author:       syz
 * @Description:  下面我们再来升级一下，做成一个小工具，提供一个简单的界面，输入一个网页地址，
 * 点击提取按钮，然后把图片自动下载下来，我们可以用swing写界面。
 * 执行main方法首先出来的就是我们的界面了
 * @Date:        Create in  2018-10-09 17:48:17
 * @Modify By:
 * ========================================
 **/
public class SwingGrab {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(425,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(100, 44, 200, 30);
        frame.add(jTextField);
        JButton jButton = new JButton("提取");
        jButton.setBounds(140, 144, 100, 30);
        frame.add(jButton);
        frame.setVisible(true);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = jTextField.getText();
                if (url == null || url.equals("")) {
                    JOptionPane.showMessageDialog(null, "请填写抓取地址");
                    return;
                }
                String html = HttpUtils.get(url);
                Document doc = Jsoup.parse(html);
                Elements imgs = doc.getElementsByTag("img");
                for (Element img : imgs) {
                    String imgSrc = img.attr("src");
                    if (imgSrc.startsWith("//")) {
                        imgSrc = "http:" + imgSrc;
                    }
                    try {
                        Files.copy(new URL(imgSrc).openStream(), Paths.get("./img/"+ UUID.randomUUID()+".png"));
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                JOptionPane.showMessageDialog(null, "抓取完成");
            }
        });
    }
}
