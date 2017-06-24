package com.alpha.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public class RolePermissionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /** {@link RoleBean.id} */
    private Long rid;

    /** {@link PermissionBean.id} */
    private Long pid;

    public RolePermissionBean() {
    }

    public RolePermissionBean(Long rid, Long pid) {
        this.rid = rid;
        this.pid = pid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String toString() {
        return JSONObject.toJSONString(this).toString();
    }
}
