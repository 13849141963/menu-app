package com.zy.cn.controller.management;
import java.util.List;
import com.zy.cn.controller.util.ResultUtil;
import com.zy.cn.entity.Menu;
import com.zy.cn.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ResponseBody
	public Object queryAllMenu(){
		ResultUtil result = new ResultUtil();
		List<Menu> menus = menuService.queryAll();
		return menus;
	}

}
