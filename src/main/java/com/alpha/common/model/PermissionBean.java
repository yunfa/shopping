package com.alpha.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Li Yunfa
 * @date 2017年6月19日
 */
public class PermissionBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /** 操作的url */
    private String url;

    /** 操作的名称 */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return JSONObject.toJSONString(this).toString();
    }
}
