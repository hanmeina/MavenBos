package com.xjtu.bos.web.action.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xjtu.bos.domain.user.User;
import com.xjtu.bos.web.action.base.BaseAction;
/**
 * 用户管理
 * @author hanmeina
 *
 */
public class UserAction extends BaseAction implements ModelDriven<User>{
    
	private User user = new User(); 
	//模型驱动
	public User getModel() {
		return user;
	}
	/**
	 * 修改密码
	 * @return
	 */
	public String editPassword(){
		System.out.println("editPassword");
		User loginUser = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		System.out.println("user"+user.getId());
		user.setId(loginUser.getId());	
		try{	    
		userService.editPassword(user);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("msg", "密码修改成功");
		//将map放入值栈，为了使用struts2自带的json plugin
		ActionContext.getContext().put("map", map);
		}catch(Exception e){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", "failure");
			map.put("msg", "密码修改失败");
			ActionContext.getContext().put("map", map);
		}
		return "editPasswordSuccess";
	}

}
