package com.xjtu.bos.service.impl.bc;

import java.util.List;

import com.xjtu.bos.domain.bc.Standard;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.service.base.BaseService;
import com.xjtu.bos.service.bc.StandardService;

/**
 * 取派业务实现
 * @author hanmeina
 *
 */
public class StandardServiceImpl extends BaseService  implements StandardService{
    /**
     * 添加或者修改取派标准
     */
	public void save(Standard standard) {
		standardDao.insertOrUpdate(standard);
	}
    /**
     * 根据分页请求，查询分页响应
     */
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
	  return pageQuery(pageRequestBean, standardDao);
	}
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delBatch(String[] ids) {
		// TODO Auto-generated method stub
		for(String id:ids){
			Standard standard = standardDao.findById(id);
			standard.setDeltag("1");
		}
	}
	/**
	 * 查询可以使用的取派标准（固定条件查询使用NamedQuery）
	 */
	public List<Standard> ajaxList() {
		// TODO Auto-generated method stub
		return standardDao.findByNamedQuery("Standard_ajaxList");
	}
	

}
