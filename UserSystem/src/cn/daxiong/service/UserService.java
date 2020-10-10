package cn.daxiong.service;
//User的业务逻辑层
import cn.daxiong.dao.UserDao;
import cn.daxiong.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();

	//注册功能
	public void register(User form) throws UserException{
		//1、把user的用户名拿去做查询，如果返回的不是null，则抛出异常！
		User user = userDao.findByUsername(form.getUsername());
		if (user != null) {
			throw new UserException("用户名："+form.getUsername()+"，已经被注册过了！！");
		}
		//2、如果返回是null，则做保存当前user对象
		userDao.add(form);
	}

	//登录功能
	public User login(User form) throws UserException {
		//1、使用form中的username进行查询，得到User
		User user = userDao.findByUsername(form.getUsername());
		//2、如果返回null，说明用户名不存在！抛出异常，异常信息为“用户名不存在！”
		if (user == null) throw new UserException("用户名不存在！"); 
		//3、用户名存在，则比较form的password和user的password，如果不同，抛出异常，异常信息为“密码错误！”
		if(!form.getPassword().equals(user.getPassword())) throw new UserException("密码错误！");
		//返回数据库中查询出来的user，而不是form，因为form只有用户名和密码，而在实际的项目中，数据库中
		//保存的是所有的用户信息，包括电话、邮箱、积分等等....
		return user;
	}
}
