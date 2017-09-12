package com.xjtu.bos.web.action.user;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ModelDriven;
import com.xjtu.bos.domain.user.User;
import com.xjtu.bos.web.action.base.BaseAction;

/**
 * 登录功能
 * @author hanmeina
 *
 * @param <User>
 */
public class LoginAction extends BaseAction implements ModelDriven<User>{
    private User user = new User();
	public User getModel() {
		
		return user;
	}
	
	//业务方法-- 登录逻辑
	public String execute(){
		//判断用户输入的验证码与session中的验证码是否一致
    String checkcodeSession = (String) ServletActionContext.getRequest().getSession().getAttribute("key");//validatecode.jsp放入session中是key
	if(checkcodeSession == null || !checkcodeSession.equals(checkcode)){
		//验证码失败
		this.addActionError("验证码错误！");
		return INPUT;
	}	
	//验证码成功，比较用户名和密码，调用业务层
	User loginUser = userService.login(user);
	if(loginUser == null){
		//登录失败
		this.addActionError("用户名或密码错误！");
		return INPUT;
	}else{
		//登录成功
		//将用户信息存入session
		ServletActionContext.getRequest().getSession().setAttribute("user", loginUser);
		return SUCCESS;
	}
  
	}
	
	//用户输入的验证码（checkcode不是user的属性，所以使用属性注入的方式）
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	
 
}
