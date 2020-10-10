package com.daxiong.book.util;
//读取配置文件，获取连接对象，关闭资源
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
	private static Properties properties; // 属性集对象
	// 静态块加载配置文件
	static {
		try {
			properties = new Properties(); // 实例化属性
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//静态方法：获取连接对象
	public static Connection getConnection() throws Exception {
		Class.forName(properties.getProperty("jdbc.driver"));
		return DriverManager.getConnection(properties.getProperty("jdbc.url"), 
				properties.getProperty("jdbc.username"),
				properties.getProperty("jdbc.password"));
	}
	
	//静态方法：关闭资源
	public static void close(ResultSet rs,PreparedStatement psmt,Connection conn) throws SQLException {
		if(rs != null) {
			rs.close();
		}
		if(psmt != null) {
			psmt.close();
		}
		if(conn != null) {
			conn.close();
		}
	}

}
