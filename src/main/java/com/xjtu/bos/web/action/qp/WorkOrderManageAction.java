package com.xjtu.bos.web.action.qp;

import java.util.HashMap;
import java.util.Map;







import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xjtu.bos.domain.qp.WorkOrderManage;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.web.action.base.BaseAction;

public class WorkOrderManageAction extends  BaseAction implements ModelDriven<WorkOrderManage>{
  private WorkOrderManage workOrderManage = new WorkOrderManage();
	@Override
	public WorkOrderManage getModel() {
		// TODO Auto-generated method stub
		return workOrderManage;
	}
	
	//修改和保存
 public String saveOrUpdate(){
	 try{
	 workOrderManageService.saveOrUpdate(workOrderManage);
	 Map<String,Object> map  = new HashMap<String,Object>();
	 map.put("result", "success");
	 map.put("msg", "保存成功");
	 ActionContext.getContext().put("map", map);
	 
	 }catch(Exception e){
		 e.printStackTrace();
		 Map<String,Object> map  = new HashMap<String,Object>();
		 map.put("result", "failure");
		 map.put("msg", "保存失败！异常原因"+e.getMessage());
		 ActionContext.getContext().put("map", map); 
	 }
	 return "saveOrUpdateSUCCESS";
 }
 public String pageQuery(){
	 if(conditionName!=null &&conditionName.trim().length()>0 && conditionValue!=null && conditionValue.trim().length()>0){
		//有条件，结合lucene索引库查询 
		// System.out.println("conditionValue:"+conditionValue);
		 PageResponseBean  pageResponseBean = workOrderManageService.queryByLucene(conditionName,conditionValue,page,rows);
	     ActionContext.getContext().put("pageResponseBean", pageResponseBean);
	 }else{
		 //无条件查询
	 DetachedCriteria  detachedCriteria  = DetachedCriteria.forClass(WorkOrderManage.class);
	 PageRequestBean  pageRequestBean  = init(detachedCriteria);
	 PageResponseBean  pageResponseBean = workOrderManageService.pageQuery(pageRequestBean);
     ActionContext.getContext().put("pageResponseBean", pageResponseBean);
	 }
	 return "pageQuerySUCCESS";
 }
 //属性驱动
 private String conditionName;
 private String conditionValue;
public void setConditionName(String conditionName) {
	this.conditionName = conditionName;
}

public void setConditionValue(String conditionValue) {
	this.conditionValue = conditionValue;
}
 
}
