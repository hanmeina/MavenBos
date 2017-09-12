package com.xjtu.bos.web.action.bc;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xjtu.bos.domain.bc.Decidedzone;
import com.xjtu.bos.domain.bc.Staff;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.web.action.base.BaseAction;
import com.xjtu.crm.domain.Customer;

public class DecidedzoneAction extends BaseAction implements ModelDriven<Decidedzone>{
  private Decidedzone decidedzone = new Decidedzone();
	@Override
	public Decidedzone getModel() {
		// TODO Auto-generated method stub
		return decidedzone;
	}
    /**
     * 保存或修改定区
     * @return
     */
	public String saveOrUpdate(){
		 decidedzoneService.saveOrUpdate(decidedzone,subareaid);
		
		return "saveOrUpdateSUCCESS";
	}
	//分页查询
	public String pageQuery(){
		 DetachedCriteria  detachedCriteria = DetachedCriteria.forClass(Decidedzone.class);
    	 PageRequestBean pageRequestBean = init(detachedCriteria); 	 
    	 PageResponseBean  pageResponseBean = decidedzoneService.pageQuery(pageRequestBean);
    	 //放入值栈
    	 ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		
		return "pageQuerySUCCESS";
	}
	
	
	// 业务方法 --- 查询未关联定区的客户
		public String findNoAssociationCustomers() {
			// 调用Hessian 获得远程列表
			List<Customer> customers = customerService.findNoAssociationCustomers();
			// 转换为json
			ActionContext.getContext().put("customers", customers);

			return "findNoAssociationCustomersSUCCESS";
		}

		// 业务方法 -- 查询已经关联定区的客户
		public String findHasAssociationCustomers() {
			// 调用Hessian 获得远程列表
			List<Customer> customers = customerService.findHasAssociationCustomers(decidedzone.getId());
			// 转换为json
			ActionContext.getContext().put("customers", customers);

			return "findHasAssociationCustomersSUCCESS";
		}
		// 业务方法 --- 关联客户到定区
		public String assignedCustomerToDecidedZone() {
			// 指定客户到定区
			customerService.assignedCustomerToDecidedZone(customerIds, decidedzone.getId());
			return "assignedCustomerToDecidedZoneSUCCESS";
		}

	
	//属性驱动分区
	private String[] subareaid;
	public String[] getSubareaid() {
		return subareaid;
	}

	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	// 属性驱动接收多个客户id
		private String[] customerIds;

		public void setCustomerIds(String[] customerIds) {
			this.customerIds = customerIds;
		}

	
}
