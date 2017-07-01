package com.alpha.permission.bo;

import java.io.Serializable;

import com.alpha.common.model.PermissionBean;

/**
 * 权限选择
 * 
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public class PermissionBo extends PermissionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 是否勾选
	 */
	private String marker;

	/**
	 * role Id
	 */
	private String roleId;

	public boolean isCheck() {
		return roleId.equals(marker);
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
