package com.xjtu.bos.service.impl.user;



import java.util.List;

import com.xjtu.bos.domain.user.User;
import com.xjtu.bos.service.base.BaseService;
import com.xjtu.bos.service.user.UserService;
import com.xjtu.bos.utils.MD5Utils;
/**
 * 业务实现类
 * @author hanmeina
 *
 */
public class UserServiceImpl extends BaseService implements UserService {

    /**
     * 登录功能
     * @param user
     * @return User
     */
	public User login(User user) {
		List<User> list  = userDao.findByNamedQuery("User.login",user.getUsername(),MD5Utils.md5(user.getPassword()));
		return list.isEmpty()? null:list.get(0);
	}
    /**
     * 修改密码
     * @param user
     */
	public void editPassword(User user) {
	    //查询是否存在这个用户
		User exitUser  = userDao.findById(user.getId());
		System.out.println("exitUser:"+exitUser.getUsername());
		//修改密码
		exitUser.setPassword(MD5Utils.md5(user.getPassword()));
	}

}
