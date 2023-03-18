package com.ch.ebusiness.service.admin;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ch.ebusiness.repository.admin.UserAndOrderAndOutRepository;
import com.ch.ebusiness.repository.before.BUserRepository;
import com.ch.ebusiness.repository.before.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserAndOrderAndOutServiceImpl implements UserAndOrderAndOutService{
	@Autowired
	private UserAndOrderAndOutRepository userAndOrderAndOutRepository;

	@Autowired
	private BUserRepository bUserRepository;

	@Autowired
	private OrderRepository orderRepository;


	@Override
	public String selectUser(Model model, int currentPage) {
		//共多少个用户
//	  	int totalCount = userAndOrderAndOutRepository.selectAllUser();
		int totalCount = bUserRepository.selectList(null).size();
		//计算共多少页
	  	int pageSize = 5;
	  	int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
		IPage page = new Page((currentPage-1)*pageSize, pageSize);
		List records = page.getRecords();
//		List<BUser> typeByPage = userAndOrderAndOutRepository.selectUserByPage((currentPage-1)*pageSize, pageSize);
	    model.addAttribute("allUsers", records);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("currentPage", currentPage);
		return "admin/allUser";
	}

	@Override
	public String deleteUser(Model model, int id) {

		if(userAndOrderAndOutRepository.selectCartUser(id).size() > 0 
				||userAndOrderAndOutRepository.selectOrderUser(id).size() > 0) {
			return "no";
		}else {
//			userAndOrderAndOutRepository.deleteUser(id);
			bUserRepository.deleteById(id);
			return "/selectUser?currentPage=1";
		}
	}

	@Override
	public String selectOrder(Model model, int currentPage) {
		//共多少个订单
//	  	int totalCount = userAndOrderAndOutRepository.selectAllOrder();
		int totalCount = orderRepository.selectList(null).size();
		//计算共多少页
	  	int pageSize = 5;
	  	int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);
	  	List<Map<String, Object>> orderByPage = userAndOrderAndOutRepository.selectOrderByPage((currentPage-1)*pageSize, pageSize);
	    model.addAttribute("allOrders", orderByPage);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("currentPage", currentPage);
		return "admin/allOrder";
	}

}
