package cn.e3mall.controller;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

import java.util.Date;


/**
 * 商品管理Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}

	@RequestMapping("/item/list")
	@ResponseBody
	public DataGridResult getItemList(Integer page, Integer rows) {
		DataGridResult result = itemService.getItemList(page, rows);
		return result;
	}

	@RequestMapping("/item/save")
	@ResponseBody
	public E3Result saveItem(TbItem item, String desc) {
		E3Result result = itemService.addItem(item, desc);
		return result;
	}

}
