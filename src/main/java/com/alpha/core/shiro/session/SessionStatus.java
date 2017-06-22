package com.alpha.core.shiro.session;

import java.io.Serializable;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public class SessionStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    //是否踢出 true:有效，false：踢出。
    private Boolean onlineStatus = Boolean.TRUE;

    public Boolean isOnlineStatus() {
        return onlineStatus;
    }

    public Boolean getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

}
