package com.xjtu.bos.service.bc;

import java.util.List;

import com.xjtu.bos.domain.bc.Region;
import com.xjtu.bos.page.PageQuery;


/**
 * 区域管理业务接口
 * @author hanmeina
 *
 */
public interface RegionService extends PageQuery{
   /**
    * 保存区域信息
    * @param region
    */
	void save(Region region);
    /**
     * 查询所有区域
     * @return
     */
    List<Region> findAllRegions();
  
}
