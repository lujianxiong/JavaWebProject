package cn.daxiong.bookstore.user.service;

//User业务层
import cn.daxiong.bookstore.user.dao.UserDao;
import cn.daxiong.bookstore.user.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();

	// 注册功能
	public void regist(User form) throws UserException {
		User user= null;
		
		// 校验用户名
		user = userDao.findByUsername(form.getUsername());
		if (user != null)
			throw new UserException("用户名已被注册!");

		// 校验email
		user = userDao.findByEmail(form.getEmail());
		if (user != null)
			throw new UserException("Email已被注册!");
		
		// 插入用户到数据库中
		userDao.add(form);
	}
	
	// 激活功能
	public void active(String code) throws UserException {
		// 1、使用code查询数据库，得到User
		User user = userDao.findByCode(code);
		// 2、如果User不存在，则激活码错误！
		if (user == null) throw new UserException("激活码无效！");
		// 3、校验用户的状态是否为未激活状态，如果已激活，说明是二次激活，抛出异常！
		if (user.isState()) throw new UserException("您已经激活成功，请勿重复激活！除非你想死！");
		// 4、到这里说明激活码是未激活的
		// 修改用户状态
		userDao.updateState(user.getUid(), true);
	}
	
	// 登录功能
	public User login(User form) throws UserException {
		// 1、使用username查询，得到user
		User user = userDao.findByUsername(form.getUsername());
		// 2、如果user为null，抛出异常（用户名不存在！）
		if (user == null) throw new UserException("用户名不存在！");
		// 3、比较form和user的password，若不同，抛出异常（密码错误！）
		if (!form.getPassword().equals(user.getPassword())) throw new UserException("密码错误！");
		// 4、查看用户的状态，若为false，抛出异常（该帐号未激活！）
		if (user.isState() == false) throw new UserException("该帐号尚未激活！");
		// 5、返回user
		return user;
	}
	
	

}
