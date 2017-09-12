package com.xjtu.bos.web.action.bc;

import java.sql.Timestamp;
import java.util.List;

import javassist.expr.NewArray;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xjtu.bos.domain.bc.Standard;
import com.xjtu.bos.domain.user.User;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.web.action.base.BaseAction;

/**
 * 取派标准Action
 * @author hanmeina
 *
 */
public class StandardAction extends BaseAction implements ModelDriven<Standard> {
    private Standard standard = new Standard();
    //模型驱动
	public Standard getModel() {
		// TODO Auto-generated method stub
		return standard;
	}
	
	/**
	 * 添加取派
	 * @return
	 */
	public String save(){
		 User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		 standard.setUser(user);
		 standard.setUpdatetime(new Timestamp(System.currentTimeMillis()));
		 standardService.save(standard);
		return "successSave";
	}
	
	/**
	 * 分页查询取派标准
	 * @return
	 */
	public String pageQuery(){
		
	
		
		// 隐含条件，查询未删除标准
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Standard.class); // 查询标准表所有数据 from Standard
		detachedCriteria.add(Restrictions.eq("deltag", "0")); // eq 相等
		PageRequestBean pageRequestBean = init(detachedCriteria); 	 
		
		// 调用业务层
	    PageResponseBean pageResponseBean = standardService.pageQuery(pageRequestBean);
		
	    // 将分页查询结果数据，转换 json格式（放入值栈）
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
	    
	    
		return "pageQuerySuccess";
	}
	
	//属性驱动
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	/**
	 * 批量删除
	 * @return
	 */
	public String delBatch(){
		String[] ids =  standard.getId().split(", ");
		standardService.delBatch(ids);
		return "successSave";
	}
	//查询取派标准json列表
	public String standardList(){
		System.out.println("hahahah");   
	  List<Standard> standardList = standardService.ajaxList();
	  //将处理结果转换成json返回
	  System.out.println("bbbbb");  
	  ActionContext.getContext().put("standardList",standardList);
	  return "ajaxListSuccess"; 
	}

}
