package top.hkfix.code;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class sqlconn {
	private static String driver;
	private static String url;
	private static String dbname;
	private static String username;
	private static String password;
	private static String port;
	private static String type;
	
	static{
		Properties prop = new Properties();
		Reader in;
		try {
			in = new FileReader("src\\config.properties");
			prop.load(in);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		dbname = prop.getProperty("dbname");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
		port = prop.getProperty("port");
		type = prop.getProperty("type");
	}
	
	public static Connection open()		//数据库连接
	{
		try {
			Class.forName(driver);
			return DriverManager.getConnection("jdbc:"+type+"://"+url+":"+port+"/"+dbname,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection conn){		//数据库关闭
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}