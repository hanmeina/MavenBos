package com.xjtu.bos.service.impl.bc;

import com.xjtu.bos.domain.bc.Decidedzone;
import com.xjtu.bos.domain.bc.Subarea;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.service.base.BaseService;
import com.xjtu.bos.service.bc.DecidedzoneService;

public class DecidedzoneServiceImpl extends BaseService implements DecidedzoneService{
   /**
    * 保存或修改定区
    */
	@Override
	public void saveOrUpdate(Decidedzone decidedzone, String[] subareaids) {
		// TODO Auto-generated method stub
		//保存定区数据
		decidedzoneDao.save(decidedzone);
		
		for(String id :subareaids){
			//多方关联一方
			Subarea subarea = subareaDao.findById(id);//分区持久对象
			subarea.setDecidedzone(decidedzone);
			
		}
	}
/**
 * 分页查询定区
 */
@Override
public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
	// TODO Auto-generated method stub
	return pageQuery(pageRequestBean, decidedzoneDao);
}

}
