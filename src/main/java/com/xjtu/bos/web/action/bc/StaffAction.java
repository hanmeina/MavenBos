package com.xjtu.bos.web.action.bc;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xjtu.bos.domain.bc.Staff;
import com.xjtu.bos.domain.bc.Standard;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.web.action.base.BaseAction;

/**
 * 取派员信息管理
 * @author hanmeina
 *
 */
public class StaffAction extends BaseAction implements ModelDriven<Staff> {
    //模型驱动
	private Staff  staff = new Staff();
	
	public Staff getModel() {
		// TODO Auto-generated method stub
		return staff;
	}
	/**
	 * 保存和修改
	 * @return
	 */
     public String saveOrUpdate(){
    	 //satff对象中关联游离状态Standard对象（只有id）
    	 System.out.println("id:"+staff.getId());
    	 System.out.println("standardid:"+staff.getStandard().getId());
    	 staffService.saveOrUpdate(staff);
    	 return "saveOrUpdateSUCCESS";
    	 
     }
     /**
      * 分页查询
      * @return
      */
     public String  pageQuery(){
    	 DetachedCriteria  detachedCriteria = DetachedCriteria.forClass(Staff.class);
    	 PageRequestBean pageRequestBean = init(detachedCriteria); 	 
    	 PageResponseBean  pageResponseBean = staffService.pageQuery(pageRequestBean);
    	 //放入值栈
    	 ActionContext.getContext().put("pageResponseBean", pageResponseBean);
    	 return "pageQuerySUCCESS";
     }
     /**
      * 批量作废
      * @return
      */
     public String  delBatch(){
    	 System.out.println(staff.getId());
    	 String[] ids = staff.getId().split(", ");
    	 staffService.delBatch(ids);
    	 return "delBatchSUCCESS";
     }
     
     
     /**
      * 批量还原
      * @return
      */
     public String  restoreBatch(){
    	 System.out.println(staff.getId());
    	 String[] ids = staff.getId().split(", ");
    	 staffService.restoreBatch(ids);
    	 return "delBatchSUCCESS";
     }
     
     /**
      * 查询所有取派员
      * @return
      */
    public String  staffList(){
    List<Staff> staffList = staffService.findAll();
    ActionContext.getContext().put("staffList", staffList);	
    	return "staffListSUCCESS";
    }
     
}
