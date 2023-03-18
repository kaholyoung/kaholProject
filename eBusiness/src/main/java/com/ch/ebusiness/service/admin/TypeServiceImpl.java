package com.ch.ebusiness.service.admin;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.ebusiness.repository.admin.GoodsRepository;
import com.ch.ebusiness.repository.admin.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ch.ebusiness.entity.Goods;
import com.ch.ebusiness.entity.GoodsType;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeRepository,GoodsType> implements TypeService{
	@Autowired
	private TypeRepository typeRepository;

	@Autowired
	private GoodsRepository goodsRepository1;
	@Override
	public String selectAllTypeByPage(Model model, int currentPage) {
		//共多少个类型
//	  	int totalCount = typeRepository.selectAll();

		int totalCount = typeRepository.selectList(null).size();
		//计算共多少页
	  	int pageSize = 2;
	  	int totalPage = (int)Math.ceil(totalCount*1.0/pageSize);

		IPage page = new Page((currentPage-1)*pageSize, pageSize);
		List records = page.getRecords();
//		List<GoodsType> typeByPage = typeRepository.selectAllTypeByPage((currentPage-1)*pageSize, pageSize);
	    model.addAttribute("allTypes", records);
	    model.addAttribute("totalPage", totalPage);
	    model.addAttribute("currentPage", currentPage);
		return "admin/selectGoodsType";
	}
	@Override
	public String delete(int id) {
//		List<Goods> list = typeRepository.selectGoods(id);

		Goods goods = goodsRepository1.selectById(id);
		if(goods != null) {
			//该类型下有商品不允许删除
			return "no";
		}else {
			typeRepository.deleteById(id);
//			typeRepository.deleteType(id);
			//删除后回到查询页面
			return "/type/selectAllTypeByPage?currentPage=1";
		}
	}
	@Override
	public String addType(GoodsType goodsType) {
		typeRepository.insert(goodsType);
//		typeRepository.addType(goodsType);
		return "redirect:/type/selectAllTypeByPage?currentPage=1";
	}

}
