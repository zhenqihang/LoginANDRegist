package com.program.user.dao;

import com.program.user.domain.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.sql.RowSetInternal;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.spi.XmlWriter;
import java.io.*;
import java.sql.SQLException;

public class UserDao {
    private String path = "D:/JAVAProgram/users.xml";

    public User findUserName(String userName) {

        //Doucument  创建解析器
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(path);
            Element element = (Element) doc.selectSingleNode("//user[@username='"+userName+"']");    //xpath查询
            if(element==null)   return null;

            //将element数据封装到User中
            User user = new User();
            String attrUser = element.attributeValue("username");
            String attrPwd = element.attributeValue("password");
            user.setUsername(attrUser);
            user.setPassword(attrPwd);

            return user;
        } catch (DocumentException e){
            throw new RuntimeException(e);
        }
        //return null;
    }

    public void addUser(User user) {
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(path);
            Element root = doc.getRootElement();  //得到根元素
            Element userEle = root.addElement("user");  //添加一个user元素,并返回
            userEle.addAttribute("username", user.getUsername());
            userEle.addAttribute("password",user.getPassword());

            //整理输出格式
            OutputFormat format = new OutputFormat("\t", true);
            format.setTrimText(true);

            XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path),"UTf-8"),format);
            writer.write(doc);
            writer.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
