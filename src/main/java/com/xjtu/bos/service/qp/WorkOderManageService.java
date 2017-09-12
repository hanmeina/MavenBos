package com.xjtu.bos.service.qp;

import org.hibernate.criterion.DetachedCriteria;

import com.xjtu.bos.domain.qp.WorkOrderManage;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;



/**
 * 工作单接口
 * @author Administrator
 *
 */
public interface WorkOderManageService {
   //保存工作单信息
	void saveOrUpdate(WorkOrderManage workOderManage);
   //分页查询
	PageResponseBean pageQuery(PageRequestBean pageRequestBean);
	PageResponseBean queryByLucene(String conditionName, String conditionValue,
			int page, int rows);
    
	

}
