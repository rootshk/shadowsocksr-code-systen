package top.hkfix.code;

import java.util.Scanner;

public class Sign {		//注册类
	static Users u = new Users();
	static Scanner sc = new Scanner(System.in);//接收控制台信息
	
	static void signMess(){
		System.out.print(
				  "-------------------------"
				+ "\n请检查你输入的信息是否正确！！！"
				+ "\n1.用户名：" + u.getNewName()
				+ "\n2.登录密码：" + u.getNewPassword()
				+ "\n3.连接密码：" + u.getNewPasswd()
				+ "\n4.邮箱：" + u.getNewEmail()
				+ "\n5.邀请码：" + u.getCode()
				+ "\n-------------------------"
				+ "\n|-信息确认无误后输入'0'将注册"
				+ "\n|-输入上方对应数字可修改对应数据，"
				+ "\n|-输入其他则重新注册！"
				+ "\n|-等待输入："
		);
		signOperation();
	}
	
	static void signOperation(){
		int sign = sc.nextInt();
		switch(sign){
			case 0:
				if(sqlOperation.insertSign(u)){
					System.out.println("注册成功");
					Login.valiDate();
				}else{
					System.out.println("注册失败");
					signUser();
				}
				break;
			case 1:
				System.out.println("更改用户名：");
				u.setName(sc.next());
				signMess();
				break;
			case 2:
				System.out.println("更改登录密码：");
				u.setPassword(sc.next());
				signMess();
				break;
			case 3:
				System.out.println("更改连接密码：");
				u.setPasswd(sc.next());
				signMess();
				break;
			case 4:
				System.out.println("更改邮箱地址：");
				u.setEmail(sc.next());
				signMess();
				break;
			case 5:
				System.out.println("修改邀请码：");
				u.setCode(sc.next());
				signMess();
				break;
			default:
				signUser();
				break;
		}
	}
	
	static void signUser(){
		System.out.println("-----欢迎注册|请输入注册信息-----");
		System.out.print("请设置用户名：");
		u.setNewName(sc.next());
		System.out.print("请设置登录密码：");
		u.setNewPassword(sc.next());
		System.out.print("请设置连接密码：");
		u.setNewPasswd(sc.next());
		System.out.print("请设置邮箱：");
		u.setNewEmail(sc.next());
		System.out.print("请输入邀请码：");
		u.setCode(sc.next());
		signMess();
		signOperation();
	}
}
