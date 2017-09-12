package com.xjtu.bos.service.impl.bc;

import java.util.List;

import com.xjtu.bos.domain.bc.Subarea;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.service.base.BaseService;
import com.xjtu.bos.service.bc.SubareaService;
/**
 * 分区实现类
 * @author Administrator
 *
 */
public class SubareaServiceImpl extends BaseService implements SubareaService {
   /**
    * 保存或修改分区
    */
	@Override
	public void saveOrUpdate(Subarea subarea) {
		// TODO Auto-generated method stub
		subareaDao.insertOrUpdate(subarea);
	}
/**
 * 分页查询分区数据
 */
@Override
public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
	// TODO Auto-generated method stub	
	return pageQuery(pageRequestBean, subareaDao);
}
/**
 * 查询所有未关联定区的分区
 */
@Override
public List<Subarea> findnoassociation() {
	// TODO Auto-generated method stub
	return subareaDao.findByNamedQuery("subarea.findnoassociation");
}

}
