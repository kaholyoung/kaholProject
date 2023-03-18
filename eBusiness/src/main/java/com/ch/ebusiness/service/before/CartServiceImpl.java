package com.ch.ebusiness.service.before;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ch.ebusiness.repository.before.FocusRepository;
import com.ch.ebusiness.repository.before.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.ch.ebusiness.entity.Goods;
import com.ch.ebusiness.entity.Order;
import com.ch.ebusiness.repository.before.CartRepository;
import com.ch.ebusiness.repository.before.IndexRepository;
import com.ch.ebusiness.util.MD5Util;
import com.ch.ebusiness.util.MyUtil;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private IndexRepository indexRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public String putCart(Goods goods, Model model, HttpSession session) {
		Integer uid = MyUtil.getUser(session).getId();
		//如果商品已在购物车，只更新购买数量
		if(cartRepository.isPutCart(uid, goods.getId()).size() > 0) {
			cartRepository.updateCart(uid, goods.getId(), goods.getBuyNumber());
		}else {//新增到购物车
			cartRepository.putCart(uid, goods.getId(), goods.getBuyNumber());
		}
		//跳转到查询购物车
		return "forward:/cart/selectCart";
	}
	@Override
	public String selectCart(Model model, HttpSession session, String act) {
		List<Map<String, Object>> list = cartRepository.selectCart(MyUtil.getUser(session).getId());
		double sum = 0;
		for (Map<String, Object> map : list) {
			sum = sum + (Double)map.get("smallsum");
		}
		model.addAttribute("total", sum);
		model.addAttribute("cartlist", list);
		//广告区商品
		model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
		//导航栏商品类型
		model.addAttribute("goodsType", indexRepository.selectGoodsType());
		if("toCount".equals(act)) {//去结算页面
			return "user/count";
		}
		return "user/cart";
	}
	@Override
	public String focus(Model model, HttpSession session, Integer gid) {
		Integer uid = MyUtil.getUser(session).getId();
		List<Map<String,Object>>  list = cartRepository.isFocus(uid, gid);
		//判断是否已收藏
		if(list.size() > 0) {
			return "no";
		}else {
			cartRepository.focus(uid, gid);
			return "ok";
		}
	}
	@Override
	public String deleteCart(HttpSession session, Integer gid) {
		Integer uid = MyUtil.getUser(session).getId();
		cartRepository.deleteAgoods(uid, gid);

		return "forward:/cart/selectCart";
	}
	@Override
	public String clearCart(HttpSession session) {
		cartRepository.clear(MyUtil.getUser(session).getId());
		return "forward:/cart/selectCart";
	}
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String submitOrder( Order order, Model model, HttpSession session) {
		order.setBusertable_id(MyUtil.getUser(session).getId());
		System.out.println("==========="+order+"============");
		order.setOrderdate(String.valueOf(LocalDateTime.now()));
		order.setStatus(0);
		System.out.println("=================================");
		System.out.println("==========="+order+"============");

		//生成订单详情
		orderRepository.insert(order);
//		cartRepository.addOrder(order);


		cartRepository.addOrderDetail(order.getId(), MyUtil.getUser(session).getId());
			//减少商品库存
			List<Map<String,Object>> listGoods =  cartRepository.selectGoodsShop(MyUtil.getUser(session).getId());
			for (Map<String, Object> map : listGoods) {
				cartRepository.updateStore(map);
			}
			//清空购物车
			cartRepository.clear(MyUtil.getUser(session).getId());
			model.addAttribute("order", order);
			return "user/pay";



	}
	@Override
	public String pay(Order order) {
//		cartRepository.pay(order.getId());
		order.setStatus(1);
		orderRepository.update(order,null);
		return "ok";
	}
	@Override
	public String myFocus(Model model, HttpSession session) {
		//广告区商品
		model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
		//导航栏商品类型
		model.addAttribute("goodsType", indexRepository.selectGoodsType());
		model.addAttribute("myFocus", cartRepository.myFocus(MyUtil.getUser(session).getId()));
		return "user/myFocus";
	}
	@Override
	public String myOder(Model model, HttpSession session) {
		//广告区商品
		model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
		//导航栏商品类型
		model.addAttribute("goodsType", indexRepository.selectGoodsType());
		model.addAttribute("myOrder", cartRepository.myOrder(MyUtil.getUser(session).getId()));
		return "user/myOrder";
	}
	@Override
	public String orderDetail(Model model, Integer id) {
		model.addAttribute("orderDetail", cartRepository.orderDetail(id));
		return "user/orderDetail";
	}
	@Override
	public String updateUpwd(HttpSession session, String bpwd) {
		Integer uid = MyUtil.getUser(session).getId();
		cartRepository.updateUpwd(uid, MD5Util.MD5(bpwd));
		return "forward:/user/toLogin";
	}

	@Autowired
	private FocusRepository focusRepository;
	@Override
	public String deleteMyFocus(HttpSession session, Integer gid) {
		Integer uid = MyUtil.getUser(session).getId();
		QueryWrapper qw = new QueryWrapper();
		qw.eq("goodstable_id", gid);
		focusRepository.delete(qw);
		return "/user/myFocus";
	}
}
