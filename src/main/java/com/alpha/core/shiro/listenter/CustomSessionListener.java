package com.alpha.core.shiro.listenter;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alpha.core.shiro.session.ShiroSessionRepository;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public class CustomSessionListener implements SessionListener {

    private static Logger logger = LoggerFactory.getLogger(CustomSessionListener.class);

    private ShiroSessionRepository shiroSessionRepository;

    /**
     * 一个回话的生命周期开始
     */
    @Override
    public void onStart(Session session) {
        logger.debug("on start");
    }

    /**
     * 一个回话的生命周期结束
     */
    @Override
    public void onStop(Session session) {
        logger.debug("on stop");
    }

    @Override
    public void onExpiration(Session session) {
        shiroSessionRepository.deleteSession(session.getId());
    }

    public ShiroSessionRepository getShiroSessionRepository() {
        return shiroSessionRepository;
    }

    public void setShiroSessionRepository(ShiroSessionRepository shiroSessionRepository) {
        this.shiroSessionRepository = shiroSessionRepository;
    }

}
