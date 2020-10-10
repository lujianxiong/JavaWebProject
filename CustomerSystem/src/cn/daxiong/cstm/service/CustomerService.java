package cn.daxiong.cstm.service;
import java.util.List;

//业务层
import cn.daxiong.cstm.dao.CustomerDao;
import cn.daxiong.cstm.domain.Customer;

public class CustomerService {
	private CustomerDao customerDao = new CustomerDao();
	
	//添加客户
	public void add(Customer c) {
		customerDao.add(c);
	}
	
	//查询所有
	public List<Customer> findAll(){
		return customerDao.findAll();
	}

	//通过cid查询客户信息
	public Customer load(String cid) {
		return customerDao.findByCid(cid);
	}

	//编辑客户
	public void edit(Customer c) {
		customerDao.edit(c);
	}

	//删除客户
	public void delete(String cid) {
		customerDao.delete(cid);
	}

	//多条件组合查询
	public List<Customer> query(Customer creteria) {
		return customerDao.query(creteria);
	}

}
