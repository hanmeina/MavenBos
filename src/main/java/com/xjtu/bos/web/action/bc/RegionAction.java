package com.xjtu.bos.web.action.bc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.criterion.DetachedCriteria;

import com.mysql.jdbc.log.Log;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xjtu.bos.domain.bc.Region;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;
import com.xjtu.bos.utils.PinYin4jUtils;
import com.xjtu.bos.web.action.base.BaseAction;
/**
 * 区域管理Action
 * @author hanmeina
 *
 */
public class RegionAction extends BaseAction  implements  ModelDriven<Region>{
    //使用log4j 日志记录器，记录日志
	private static final Logger LOG =  Logger.getLogger(RegionAction.class);
	
	//模型驱动
	Region region = new Region();

    @Override
	public Region getModel() {
		// TODO Auto-generated method stub
		return region;
	}
	/**
	 * 一键上传
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String importXls() throws FileNotFoundException, IOException{
		//解析excel
		HSSFWorkbook  hssfWorkbook  =  new HSSFWorkbook(new FileInputStream(upload));
	    HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
		for(Row row:sheet){
			if(row.getRowNum()==0){//第一行，表头，不解析
				continue;
			}
		  Region region = new Region();
		  String id = row.getCell(0).getStringCellValue();
		  if(id.trim().equals("")){
			  continue;
		  }
		  region.setId(id);
		  region.setProvince(row.getCell(1).getStringCellValue());
		  region.setCity(row.getCell(2).getStringCellValue());
		  region.setDistrict(row.getCell(3).getStringCellValue());	
		  region.setPostcode(row.getCell(4).getStringCellValue());
		  
			// 使用pinyin4j 生成简码和城市编码
			// 连接省市区
			String str = region.getProvince() + region.getCity() + region.getDistrict();
			str = str.replaceAll("省", "").replaceAll("市", "").replaceAll("区", "");

			// 使用pinyin4j生成简码
			String[] arr = PinYin4jUtils.getHeadByString(str); // [B,J,B,J,G,D]
			StringBuffer sb = new StringBuffer();
			for (String headChar : arr) {
				sb.append(headChar);
			}
			region.setShortcode(sb.toString()); // 简码

			// 生成城市编码
			region.setCitycode(PinYin4jUtils.hanziToPinyin(region.getCity().replaceAll("市",""), ""));
		  //保存region信息（导入失败怎么办？）
		  
		  try{
	 	  regionService.save(region);
		  }catch(Exception e){
			  //导入失败，记录日志
			  LOG.error("区域导入失败，编号："+region.getId(), e);
		  }	  

		}
		//返回json
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result","success");
		map.put("msg", "区域导入完成");
		ActionContext.getContext().put("map", map);
		return "importXlsSUCCESS";
	}
	
	
	//属性驱动
	private File upload;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	/**
	 * 分页查询
	 * @return
	 */
	public String pageQuery(){
	   DetachedCriteria  detachedCriteria = DetachedCriteria.forClass(Region.class);
	   PageRequestBean pageRequestBean = init(detachedCriteria);	   
	   PageResponseBean  pageResponseBean = regionService.pageQuery(pageRequestBean);	   
	   ActionContext.getContext().put("pageResponseBean", pageResponseBean);
	   return "pageQuerySUCCESS";
	}
	
	     // 业务方法 ---- 查询所有区域，转换json列表
		 public String ajaxlist() {
			 System.out.println("==============================hahhha");
			// 调用业务层，查询所有区域信息
			List<Region> regions = regionService.findAllRegions();

			// 将查询结果 转换 json格式
			ActionContext.getContext().put("regions", regions);// 压入struts2 值栈

			return "ajaxlistSUCCESS";
		}

}
