package top.hkfix.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import top.hkfix.code.PasswordStorage.CannotPerformOperationException;
import top.hkfix.code.Users;
import top.hkfix.code.sqlconn;

public class sqlOperation {
	static void update(){//改
		
	}
	static void select(){//查
		
	}
	static void delete(){//删
		
	}
	
	public static void upDate(Users u) {		//修改连接密码
		String sql = "UPDATE `users` SET " + u.getMode() + "= ? WHERE user_name= ?";
		Connection conn = sqlconn.open();
		PreparedStatement pstamt;
		try {
			pstamt = conn.prepareStatement(sql);
			if(u.getMode() == "passwd"){
				pstamt.setString(1, u.getNewPasswd());
			}else if(u.getMode() == "port"){
				pstamt.setInt(1, u.getNewPort());
			}
			pstamt.setString(2, u.getName());
			pstamt.executeUpdate();
			System.out.println("修改成功!");
			UserMenu.menu(u.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			sqlconn.close(conn);
		}
	}
	
	static void upPassword(Users u){		//修改密码
		boolean o = select(u);
		if(o == true){
			String sql = "UPDATE `users` SET `password`= ? WHERE user_name= ?";		//？：表示等待传入的值
			Connection conn = sqlconn.open();
			PreparedStatement pstamt;
			try {
				pstamt = conn.prepareStatement(sql);
				pstamt.setString(1, u.getNewPassword());
				pstamt.setString(2, u.getName());
				pstamt.executeUpdate();
				System.out.println("修改成功，请重新登录");
				Login.valiDate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				sqlconn.close(conn);
			}
		}else{
			System.out.println("你输入的原密码有误，请重试！！！");
			UserMenu.menu(u.getName());
		}
	}
	
	static int queryMess(Users u){		//动态查询单条信息
		String sql = "SELECT " + u.getMode() + " FROM `users` WHERE user_name = ?";		//？：表示等待传入的值
		Connection conn = sqlconn.open();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				switch(u.getMode())
				{
					case "port":	//端口
						int a = rs.getInt(1);
						return a;
					case "ever":	//所有流量
						int b = rs.getInt(1)/1024/1024;
						return b;
					case "u":		//上传
						int up = rs.getInt(1)/1024/1024;
						return up;
					case "d":		//下载
						int o = rs.getInt(1)/1024/1024;
						return o;
					default:
						System.out.println("\n出现查询溢出");
						break;
				}
			}
			UserMenu.menu(u.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			sqlconn.close(conn);
		}
		return 0;
	}
	
	static void queryUser(Users u){		//查询个人信息
		String sql = "SELECT * FROM `users` WHERE user_name = ?";		//？：表示等待传入的值
		Connection conn = sqlconn.open();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				int sql_uid = rs.getInt(2);
				String sql_name = rs.getString(3);
				String sql_password = rs.getString(4);
				String sql_passwd = rs.getString(5);
				String sql_email = rs.getString(6);
				int sql_ever = rs.getInt(9);
				int sql_port = rs.getInt(10);
				System.out.println(
						"个人信息：\n用户类别："+sql_uid+
						"\n用户名："+sql_name+
						"\n用户密码："+sql_password+
						"\n登录密码："+sql_passwd+
						"\n邮箱："+sql_email+
						"\n流量总额："+sql_ever+
						"\n端口号："+sql_port+
						"\n-------------------------"
						);
				UserMenu.menu(u.getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			sqlconn.close(conn);
		}
	}
	
