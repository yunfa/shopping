package com.alpha.user.service;

import java.math.BigDecimal;

import com.alpha.common.base.BusException;
import com.alpha.common.model.DsUserBean;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface DsUserService {

	boolean sendCode(String userName) throws BusException;

	DsUserBean login(String userName, String userPwd, String verifyCode) throws BusException;

	boolean updatePwd(String userName, String userPwd, String verifyCode) throws BusException;

	boolean updateAmount(String userName, BigDecimal amount);

	DsUserBean getUserByName(String userName);
}
