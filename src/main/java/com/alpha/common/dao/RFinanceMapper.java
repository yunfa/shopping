package com.alpha.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alpha.common.model.FinanceBean;

/**
 * 财务转账
 * 
 * @author Li Yunfa
 * @date 2017年6月21日
 */
public interface RFinanceMapper {

	int insert(FinanceBean bean);

	List<FinanceBean> getListByUserName(@Param("userName") String userName);
}
