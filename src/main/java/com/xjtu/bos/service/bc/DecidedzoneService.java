package com.xjtu.bos.service.bc;

import com.xjtu.bos.domain.bc.Decidedzone;
import com.xjtu.bos.page.PageRequestBean;
import com.xjtu.bos.page.PageResponseBean;

public interface DecidedzoneService {
    /**
     * 保存或修改定区
     * @param decidedzone
     * @param subareaid
     */
	void saveOrUpdate(Decidedzone decidedzone, String[] subareaids);
    /**
     * 分页查询定区
     * @param pageRequestBean
     * @return
     */
	PageResponseBean pageQuery(PageRequestBean pageRequestBean);

}
