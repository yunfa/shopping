package com.alpha.core.freemarker.extend;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.alpha.common.model.UserBean;
import com.alpha.common.utils.StringUtils;
import com.alpha.core.shiro.token.manager.TokenManager;
import com.alpha.core.statics.Constant;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public class FreeMarkerViewExtend extends FreeMarkerView {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) {

        try {
            super.exposeHelpers(model, request);
        } catch (Exception e) {
            logger.error("FreeMarkerViewExtend 加载父类出现异常。请检查。", e);
        }
        model.put(Constant.CONTEXT_PATH, request.getContextPath());
        model.putAll(Ferrmarker.initMap);
        UserBean token = TokenManager.getToken();
        // String ip = IPUtils.getIP(request);
        model.put("token", token);// 登录的token
        model.put("_time", new Date().getTime());
        model.put("NOW_YEAY", Constant.NOW_YEAY);// 今年

        model.put("_v", Constant.VERSION);// 版本号，重启的时间
        model.put("cdn", Constant.DOMAIN_CDN);// CDN域名
        model.put("basePath", request.getContextPath());// base目录。
    }
}
