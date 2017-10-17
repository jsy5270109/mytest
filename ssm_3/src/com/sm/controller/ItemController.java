package com.sm.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sm.exception.BusinessException;
import com.sm.po.Item;
import com.sm.service.ItemService;

@Controller
@RequestMapping("/user")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/queryItem.do")
	public ModelAndView fingItemList(){
		
		ModelAndView mv=new ModelAndView();
		List<Item> list = itemService.findItemList();
		
		mv.addObject("itemList", list);
		
		mv.setViewName("item/item-list");
		
		
		return mv;
	}
	
	@RequestMapping("showItemEdit")
	private ModelAndView showItemEdit(@RequestParam("itemid")Integer id){
		
		ModelAndView mv=new ModelAndView();
		
		Item item=itemService.findItem(id);
		mv.addObject("item", item);
		
		mv.setViewName("item/item-edit");
		return mv;
	}
	
	@RequestMapping("deleteAll")
	public String deleteAll(Integer[] itemId) throws BusinessException{
		
		/*if(itemId.length<=0){
			
			System.out.println("aaa");
			throw new BusinessException("选择不能为空");
		}*/
		
		if(itemId==null){
			
			System.out.println("aaa");
			throw new BusinessException("选择不能为空");
			
		}
		
		for (Integer id : itemId) {
			System.out.println(id);
		}
		
		
		
		return "redirect:queryItem";
	}
	
	
	
	
	
	
	/*@RequestMapping("updateItem")
	private ModelAndView updateItem(Integer id,Item item){
		
		ModelAndView mv=new ModelAndView();
		
		itemService.updateItem(item);
		
		mv.setViewName("success");
		return mv;
		
	}*/
	
	@RequestMapping("/updateItem")
	public String updateItem(Integer id, Item item, MultipartFile pictureFile) throws Exception {

		// 处理上传文件
		if (pictureFile != null) {
			// 获取上传文件格式

			// 上传文件的原始名称
			String originalFilename = pictureFile.getOriginalFilename();

			String extName = "";
			if (originalFilename != null && !"".equals(originalFilename)) {
				// 得到扩展名，比如: .jpg
				extName = originalFilename.substring(originalFilename.lastIndexOf("."));
			}

			// 创建新的文件名
			String newFileName = UUID.randomUUID().toString() + extName;

			// 指定上传文件存储目录
			String dir = "E:\\pic\\";

			// 保证存储目录一定存在
			File dirFile = new File(dir);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			// 将上传文件保存到新文件中
			pictureFile.transferTo(new File(dir + newFileName));

			// 保存新文件名
			item.setPic(newFileName);
		}

		// 修改商品
		itemService.updateItem(item);

		// forward
		// return "forward:/item/queryItem.do";

		// redirect
		return "redirect:/user/queryItem.do";
	}

	@RequestMapping("requestKV")
	@ResponseBody
	private Item requestKV(Item item){
		
		System.out.println(item);
		
		
		
		return item;
	}
}
