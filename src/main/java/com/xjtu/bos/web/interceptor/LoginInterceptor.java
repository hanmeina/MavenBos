package com.xjtu.bos.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xjtu.bos.domain.user.User;
/**
 * 登录拦截器
 * @author hanmeina
 *
 */
public class LoginInterceptor extends AbstractInterceptor {


	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取session中的user
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user==null){
			//没有登录
			// 设置错误信息
			ActionSupport action = (ActionSupport) invocation.getAction();
			action.addActionError("你还未登陆或者长时间未使用，请重新登陆！");
			return "login";// 登陆页面
		}else{
			// 已经登陆
			return invocation.invoke();
		}

	}

}
