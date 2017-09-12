package com.xjtu.bos.web.action.bc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xjtu.bos.domain.bc.Subarea;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.utils.FileUtils;
import com.xjtu.bos.web.action.base.BaseAction;

public class SubareaAction extends BaseAction implements ModelDriven<Subarea> {
     private Subarea subarea = new Subarea();
	@Override
	public Subarea getModel() {
		// TODO Auto-generated method stub
		return subarea;
	}
	/**
	 * 保存或者修改分区
	 * @return
	 */
	public String saveOrUpdate(){
		subareaService.saveOrUpdate(subarea);
		return "saveOrUpdateSUCCESS";
	}
	/**
	 * 分页查询分区
	 * @return
	 */
	public String pageQuery(){
		DetachedCriteria  detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		       // 针对QBC添加查询条件
				if (subarea.getAddresskey() != null && subarea.getAddresskey().trim().length() > 0) {
					// 添加关键字条件
					detachedCriteria.add(Restrictions.like("addresskey", "%" + subarea.getAddresskey() + "%"));
				}
				if (subarea.getDecidedzone() != null && subarea.getDecidedzone().getId() != null && subarea.getDecidedzone().getId().trim().length() > 0) {
					// 输入定区编号
					detachedCriteria.add(Restrictions.eq("decidedZone", subarea.getDecidedzone()));// 比较对象，实际上比较定区id 属性
				}
				if (subarea.getRegion() != null) {
					// 表关联，QBC解决方案 --- 别名
					detachedCriteria.createAlias("region", "r");
					if (subarea.getRegion().getProvince() != null && subarea.getRegion().getProvince().trim().length() > 0) {
						detachedCriteria.add(Restrictions.like("r.province", "%" + subarea.getRegion().getProvince() + "%"));
					}
					if (subarea.getRegion().getCity() != null && subarea.getRegion().getCity().trim().length() > 0) {
						detachedCriteria.add(Restrictions.like("r.city", "%" + subarea.getRegion().getCity() + "%"));
					}
					if (subarea.getRegion().getDistrict() != null && subarea.getRegion().getDistrict().trim().length() > 0) {
						detachedCriteria.add(Restrictions.like("r.district", "%" + subarea.getRegion().getDistrict() + "%"));
					}
				}
		 
		PageRequestBean pageRequestBean = init(detachedCriteria);
		
		PageResponseBean pageResponseBean = subareaService.pageQuery(pageRequestBean);
				
		//System.out.println("total:=============="+pageResponseBean.getTotal());
		ActionContext.getContext().getSession().put("pageResponseBean", pageResponseBean);
		//放入值栈
       
		ActionContext.getContext().put("pageResponseBean","pageResponseBean");
		//ActionContext.getContext().getValueStack().push("pageResponseBean");
		ActionContext.getContext().getValueStack().set("pageResponseBean", pageResponseBean);
		return "pageQuerySUCCESS";
	}
	
	  /**
	   * 数据查询结果导出
	   * @return
	 * @throws IOException 
	   */
	  public  String  exportXls() throws IOException{
		  //对文件名编码
		  String downloadFileName = "分区数据.xls";
		  String agent  = ServletActionContext.getRequest().getHeader("user-agent");
		  //对下载文件名编码
		  downloadFileName = FileUtils.encodeDownloadFilename(downloadFileName, agent);
		  //放入值栈
		  ActionContext.getContext().put("downloadFileName", downloadFileName);
		  return "exportXlsSUCCESS";
	  }
	  
	  //提供文件下载流
	  public InputStream getInputStream() throws IOException{
		  //将session中缓存的PageResponseBean中的数据，生成excel
		 PageResponseBean pageResponseBean = (PageResponseBean) ActionContext.getContext().getSession().get("pageResponseBean");
		 List<Subarea> subareas = pageResponseBean.getRows();
		 //工作薄
		 HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		 //sheet
		 HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
		// 先写标题行
			HSSFRow headRow = sheet.createRow(0);// 第一行 （标题行）
			headRow.createCell(0).setCellValue("分区编号");
			headRow.createCell(1).setCellValue("关键字");
			headRow.createCell(2).setCellValue("起始号");
			headRow.createCell(3).setCellValue("结束号");
			headRow.createCell(4).setCellValue("是否区分单双号号");
			headRow.createCell(5).setCellValue("位置信息");

			// 向excel写数据
			for (Subarea subarea : subareas) {
				// 每个分区一行
				HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
				dataRow.createCell(0).setCellValue(subarea.getId());
				dataRow.createCell(1).setCellValue(subarea.getAddresskey());
				dataRow.createCell(2).setCellValue(subarea.getStartnum());
				dataRow.createCell(3).setCellValue(subarea.getEndnum());
				dataRow.createCell(4).setCellValue(subarea.getSingle());
				dataRow.createCell(5).setCellValue(subarea.getPosition());
			}

			// 将数据缓存到字节数组
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			hssfWorkbook.write(arrayOutputStream);
			arrayOutputStream.close();
			byte[] data = arrayOutputStream.toByteArray();

			// 再通过字节数组输入流读取数据
			return new ByteArrayInputStream(data);
	  }
      /**
       * 查询所有未关联定区的分区
       * @return
       */
	  public String findnoassociation(){
		  List<Subarea>  subareas = subareaService.findnoassociation();
		  ActionContext.getContext().put("subareas", subareas);
		  return "findnoassociationSUCCESS";
	  }
}
