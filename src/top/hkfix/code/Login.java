package top.hkfix.code;

import java.util.Scanner;
import top.hkfix.code.Login;

public class Login {//登录类
	static String namet;
	static boolean startLogin(){//用户登录
		Scanner sc = new Scanner(System.in);//接收控制台信息
		Users u = new Users();
		
		System.out.print("登录请输入用户名：");
		u.setName(sc.next());
		namet = u.getName();
		System.out.println("-------------------------");
		System.out.print("登录请输入密码：");
		u.setPassword(sc.next());
		System.out.println("-------------------------");
		boolean lo = sqlOperation.select(u);	//反馈用户及密码是否同时正确
		return lo;
	}
	
	static void valiDate(){		//登录验证循环
		int d = 0;//登录判断
		for(int i = 1;i > d;){
			boolean bl = startLogin();	//登录方法
			if(bl == true){		//判断是否存在这个用户，及密码是否匹配（以后会将密码进行加密）
				System.out.println("登录成功！！！正在跳转···");
				System.out.println("-------------------------");
				d = 2;		//跳出登录循环
			}else{		//如果登录失败将一直死循环
				System.out.println("登录失败！用户名或密码错误，请重新登录");
				System.out.println("-------------------------");
			}
		}
		UserMenu.menu(namet);
	}
	
	
}
