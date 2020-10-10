package test.dao;

import org.junit.jupiter.api.Test;

import cn.daxiong.dao.UserDao;
import cn.daxiong.domain.User;

//UserDao的测试类
public class UserDaoTest {
	@Test
	public void testFindByUsername() {
		UserDao userDao = new UserDao();
		User user = userDao.findByUsername("鲁建雄");
		System.out.println(user);
	}
	
	@Test
	public void testAdd() {
		UserDao userDao = new UserDao();
		User user = new User();
		user.setUsername("鲁建雄");
		user.setPassword("lu1jian2xiong3");
		userDao.add(user);
	}
}
