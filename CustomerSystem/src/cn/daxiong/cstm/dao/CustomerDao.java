package cn.daxiong.cstm.dao;
import java.util.ArrayList;
import java.util.List;

//持久层
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import cn.daxiong.cstm.domain.Customer;
import cn.itcast.jdbc.TxQueryRunner;

public class CustomerDao {
	private QueryRunner qr= new TxQueryRunner();
	
	//添加客户
	public void add(Customer c) {
		try {
			String sql = "INSERT INTO t_customer VALUES(?,?,?,?,?,?,?)";
			Object[] params = {c.getCid(),c.getCname(),c.getGender(),c.getBirthday(),
					c.getCellphone(),c.getEmail(),c.getDescription()};
			qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//查询所有
	public List<Customer> findAll() {
		try {
			String sql = "SELECT * FROM t_customer";
			return qr.query(sql, new BeanListHandler<Customer>(Customer.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//根据cid查询客户信息
	public Customer findByCid(String cid) {
		try {
			String sql = "SELECT * FROM t_customer where cid=?";
			return qr.query(sql, new BeanHandler<Customer>(Customer.class),cid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//编辑客户
	public void edit(Customer c) {
		try {              //sql语句中的字段名要和prams数组中的参数对应
			String sql = "UPDATE t_customer SET cname=?,gender=?,birthday=?,"  //这里当初少了一个逗号！
					+ "cellphone=?,email=?,description=? WHERE cid=?";
			Object[] params = {c.getCname(),c.getGender(),c.getBirthday(),
					c.getCellphone(),c.getEmail(),c.getDescription(),c.getCid()};
			qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//删除客户
	public void delete(String cid) {
		try {
			String sql = "DELETE FROM t_customer WHERE cid=?";
			qr.update(sql,cid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//多条件组合查询
	public List<Customer> query(Customer creteria) {
		try {
			//1、给出sql模板
			//	1）给出sql语句的前半部分                                用一个1=1费条件把WHERE占了，省得后面WHERE不知道放哪
			StringBuilder sql = new StringBuilder("SELECT * FROM t_customer WHERE 1=1");
			//	2）判断条件，向sql语句中追加where子句
			//2、给出参数，创建一个ArrayList，用来装载参数值
			//判断完，追加了子句后，紧接着添加参数，保证?和参数是对应的
			List<Object> params = new ArrayList<Object>();
			String cname = creteria.getCname();
			if (cname != null && !cname.trim().isEmpty()) {
				sql.append(" AND cname like ?");    //cname采用模糊查询
				params.add("%" + cname + "%");    //做模糊查询的时候，前后需要加%
			}
			String gender = creteria.getGender();
			if (gender != null && !gender.trim().isEmpty()) {
				sql.append(" AND gender=?");
				params.add(gender);
			}
			String cellphone = creteria.getCellphone();
			if (cellphone != null && !cellphone.trim().isEmpty()) {
				sql.append(" AND cellphone like ?");
				params.add("%" + cellphone + "%");
			}
			String email = creteria.getEmail();
			if (email != null && !email.trim().isEmpty()) {
				sql.append(" AND email like ?");
				params.add("%" + email + "%");
			}
			//3、调用query方法，使用BeanListHandler处理器
			//sql应该是字符串，我们把StringBuilder转换成String类型         params应该是数组，我们把list集合转换一下
			return qr.query(sql.toString(), 
						new BeanListHandler<Customer>(Customer.class),
						params.toArray());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
}
