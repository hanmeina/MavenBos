package com.xjtu.bos.service.user;

import com.xjtu.bos.domain.user.User;

/**
 * 用户管理业务接口
 * @author hanmeina
 *
 */
public interface UserService {
   /**
    * 用户登录
    * @param user
    * @return
    */
   public User login(User user);
   /**
    * 修改密码
    * @param user
    */
   public void editPassword(User user);

}
