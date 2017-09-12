package com.xjtu.crm.hessian;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xjtu.crm.domain.Customer;
import com.xjtu.crm.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class HessianRemotingTest {
	@Autowired
	private CustomerService customerService;
//
//	@Test
//	public void demo1() {
//		// 查询未指定定区的客户
//		List<Customer> customers = customerService.findNoAssociationCustomers();
//		System.out.println(customers.size());
//		System.out.println(customers);
//	}

//	@Test
//	public void demo2() {
//		// 查询 指定定区关联的客户
//		List<Customer> customers = customerService.findHasAssociationCustomers("DQ001");
//		System.out.println(customers.size());
//		System.out.println(customers);
//	}
//
	@Test
	public void demo3() {
		// 将客户关联到定区上
		String[] customerIds = { "fdagf" };
		String decidedZoneId = "DQ001";

		customerService.assignedCustomerToDecidedZone(customerIds, decidedZoneId);
	}

}
