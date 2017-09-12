package com.xjtu.bos.service.impl.bc;

import java.util.List;

import com.xjtu.bos.domain.bc.Staff;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.service.base.BaseService;
import com.xjtu.bos.service.bc.StaffService;
/**
 * 取派员管理实现
 * @author hanmeina
 *
 */
public class StaffServiceImpl extends BaseService implements StaffService{
    /**
     * 添加或修改取派员
     */
	public void saveOrUpdate(Staff staff) {
		// TODO Auto-generated method stub
		staffdDao.insertOrUpdate(staff);
	}
    
	/**
	 * 分页查询取派员信息
	 */
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean) {
		// TODO Auto-generated method stub
	    return pageQuery(pageRequestBean, staffdDao);
	}
    /**
     * 批量删除
     */
	public void delBatch(String[] ids) {
		// TODO Auto-generated method stub
		for(String id:ids){
			Staff staff = staffdDao.findById(id);
			staff.setDeltag("1");			
		}
	}

	public void restoreBatch(String[] ids) {
		// TODO Auto-generated method stub
		
		for(String id:ids){
			System.out.println("staffId:======================"+id);
			Staff staff = staffdDao.findById(id);
			staff.setDeltag("0");			
		}
	}
   
	/**
	 * 查询所有取派员
	 */
	@Override
	public List<Staff> findAll() {
		// TODO Auto-generated method stub
		
		return staffdDao.findByNamedQuery("findStaff");
	}

}
