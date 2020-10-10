package cn.daxiong.bookstore.user.dao;

//User持久层
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.daxiong.bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();

	// 按用户名查询
	public User findByUsername(String username) {
		try {
			String sql = "SELECT * FROM tb_user WHERE username=?";
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 按邮箱查询
	public User findByEmail(String email) {
		try {
			String sql = "SELECT * FROM tb_user WHERE email=?";
			return qr.query(sql, new BeanHandler<User>(User.class), email);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 插入User
	public void add(User user) {
		try {
			String sql = "INSERT INTO  tb_user VALUES(?,?,?,?,?,?)";
			Object[] params = { user.getUid(), user.getUsername(), user.getPassword(), user.getEmail(), user.getCode(),
					user.isState() };
			qr.update(sql, params);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 按激活码code查询
	public User findByCode(String code) {
		try {
			String sql = "SELECT * FROM tb_user WHERE code=?";
			return qr.query(sql, new BeanHandler<User>(User.class), code);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	// 修改用户的状态码state
	public void updateState(String uid, boolean state) {
		try {
			String sql = "UPDATE tb_user SET state=? WHERE uid=?";
			qr.update(sql,state,uid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	

}
