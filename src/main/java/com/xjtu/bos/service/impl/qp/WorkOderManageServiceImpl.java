package com.xjtu.bos.service.impl.qp;

import org.hibernate.criterion.DetachedCriteria;

import com.xjtu.bos.domain.qp.WorkOrderManage;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.service.base.BaseService;
import com.xjtu.bos.service.qp.WorkOderManageService;


/**
 * 工作单业务接口实现
 * @author Administrator
 *
 */
public class WorkOderManageServiceImpl extends BaseService implements WorkOderManageService{
     /**
      * 保存工作单
      */
	@Override
	public void saveOrUpdate(WorkOrderManage workOderManage) {
		// TODO Auto-generated method stub
		workOrderManageDao.insertOrUpdate(workOderManage);
	}



	@Override
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		// TODO Auto-generated method stub
		return pageQuery(pageRequestBean, workOrderManageDao);
	}



	@Override
	public PageResponseBean queryByLucene(String conditionName,
			String conditionValue, int page, int rows) {
		// TODO Auto-generated method stub
		return  workOrderManageDao.queryByLucene(conditionName,conditionValue,page,rows);
	}

}
