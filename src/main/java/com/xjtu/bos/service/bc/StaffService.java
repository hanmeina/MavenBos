package com.xjtu.bos.service.bc;

import java.util.List;

import com.xjtu.bos.domain.bc.Staff;
import com.xjtu.bos.page.PageQuery;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;

/**
 * 取派员业务接口
 * @author hanmeina
 *
 */
public interface StaffService extends PageQuery{
    
	/**
	 * 修改或保存取派员
	 * @param staff
	 */
	void saveOrUpdate(Staff staff);
//    /**
//     * 分页查询取派员信息
//     * @param pageRequestBean
//     * @return
//     */
//	PageResponseBean pageQuery(PageRequestBean pageRequestBean);
     /**
      * 批量删除
      * @param ids
      */
	void delBatch(String[] ids);
	 /**
     * 批量还原
     * @param ids
     */
	void restoreBatch(String[] ids);
	/**
	 * 查询所有取派员
	 * @return
	 */
	List<Staff> findAll();
   
}
