package com.xjtu.bos.service.bc;

import java.util.List;

import com.xjtu.bos.domain.bc.Subarea;
import com.xjtu.bos.page.PageQuery;
/**
 * 分区接口
 * @author Administrator
 *
 */
public interface SubareaService extends PageQuery {
    /**
     * 保存或修改分区
     * @param subarea
     */
	void saveOrUpdate(Subarea subarea);
    /**
     * 查询所有未关联定区的分区
     * @return
     */
	List<Subarea> findnoassociation();
 
}
