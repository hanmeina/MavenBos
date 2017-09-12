package com.xjtu.bos.service.bc;

import java.util.List;

import com.xjtu.bos.domain.bc.Standard;
import com.xjtu.bos.page.PageQuery;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;

/**
 * 取派业务接口
 * @author hanmeina
 *
 */
public interface StandardService extends PageQuery{
    /**
     * 添加取派标准
     * @param standard
     */
	void save(Standard standard);
//    /**
//     * 根据分页请求，查询分页响应
//     * @param pageRequestBean
//     * @return PageResponseBean
//     */
//	PageResponseBean pageQuery(PageRequestBean pageRequestBean);
	/**
	 * 批量删除
	 * @param ids
	 */
	void delBatch(String[] ids);
	/**
	 * 查询可以使用的标准，delTag=0
	 * @return
	 */
	List<Standard> ajaxList();
    
}
