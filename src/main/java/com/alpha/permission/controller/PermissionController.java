package com.alpha.permission.controller;

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
import com.alpha.common.model.PermissionBean;
import com.alpha.core.mybatis.page.Pagination;
import com.alpha.permission.service.PermissionService;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("permission")
public class PermissionController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    PermissionService permissionService;

    /**
     * 权限列表
     * 
     * @param findContent 查询内容
     * @param pageNo 页码
     * @param modelMap 参数回显
     * @return
     */
    @RequestMapping(value = "index")
    public ModelAndView index(String findContent, ModelMap modelMap, Integer pageNo) {
        modelMap.put("findContent", findContent);
        Pagination<PermissionBean> permissions = permissionService.findPage(modelMap, pageNo, pageSize);
        return new ModelAndView("permission/index", "page", permissions);
    }

    /**
     * 权限添加
     * 
     * @param role
     * @return
     */
    @RequestMapping(value = "addPermission", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addPermission(PermissionBean psermission) {
        try {
            PermissionBean entity = permissionService.insertSelective(psermission);
            resultMap.put("status", 200);
            resultMap.put("entity", entity);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败，请刷新后再试！");
            logger.error("添加权限报错。source:{}", psermission.toString(), e);
        }
        return resultMap;
    }

    /**
     * 删除权限，根据ID，但是删除权限的时候，需要查询是否有赋予给角色，如果有角色在使用，那么就不能删除。
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "deletePermissionById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteRoleById(String ids) {
        return permissionService.deletePermissionById(ids);
    }
}
