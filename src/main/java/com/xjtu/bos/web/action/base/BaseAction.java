package com.xjtu.bos.web.action.base;


import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.xjtu.bos.domain.bc.Region;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.service.bc.DecidedzoneService;
import com.xjtu.bos.service.bc.RegionService;
import com.xjtu.bos.service.bc.StaffService;
import com.xjtu.bos.service.bc.StandardService;
import com.xjtu.bos.service.bc.SubareaService;
import com.xjtu.bos.service.qp.NoticeBillService;
import com.xjtu.bos.service.qp.WorkOderManageService;
import com.xjtu.bos.service.user.UserService;
import com.xjtu.crm.service.CustomerService;

/**
 * 公共抽象Action(实现代码复用)
 * @author hanmeina
 *
 */
public abstract class BaseAction extends ActionSupport {

	@Resource(name="userService")
	protected UserService userService;
	
	@Resource(name="standardService")
	protected StandardService  standardService;
	
	@Resource(name="staffService")
	protected StaffService  staffService;
	
	@Resource(name="regionService")
	protected RegionService  regionService;
	
	@Resource(name="subareaService")
	protected SubareaService  subareaService;
	
	@Resource(name="decidedzoneService")
	protected DecidedzoneService  decidedzoneService;
	
	@Resource(name="customerService")
	protected CustomerService  customerService;
	
	@Resource(name="noticeBillService")
	protected NoticeBillService  noticeBillService;
	
	@Resource(name="workOrderManageService")
	protected WorkOderManageService  workOrderManageService;
	/**
	 * 封装分页查询条件（初始化）
	 * @param detachedCriteria
	 * @return
	 */
	public PageRequestBean  init(DetachedCriteria detachedCriteria){
		// 封装分页查询条件对象
		 PageRequestBean pageRequestBean = new PageRequestBean();
    	 pageRequestBean.setPage(page);
    	 pageRequestBean.setRows(rows);
    	 
    	 pageRequestBean.setDetachedCriteria(detachedCriteria);
		 return pageRequestBean;
	}
	 //属性驱动
    protected int page;
    protected int rows;
	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
