package cn.daxiong.bookstore.cart.service;

import cn.daxiong.bookstore.cart.dao.CartDao;

public class CartService {
	private CartDao cartDao = new CartDao();
	
	public void UpdateStock(String bid,int stock) {
		cartDao.updateStock(bid, stock);
	}
	

}
