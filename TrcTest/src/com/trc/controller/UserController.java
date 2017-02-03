package com.trc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.trc.entity.sysUser;
import com.trc.service.UserService;
import com.trc.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
//@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {
	@Resource
	private UserService userService;
	
	/**
	 * 查找用户
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findUser")
	public ModelAndView getUser(	@RequestParam(value="id", required=false)String id,
			HttpServletResponse response){	
	   ModelAndView view = new ModelAndView("findUser");
       view.addObject("message", "Say hi for Freemarker.");
       view.addObject("ssss", "ssssss");
       System.out.println(id);
       sysUser s =userService.findUserById(Integer.parseInt(id));
       System.out.println(s.getNickname());
       view.addObject("ssssa", s);	
        return view;
	}
	/**
	 * 返回初始页面
	 * @return
	 */
	@RequestMapping(value="/")
	public String getIndex(){	
		return "index";
	}
	/**
	 * 保存用户
	 * @param username
	 * @param password
	 * @param nickname
	 * @param face
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	public @ResponseBody String save(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "face", required = false) String face,
			HttpServletResponse response){
		JSONObject result=new JSONObject();
//		判断用户名为空
		if(StringUtil.isEmpty(username)){
			result.put("success", false);
			result.put("note","用户名不能为空");
		}else {
//			判断密码
			if(StringUtil.isEmpty(password)){
				result.put("success", false);
				result.put("note","密码不能为空");
			}else {
//				判断用户名是否存在
				if (userService.ifUsernameExist(username)) {
					result.put("success", false);
					result.put("note","用户名已存在");
				}else{
					sysUser user=new sysUser();
					user.setNickname(nickname);
					user.setPassword(password);
					user.setUsername(username);
					user.setFace(face);
//					类型为3代表是一个普通用户
					user.setUser_type(3);
//					判断是否入库
					if(userService.saveUser(user)){
						result.put("success", true);
						result.put("note","入库成功");
					}else {
						result.put("success", false);
						result.put("note","入库失败");
					}
					
				}
				
				
			}
		}
		
		return result.toString();

	}
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/login")
	public @ResponseBody String login(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			HttpServletRequest request,
			HttpServletResponse response){
		    HttpSession session=request.getSession();
		    JSONObject result=new JSONObject();
//		    验证用户名为空
		    if(StringUtil.isEmpty(username)){
		    	result.put("sucess", false);
		    	result.put("des","用户名不能为空");
		    	return result.toString();
		    }
//		    验证密码为空
		    if(StringUtil.isEmpty(password)){
		    	result.put("sucess", false);
		    	result.put("des","密码不能为空");
		    	return result.toString();
		    }
//		    验证用户不存在
		    if(userService.ifUsernameExist(username)){
		    	result.put("sucess", false);
		    	result.put("des","用户不存在");
		    	return result.toString();
		    }
//		   验证用户名密码是否正确
		    if(userService.ifCanLogin(username, password, userService.findUserByUsrname(username))){
		    	result.put("sucess", true);
		    	result.put("des","登陆成功");
		    	session.setAttribute("user", userService.findUserByUsrname(username).getId());
		    	return result.toString();		    	
		    	
		    }else {
		    	result.put("sucess", false);
		    	result.put("des","用户名密码不匹配");
		    	return result.toString();
		    	}
	}
			
	@RequestMapping(value="/welcome")
	public ModelAndView welcome(HttpServletRequest request,
			HttpServletResponse response){
			return new ModelAndView("welcome");
	}
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response){
	    	HttpSession session=request.getSession();
	    	session.removeAttribute("user");
			return new ModelAndView("login");
	}
	
}
