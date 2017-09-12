package com.xjtu.bos.service.impl.bc;

import java.util.List;

import com.xjtu.bos.domain.bc.Region;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.service.base.BaseService;
import com.xjtu.bos.service.bc.RegionService;

/**
 * 区域管理实现
 * @author hanmeina
 *
 */
public class RegionServiceImpl extends BaseService  implements RegionService{
    /**
     * 保存region信息
     */
	@Override
	public void save(Region region) {
		// TODO Auto-generated method stub
		regionDao.save(region);
	}
   /**
    * 分页查询
    */
	@Override
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		// TODO Auto-generated method stub
		return pageQuery(pageRequestBean, regionDao);
	}
   /**
    * 查询所有区域
    */
	@Override
	public List<Region> findAllRegions() {
		// TODO Auto-generated method stub
		return regionDao.findAll();
	}

}
