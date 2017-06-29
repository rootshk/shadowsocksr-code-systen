package top.hkfix.code;

import java.util.Scanner;
import top.hkfix.code.Users;
import top.hkfix.code.sqlOperation;

public class UserMenu {
	static Scanner sc = new Scanner(System.in);
	static void menu(String name){		//菜单判断
		System.out.println("输入对应数字打开对应功能");
		System.out.println("1.查看本人信息");
		System.out.println("2.查看所有用户信息");
		System.out.println("3.修改登录密码");
		System.out.println("4.修改连接密码");
		System.out.println("5.查看剩余流量");
		System.out.println("6.查看端口号");
		System.out.println("7.修改端口号");
		System.out.println("8.查看服务器信息");
		System.out.println("-------------------------");
		System.out.print("对应数字（1~8）：");
		
		int num;
		num = sc.nextInt();
		Users u = new Users();
		switch(num)
		{
			case 1:		//查看个人信息
				u.setName(name);
				sqlOperation.queryUser(u);
				break;
			case 2:		//查看所有用户
				sqlOperation.queryAll(u);
				break;
			case 3:		//修改登录密码
				System.out.print("请输入原登录密码：");
				String pa = sc.next();
				System.out.print("请输入你要修改的密码：");
				String pb = sc.next();
				System.out.print("请重复输入你要修改的密码：");
				String pc = sc.next();
				if(pb.equals(pc)){
					if(StringComparison.stringBig(pc)){		//长度大于或等于8则返回true
						u.setName(name);
						u.setPassword(pa);
						u.setNewPassword(pc);
						sqlOperation.upPassword(u);
					}else{
						System.out.print("密码长度小于8位！");
						menu(u.getName());
					}
				}else{
					System.out.println("两次输入的密码不一致，请重试！！！");
					menu(u.getName());
				}
				break;
			case 4:		//修改连接密码
				System.out.print("请先输入要修改的连接密码：");
				String pwda = sc.next();
				System.out.print("再次输入要修改的连接密码：");
				String pwdb = sc.next();
				if(pwda.equals(pwdb)){
					if(StringComparison.stringBig(pwdb)){		//长度大于或等于8则返回true
						u.setName(name);
						u.setNewPasswd(pwdb);
						u.setMode("passwd");
						sqlOperation.upDate(u);
					}else{
						System.out.println("密码长度小于八位，请重试");
						menu(u.getName());
					}
				}else{
					System.out.println("两次输入的密码不一致，请重试！！！");
					menu(u.getName());
				}
				break;
			case 5:		//查看剩余流量
				u.setName(name);
				
				u.setMode("ever");
				int t = sqlOperation.queryMess(u);//所有流量
				
				u.setMode("u");
				int up = sqlOperation.queryMess(u);//上传
				
				u.setMode("d");
				int dow = sqlOperation.queryMess(u);//下载
				System.out.print("你拥有的流量总值为：" + t + " MB,剩余流量为：" + (t-(up+dow)) + " MB");
				menu(u.getName());
				break;
			case 6:		//查看端口号
				u.setName(name);
				u.setMode("port");
				sqlOperation.queryMess(u);
				break;
			case 7:		//修改端口号
				u.setName(name);
				u.setMode("port");
				System.out.print("请输入你想修改的端口号:");
				u.setNewPort(sc.nextInt());
				
				int max = 30000;//端口最大值
				int min = 10000;//端口最小值
				if(u.getNewPort() >= min && u.getNewPort() <= max){
					boolean a = sqlOperation.selectMessOnly(u);
					if(a == true){
						System.out.println("端口号已被占用！");
						menu(u.getName());
					}else{
						System.out.println("端口号未被使用，可以更换");
						changeMess(u);
					}
				}
				break;
			case 8:		//查看服务器详情
				u.setName(name);
				sqlOperation.queryServer();
				menu(u.getName());
				break;
			default:
				System.out.println("你的输入有误，请重新输入！");
				u.setName(name);
				menu(u.getName());
				break;
		}
	}
	
	static void changeMess(Users u){
		System.out.println("输入‘Y’确认更换，输入其他取消更换");
		String n = sc.next();
		switch (n){
			case"Y":
				sqlOperation.upDate(u);
				break;
			case"y":
				sqlOperation.upDate(u);
				break;
			default:
				menu(u.getName());
				break;
		}
	}
}
