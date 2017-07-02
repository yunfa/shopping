package com.alpha.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alpha.common.base.BusException;
import com.alpha.common.model.FinanceBean;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface RFinanceService {

	int insert(FinanceBean bean);

	List<FinanceBean> getListByUserName(@Param("userName") String userName);

	void transferFunds(String payUserName, String receiveUserName, String perAmount, String transferType)
			throws BusException;

	List<FinanceBean> transferRecord(String userName);
}
