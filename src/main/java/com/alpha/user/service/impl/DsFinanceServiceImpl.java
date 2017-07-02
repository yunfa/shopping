package com.alpha.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alpha.common.dao.DsFinanceMapper;
import com.alpha.common.model.DsFinanceBean;
import com.alpha.core.mybatis.BaseMybatisDao;
import com.alpha.user.service.DsFinanceService;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
@Service
public class DsFinanceServiceImpl extends BaseMybatisDao<DsFinanceMapper> implements DsFinanceService {

	@Autowired
	private DsFinanceMapper dsFinanceMapper;

	@Override
	public int insert(DsFinanceBean record) {
		return dsFinanceMapper.insert(record);
	}
}
