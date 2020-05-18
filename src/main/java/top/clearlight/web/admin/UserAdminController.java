package top.clearlight.web.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import top.clearlight.dto.Result;
import top.clearlight.entity.User;
import top.clearlight.service.UserService;
import top.clearlight.util.StringUtil;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

	@Autowired
	private UserService userService;
	
	/**
	 * 用户列表
	 * @param username
	 * @param email
	 * @param p
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user:list")
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(String username, String email, @RequestParam(value = "p",defaultValue = "1") Integer p, Model model) {
		if(StringUtils.isEmpty(username)) {
			username = null;
		}
		if(StringUtils.isEmpty(email)) {
			email = null;
		}
		model.addAttribute("username", username);
		model.addAttribute("email", email);
		model.addAttribute("p", p);
		model.addAttribute("page", userService.pageForAdmin(username, email, p, 25));
		return "/admin/user/list";
	}
	
	/**
	 * 编辑用户界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("user:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.GET)
	public String edit(Integer id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "/admin/user/edit";
	}
	
	/**
	 * 编辑用户接口
	 * @param user
	 * @return
	 */
	@RequiresPermissions("user:edit")
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Result<String> edit(User user){
		userService.updateAdmin(user);
		return new Result<>(true, "编辑成功");
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user:delete")
	@RequestMapping(value = "/delete",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> delete(Integer id){
		userService.deleteAdmin(id);
		return new Result<>(true, "删除成功");
	}
	
	/**
	 * 刷新Token
	 * @return
	 */
	@RequestMapping(value = "/refreshToken",method = RequestMethod.GET)
	@ResponseBody
	public Result<String> refreshToken(){
		return new Result<>(true, StringUtil.getUUID());
	}
	
	/**
	 * 局部日期转换，将 String 类型的时间数据转化为 Date 类型
	 * @param binder
	 * @param request
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        // 转换日期 注意这里的转化要和传进来的字符串的格式一直 如2020-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