	static void queryServer(){		//查询服务器信息
		String sql = "SELECT * FROM `ss_node`";		//？：表示等待传入的值
		Connection conn = sqlconn.open();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			System.out.println("所有服务器信息：\n-------------------------");
			while(rs.next())
			{
				String node_name = rs.getString(2);
				String node_server = rs.getString(4);
				String node_method = rs.getString(5);
				String node_info = rs.getString(6);
				String node_status = rs.getString(7);
				int node_order = rs.getInt(8);
				System.out.println(
						"\n"+node_order+
						"\n服务器名称："+node_name+
						"\n服务器地址："+node_server+
						"\n服务器加密协议："+node_method+
						"\n服务器详情："+node_info+
						"\n服务器状态："+node_status+
						"\n-------------------------"
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			sqlconn.close(conn);
		}
	}
	
	static void queryAll(Users u){		//查询所有用户信息
		String sql = "SELECT * FROM `users`";		//？：表示等待传入的值
		Connection conn = sqlconn.open();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			System.out.println("所有用户信息：");
			while(rs.next())
			{
				int sql_uid = rs.getInt(2);
				String sql_name = rs.getString(3);
				String sql_password = rs.getString(4);
				String sql_passwd = rs.getString(5);
				String sql_email = rs.getString(6);
				int sql_ever = rs.getInt(9);
				int sql_port = rs.getInt(10);
				System.out.println(
						"\n用户类别："+sql_uid+
						"\n用户名："+sql_name+
						"\n用户密码："+sql_password+
						"\n登录密码："+sql_passwd+
						"\n邮箱："+sql_email+
						"\n流量总额："+sql_ever+
						"\n端口号："+sql_port+
						"\n-------------------------"
						);
			}
			UserMenu.menu(u.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			sqlconn.close(conn);
		}
	}
	
	static boolean insertSign(Users u){		//	注册
		String sql = "INSERT INTO `users` VALUES (null, '0', ?, ?, ?, ?, '0', '0', '0', ?);";		//？：表示等待传入的值
		Connection conn = sqlconn.open();
		PreparedStatement p;
		try {
			p = conn.prepareStatement(sql);
			
			//用户名
			if(StringComparison.stringBig(u.getNewName())){		//用户名长度大于或等于8则返回true
				if(StringComparison.numAz(u.getNewName())){		//用户名只包含英文和数字则返回true
					
					//setMode设置模式为查询用户名
					u.setMode("user_name");
					
					if(selectMessOnly(u)){		//存在用户名返回true
						System.out.println("用户名已被占用！");
						
						//存在则停止注册
						return false;
					}else{
						p.setString(1, u.getNewName());		//成功则继续
					}
				}else{
					System.out.println("用户名格式出现错误,只支持英文个阿拉伯数字，请检查后重试");
					return false;
				}
			}else{
				System.out.println("输入的用户名长度不能小于8位");
				return false;
			}
			
			//密码
			if(StringComparison.stringBig(u.getNewPassword())){		//密码长度大于或等于8则返回true
				if(StringComparison.numAz(u.getNewPassword())){		//密码只包含英文和数字则返回true
					String pswd = PasswordStorage.createHash(u.getNewPassword());//密码加密(sha1+salt)后续改为sha256/512
					p.setString(2, pswd);
				}else{
					System.out.println("登录密码格式出现错误,只支持英文个阿拉伯数字，请检查后重试");
					return false;
				}
			}else{
				System.out.println("输入的登录密码长度不能小于8位");
				return false;
			}
			
			//连接密码
			if(StringComparison.stringBig(u.getNewPasswd())){		//连接密码长度大于或等于6则返回true
				if(StringComparison.numAz(u.getNewPasswd())){		//连接密码只包含英文和数字则返回true
					p.setString(3, u.getNewPasswd());
				}else{
					System.out.println("连接密码格式出现错误,只支持英文个阿拉伯数字，请检查后重试");
					return false;
				}
			}else{
				System.out.println("输入的连接密码长度不能小于8位");
				return false;
			}
			
			//邮箱
			if(StringComparison.email(u.getNewEmail())){	//邮箱合法返回true	
				//setMode设置模式为查询邮箱
				u.setMode("email");
				
				if(selectMessOnly(u)){
					System.out.println("邮箱已被占用！");
					//存在则停止注册
					return false;
				}else{
					p.setString(4, u.getNewEmail());		//成功则继续
				}
			}else{
				System.out.println("邮箱格式出现错误，请检查后重试");
				return false;
			}
			
			//p.setString(5, u.getEver());
			
			//创建端口号
			int port = queryPort() + 2;
			p.setInt(5, port);
			
			p.executeUpdate();//执行注册
			return true;
		} catch (SQLException | CannotPerformOperationException e) {
			e.printStackTrace();
		}finally{
			sqlconn.close(conn);
		}
		return false;
	}
	
	static boolean select(Users u){		//用户登录查询用户密码是否都正确
		String sql = "SELECT * FROM `users` where user_name = ? and password = ?";		//？：表示等待传入的值
		Connection conn = sqlconn.open();//连接数据库
		try {
			PreparedStatement pstamt = conn.prepareStatement(sql);//预处理
			pstamt.setString(1, u.getName());
			pstamt.setString(2, u.getPassword());//补充？占位符数值
			ResultSet rs = pstamt.executeQuery();//执行查询
			while(rs.next())//判断用户、密码
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			sqlconn.close(conn);
		}
		return false;
	}
	
	static int queryPort(){		//查询最大端口号
		String sql = "select max(port) from users";
		Connection conn = sqlconn.open();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			sqlconn.close(conn);
		}
		return 0;
	}

	static boolean selectMessOnly(Users u){		//查询信息的单一性
		String sql = "SELECT * FROM `users` where "+u.getMode()+" = ?";		//？：表示等待传入的值
		Connection conn = sqlconn.open();//连接数据库
		try {
			PreparedStatement pstamt = conn.prepareStatement(sql);//预处理
			String t = u.getMode();
			
			if(t == "user_name"){
				pstamt.setString(1, u.getNewName());
			}else if(t == "email"){
				pstamt.setString(1, u.getNewEmail());
			}else if(t == "port"){
				pstamt.setInt(1, u.getNewPort());
			}
			
			ResultSet rs = pstamt.executeQuery();//执行查询
			if(rs.next())//判断用户、密码
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			sqlconn.close(conn);
		}
		return false;
	}
}
