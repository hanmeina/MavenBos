package com.xjtu.bos.service.qp;

import com.xjtu.bos.domain.qp.NoticeBill;

/**
 * 业务通知单接口
 * @author hanmeina
 *
 */
public interface NoticeBillService {
   /**
    * 新增业务通知单
    * @param noticeBill
    */
	void saveNoticeBill(NoticeBill noticeBill);

}
