package com.xjtu.bos.web.action.qp;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.xjtu.bos.domain.qp.NoticeBill;
import com.xjtu.bos.domain.user.User;
import com.xjtu.bos.web.action.base.BaseAction;
/**
 * 业务通知单
 * @author hanmeina
 *
 */
public class NoticeBillAction extends BaseAction implements ModelDriven<NoticeBill> {
   //模型驱动
	private NoticeBill noticeBill = new NoticeBill();
	
	@Override
	public NoticeBill getModel() {
		// TODO Auto-generated method stub
		return noticeBill;
	}
    
   /**
    * 新增工作单
    * @return
    */
	public String save(){
		//完善model信息
		User user= (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		noticeBill.setUser(user);
		//调用业务层，完成通知单新增
		noticeBillService.saveNoticeBill(noticeBill);
		
		return "saveSUCCESS";
	}
}
