package com.alpha.common.dao;

import org.apache.ibatis.annotations.Param;

import com.alpha.common.model.DsUserBean;

/**
 * [OLITCVUY]和[TWN44]两个库的用户
 * 
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface DsUserMapper {

	DsUserBean getUserByNumber(@Param("userName") String userName, @Param("dbName") String dbName);

	DsUserBean getUserById(@Param("userName") String userName, @Param("dbName") String dbName);

	int updateLoginPwd(DsUserBean bean);

	int updateAmount(DsUserBean bean);

	String getMobileByNumber(@Param("userId") Integer userId, @Param("dbName") String dbName);
}
