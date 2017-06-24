package com.alpha.permission.bo;

import java.io.Serializable;

import com.alpha.common.model.RoleBean;
import com.alpha.common.utils.StringUtils;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public class URoleBo extends RoleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID (用String， 考虑多个ID，现在只有一个ID)
     */
    private String userId;

    /**
     * 是否勾选
     */
    private String marker;

    public boolean isCheck() {
        return StringUtils.equals(userId, marker);
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
