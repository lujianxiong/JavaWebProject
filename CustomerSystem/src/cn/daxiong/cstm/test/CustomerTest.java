package cn.daxiong.cstm.test;

import org.junit.jupiter.api.Test;

import cn.daxiong.cstm.dao.CustomerDao;
import cn.daxiong.cstm.domain.Customer;
import cn.itcast.commons.CommonUtils;

public class CustomerTest {
	
	@Test
	public void fun1() {
		CustomerDao customerDao = new CustomerDao();
		for(int i = 1;i <= 300;i++) {
			Customer c = new Customer();
			c.setCid(CommonUtils.uuid());
			c.setCname("cstm_"+i);
			c.setBirthday("2020-08-24");
			c.setGender(i%2==0?"male":"female");
			c.setCellphone("173"+i);
			c.setEmail("cstm_"+i+"@163.com");
			c.setDescription("我是客户");
			
			customerDao.add(c);
		}
		
	}

}
