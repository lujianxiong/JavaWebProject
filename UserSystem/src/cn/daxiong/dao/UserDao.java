package cn.daxiong.dao;
//数据类
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.daxiong.domain.User;

public class UserDao {
	private String path="E:\\users.xml";  //数据文件
	
	//按用户名查询
	public User findByUsername(String username) {
	//dom4j的一系列操作：
		try {
			//1、得到Document对象
			SAXReader reader = new SAXReader();
			Document doc = reader.read(path);
			//2、XPath查询，得到Element元素
			Element ele = (Element) doc.selectSingleNode("//user[@username='"+username+"']");
			//3、校验查询结果ele是否为null，如果为null，则返回null
			if (ele == null) return null;
			//4、ele不为null，将Element封装到User对象中
			User user = new User();
			String attrUsername = ele.attributeValue("username");  //获取ele元素名为username的属性值
			String attrPassword = ele.attributeValue("password");  //获取ele元素名为password的属性值
			user.setUsername(attrUsername);
			user.setPassword(attrPassword);
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//添加用户
	public void add(User user) {
	//dom4j的一系列操作：
		try {
			//1、得到Document对象
			SAXReader reader = new SAXReader();
			Document doc = reader.read(path);
			//2、通过Document得到root元素（<users>）
			Element root = doc.getRootElement();
			//3、将user转换成Element对象,把element添加到root元素中
			Element userEle = root.addElement("user");  //通过根元素创建新的user元素
			userEle.addAttribute("username", user.getUsername());  //为userEle设置属性
			userEle.addAttribute("password", user.getPassword());  //为userEle设置属性
			//4、保存document
			//创建输出格式化器
			OutputFormat format = new OutputFormat("\t",true);  //缩进使用\t，是否换行：是！
			format.setTrimText(true);  //清空原有的换行和缩进
			//创建XmlWriter
			XMLWriter writer = new XMLWriter(
					new OutputStreamWriter(
							new FileOutputStream(path), "utf-8"),format);
			writer.write(doc);  //保存document对象
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
