package cn.daxiong.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();  //Bid做键，条目做值
	
	// 添加条目到购物车  (这里有一个合并项的问题)
	public void add (CartItem cartItem) {
		// 判断原来的购物车中是否存在该条目
		if (map.containsKey(cartItem.getBook().getBid())) {
			// 存在即返回原条目
			CartItem _cartItem = map.get(cartItem.getBook().getBid());
			// 设置老条目的数量为：原条目的数量+新条目的数量
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
			map.put(cartItem.getBook().getBid(), _cartItem);
		}else {
			map.put(cartItem.getBook().getBid(), cartItem);
		}
	}
	
	// 合计(所有条目的小计之和)
	// 使用BigDecimal消除二进制的误差问题
	public double getTotal() {
		BigDecimal total = new BigDecimal("0");
		for(CartItem cartItem : map.values() ) {
			BigDecimal subTotal = new BigDecimal(cartItem.getSubtotal()+"");
			total = total.add(subTotal);
		}
		return total.doubleValue();
	}
	
	// 清空所有条目
	public void clear() {
		map.clear();
	}
	
	// 删除指定条目
	public void delete(String bid) {
		map.remove(bid);
	}
	
	// 获取所有条目
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	
}
