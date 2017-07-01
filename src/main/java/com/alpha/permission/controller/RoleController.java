package com.alpha.permission.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alpha.common.controller.BaseController;
import com.alpha.common.model.RoleBean;
import com.alpha.core.mybatis.page.Pagination;
import com.alpha.permission.service.RoleService;
import com.alpha.user.service.UserService;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("role")
public class RoleController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "index")
	public ModelAndView index(String findContent, ModelMap modelMap) {
		modelMap.put("findContent", findContent);
		Pagination<RoleBean> role = roleService.findPage(modelMap, pageNo, pageSize);
		return new ModelAndView("role/index", "page", role);
	}

	/**
	 * 角色添加
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "addRole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addRole(RoleBean role) {
		try {
			int count = roleService.insertSelective(role);
			resultMap.put("status", 200);
			resultMap.put("successCount", count);
		} catch (Exception e) {
			resultMap.put("status", 500);
			resultMap.put("message", "添加失败，请刷新后再试！");
			logger.error("添加角色报错。source:{}", role.toString(), e);
		}
		return resultMap;
	}

	/**
	 * 删除角色，根据ID，但是删除角色的时候，需要查询是否有赋予给用户，如果有用户在使用，那么就不能删除。
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteRoleById", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteRoleById(String ids) {
		return roleService.deleteRoleById(ids);
	}

	/**
	 * 我的权限页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "mypermission", method = RequestMethod.GET)
	public ModelAndView mypermission() {
		return new ModelAndView("permission/mypermission");
	}

	/**
	 * 我的权限 bootstrap tree data
	 * 
	 * @return
	 */
	@RequestMapping(value = "getPermissionTree", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> getPermissionTree() {
		// 查询我所有的角色 ---> 权限
		List<RoleBean> roles = roleService.findNowAllPermission();
		// 把查询出来的roles 转换成bootstarp 的 tree数据
		List<Map<String, Object>> data = userService.toTreeData(roles);
		return data;
	}
}
