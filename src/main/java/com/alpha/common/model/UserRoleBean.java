package com.alpha.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Li Yunfa
 * @date 2017年6月19日
 */
public class UserRoleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /** {@link UserBean.id} */
    private Long uid;

    /** {@link RoleBean.id} */
    private Long rid;

    public UserRoleBean(Long uid, Long rid) {
        this.uid = uid;
        this.rid = rid;
    }

    public UserRoleBean() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String toString() {
        return JSONObject.toJSONString(this).toString();
    }
}
