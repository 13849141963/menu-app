package com.zy.cn.controller.management;
import java.util.List;

import com.zy.cn.controller.util.ResultUtil;
import com.zy.cn.entity.MenuEntity;
import com.zy.cn.service.MenuService;
import com.zy.cn.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/***
 * 展示系统菜单的Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	//展示所有菜单
	@RequestMapping("/queryAll")
	public ResultUtil queryAllMenu(HttpSession session){
		ResultUtil result = new ResultUtil();
		List<MenuEntity> menus = menuService.queryAll();
		session.setAttribute("menus",menus);
		result.setStatus(Constant.RESULT_TRUE);
		result.setCode(Constant.USER_CORRENT_DODE);
		return result;
	}

}
