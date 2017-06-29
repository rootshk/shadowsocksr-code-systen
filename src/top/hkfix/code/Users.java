package top.hkfix.code;

public class Users {
	private int id;				//用户id
	private int uid;			//用户类别
	private String name;		//用户名称
	private String newName;		//修改用户名称
	private String password;	//登录密码
	private String newPassword;	//修改登录密码
	private String passwd;		//连接密码
	private String newPasswd;	//修改连接密码
	private String email;		//用户邮箱
	private String newEmail;	//修改用户邮箱
	private int port;		//连接端口
	private int newPort;		//修改连接端口
	private String ever;		//所有流量
	private int u;				//上传流量
	private int d;				//下载流量
	private String code;			//邀请码
	private String mode;
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getEver() {
		return ever;
	}
	public void setEver(String ever) {
		this.ever = ever;
	}
	public int getU() {
		return u;
	}
	public void setU(int u) {
		this.u = u;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswd() {
		return newPasswd;
	}
	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}
	public String getNewEmail() {
		return newEmail;
	}
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}
	public int getNewPort() {
		return newPort;
	}
	public void setNewPort(int newPort) {
		this.newPort = newPort;
	}
}
