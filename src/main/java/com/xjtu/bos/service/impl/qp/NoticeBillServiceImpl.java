package com.xjtu.bos.service.impl.qp;

import java.sql.Timestamp;
import java.util.List;

import com.xjtu.bos.domain.bc.Decidedzone;
import com.xjtu.bos.domain.bc.Subarea;
import com.xjtu.bos.domain.qp.NoticeBill;
import com.xjtu.bos.domain.qp.WorkBill;
import com.xjtu.bos.service.base.BaseService;
import com.xjtu.bos.service.qp.NoticeBillService;
/**
 * 业务通知单接口实现
 * @author hanmeina
 *
 */
public class NoticeBillServiceImpl extends  BaseService  implements NoticeBillService {

	@Override
	public void saveNoticeBill(NoticeBill noticeBill) {
		// TODO Auto-generated method stub
		//保存到数据库
		noticeBillDao.save(noticeBill);
		
		//自动分单
		//1.使用当前取件地址,去crm中查定区编码
		
		String decideZoneId = customerService.findDecidedZoneIdByCustomerAddress(noticeBill.getPickaddress());
		if(decideZoneId == null){
		//未查到
	    //2.匹配分区,关键字
		String[]  addressArray = noticeBill.getPickaddress().split(" ");//北京市海淀区 xx路 1号楼XXX
		if(addressArray.length>=2){
		String addresskey = 	addressArray[1];//取第二个元素作为关键字
		   List<Subarea> list =  subareaDao.findByNamedQuery("subarea.findByAddressKey", addresskey);
		   //只匹配到一个分区，而且关联到定区 
		   if(list.size()==1 && list.get(0).getDecidedzone()!=null){
			 //1.查到（自动分单成功）
				Decidedzone	decideZone =  list.get(0).getDecidedzone();
				//通知单信息
				noticeBill.setStaff(decideZone.getStaff());
				noticeBill.setOrdertype("自动");
				//工单信息
				 WorkBill workBill  = new WorkBill();
				 workBill.setNoticeBill(noticeBill);
				 workBill.setStaff(decideZone.getStaff());
				 workBill.setType("新");
				 workBill.setPickstate("新单");
				 workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
				 workBill.setAttachbilltimes(0);
				 workBill.setRemark(noticeBill.getRemark());
				 workBillDao.save(workBill);	    	
		    }else{
		    	//人工调度
		    	noticeBill.setOrdertype("人工");
		    }
		}else{
			//人工调度
			noticeBill.setOrdertype("人工");
		}
			
			
		}else{
	    //1.查到（自动分单成功）
		Decidedzone	decideZone =  decidedzoneDao.findById(decideZoneId);
		//通知单信息
		noticeBill.setStaff(decideZone.getStaff());
		noticeBill.setOrdertype("自动");
		//工单信息
		 WorkBill workBill  = new WorkBill();
		 workBill.setNoticeBill(noticeBill);
		 workBill.setStaff(decideZone.getStaff());
		 workBill.setType("新");
		 workBill.setPickstate("新单");
		 workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
		 workBill.setAttachbilltimes(0);
		 workBill.setRemark(noticeBill.getRemark());
		 workBillDao.save(workBill);
		}
		
	}

}
