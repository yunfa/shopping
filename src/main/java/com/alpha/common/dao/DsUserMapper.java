package com.alpha.common.dao;

import com.alpha.common.model.DsUserBean;

/**
 * [OLITCVUY]和[TWN44]两个库的用户
 * 
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface DsUserMapper {

	DsUserBean getUserByNumber(String userNumber);

	DsUserBean getUserById(String userNumber);

	int updateAmount(DsUserBean bean);

}
