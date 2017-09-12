package com.xjtu.bos.service.base;
import java.util.List;


import javax.annotation.Resource;











import com.xjtu.bos.dao.GenericDao;
import com.xjtu.bos.domain.bc.Decidedzone;
import com.xjtu.bos.domain.bc.Region;
import com.xjtu.bos.domain.bc.Staff;
import com.xjtu.bos.domain.bc.Standard;
import com.xjtu.bos.domain.bc.Subarea;
import com.xjtu.bos.domain.qp.NoticeBill;
import com.xjtu.bos.domain.qp.WorkBill;
import com.xjtu.bos.domain.qp.WorkOrderManage;
import com.xjtu.bos.domain.user.User;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;

import com.xjtu.crm.service.CustomerService;

/**
 * 公共抽象Service(实现代码复用)
 * @author hanmeina
 *
 */
public abstract class BaseService{
  @Resource(name="userDao")
  protected GenericDao<User> userDao;
  @Resource(name="standardDao")
  protected GenericDao<Standard> standardDao;
  @Resource(name="staffdDao")
  protected GenericDao<Staff> staffdDao;
  @Resource(name="regionDao")
  protected GenericDao<Region> regionDao;
  @Resource(name="subareaDao")
  protected GenericDao<Subarea> subareaDao;
  @Resource(name="decidedzoneDao")
  protected GenericDao<Decidedzone> decidedzoneDao;
  
  @Resource(name="noticeBillDao")
  protected GenericDao<NoticeBill> noticeBillDao;
  @Resource(name="workBillDao")
  protected GenericDao<WorkBill> workBillDao;
  
  @Resource(name="workOrderManageDao")
  protected GenericDao<WorkOrderManage> workOrderManageDao;
  
  @Resource(name="customerService")
  protected CustomerService customerService;
  
  /**
   * 分页查询
   * @param pageRequestBean
   * @param dao
   * @return
   */
  public <T> PageResponseBean pageQuery(PageRequestBean pageRequestBean,GenericDao<T> dao) {
		// TODO Auto-generated method stub
		PageResponseBean  pageResponseBean = new PageResponseBean();
		//查询total
	
		
		//pageRequestBean.getDetachedCriteria().setProjection(null);//清除投影
		
		//查询rows
		int firstResult = (pageRequestBean.getPage()-1)*pageRequestBean.getRows();
		int maxResults = pageRequestBean.getRows();	
		List<T> rows = dao.pageQuery(pageRequestBean.getDetachedCriteria(), firstResult, maxResults);
		pageResponseBean.setRows(rows);	
		
		// 满足当前条件，记录总条数
		long total = dao.findTotalCount(pageRequestBean.getDetachedCriteria());
		pageResponseBean.setTotal(total);
		return pageResponseBean;
	}
}
