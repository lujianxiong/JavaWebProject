package cn.daxiong.service;
//自定义一个异常类，只是给出父类中的构造器即可！方便用来创建对象
//异常类与异常类之间唯一的区别就是类名字不同！
//你平时除了关系异常的名字，会关心它独有的构造器和方法吗？
public class UserException extends Exception {

	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	 

}
