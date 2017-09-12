package com.xjtu.bos.page;



public interface PageQuery{
	
	/**
	 * 分页查询
	 * @param pageRequestBean
	 * @return
	 */
	public PageResponseBean pageQuery(PageRequestBean pageRequestBean);
  
}
