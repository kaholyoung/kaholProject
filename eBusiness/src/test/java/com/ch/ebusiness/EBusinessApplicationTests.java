package com.ch.ebusiness;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ch.ebusiness.entity.Order;
import com.ch.ebusiness.repository.admin.GoodsRepository;
import com.ch.ebusiness.repository.before.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EBusinessApplicationTests {


	@Autowired
	private OrderRepository orderRepository;


	@Autowired
	private GoodsRepository goodsRepository1;

	@Test
	public void contextLoads() {
		QueryWrapper wrapper = new QueryWrapper();

		wrapper.eq("goprice",900);
		wrapper.eq("grprice",800);

		System.out.println(goodsRepository1.selectList(wrapper));


	}




	@Test
	public void test1(){

		QueryWrapper qw = new QueryWrapper();
		qw.eq("goodstable_id", 35L);

//		System.out.println(focustable4TestRepository1.selectById(7));
	}


	@Test
	public void test12(){


		Order order = new Order();
		order.setStatus(0);
		order.setOrderdate(String.valueOf(LocalDate.now()));
		order.setBusertable_id(9);
		order.setAmount(2222.0);

		orderRepository.insert(order);
	}


	@Test
	public void test123(){




	}

	@Test
	public void test1234(){

		int[] a = {2,3,4,2,4,3,6,7,35,67,3,4,6};



		System.out.println(Arrays.toString(a));
	}
}
