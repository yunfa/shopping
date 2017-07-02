package com.alpha.user.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.common.base.BusException;
import com.alpha.common.base.Enums;
import com.alpha.common.dao.RFinanceMapper;
import com.alpha.common.model.DsFinanceBean;
import com.alpha.common.model.DsUserBean;
import com.alpha.common.model.FinanceBean;
import com.alpha.common.utils.StringUtil;
import com.alpha.core.mybatis.BaseMybatisDao;
import com.alpha.user.service.DsFinanceService;
import com.alpha.user.service.DsUserService;
import com.alpha.user.service.RFinanceService;

/**
 * @author Li Yunfa
 * @date 2017年6月21日
 */
@Service
public class RFinanceServiceImpl extends BaseMybatisDao<RFinanceMapper> implements RFinanceService {

	@Autowired
	private RFinanceMapper rFinanceMapper;

	@Autowired
	private DsUserService dsUserService;

	@Autowired
	private DsFinanceService dsFinanceService;

	@Override
	@Transactional
	public void transferFunds(String payUserName, String recUserName, String perAmount, String transferType)
			throws BusException {
		// 转账金额判断
		BigDecimal transAmount = new BigDecimal(perAmount);
		if (transAmount.compareTo(BigDecimal.ZERO) < 0) {
			throw new BusException("转账金额不能小于0!");
		}
		// 判断付方余额
		String dbName = StringUtil.getDbName(payUserName);
		DsUserBean payUserBean = dsUserService.getUserByName(payUserName);
		if (payUserBean == null) {
			throw new BusException("付款方未找到!");
		}
		DsUserBean recUserBean = dsUserService.getUserByName(recUserName);
		if (recUserBean == null) {
			throw new BusException("收款方未找到!");
		}
		BigDecimal payTotalAmount = payUserBean.getAmount();
		BigDecimal recTotalAmount = recUserBean.getAmount();
		BigDecimal payLeftAmount = payTotalAmount.subtract(transAmount);
		BigDecimal recLeftAmount = recTotalAmount.add(transAmount);
		if (payLeftAmount.compareTo(BigDecimal.ZERO) < 0) {
			throw new BusException("账户余额不够!");
		}

		// 财务付款信息
		FinanceBean payBean = new FinanceBean();
		Date createTime = new Date();
		payBean.setAmount(transAmount);
		payBean.setCode(Enums.FinanceCode.C502.getName());
		payBean.setCreateTime(createTime);
		payBean.setToUserName(recUserName);
		payBean.setUserName(payUserName);
		payBean.setSubject("账户付款-转给" + recUserName);
		rFinanceMapper.insert(payBean);

		// 财务收款信息
		FinanceBean receiveBean = new FinanceBean();
		receiveBean.setAmount(transAmount);
		receiveBean.setCode(Enums.FinanceCode.C503.getName());
		receiveBean.setCreateTime(createTime);
		receiveBean.setToUserName(payUserName);
		receiveBean.setUserName(recUserName);
		receiveBean.setSubject("账户收款-来自" + payUserName);
		rFinanceMapper.insert(receiveBean);

		// 更新ds付方账户 pay
		dsUserService.updateAmount(payUserName, payLeftAmount);
		// 写入付方ds财务表
		DsFinanceBean record = new DsFinanceBean();
		record.setAddTime(createTime);
		record.setAmount(transAmount);
		record.setCode(Enums.FinanceCode.C502.getName());
		record.setDbName(dbName);
		record.setUserId(payUserBean.getUserId());
		record.setToUserId(recUserBean.getUserId());
		record.setSubject("");
		record.setCid(0);
		record.setOid(0);
		record.setFlag(1);
		dsFinanceService.insert(record);

		// 更新ds收方账户 rec
		dsUserService.updateAmount(recUserName, recLeftAmount);
		// 写入收方ds财务表
		record = new DsFinanceBean();
		record.setAddTime(createTime);
		record.setAmount(transAmount);
		record.setCode(Enums.FinanceCode.C503.getName());
		record.setDbName(dbName);
		record.setUserId(recUserBean.getUserId());
		record.setToUserId(payUserBean.getUserId());
		record.setSubject("");
		record.setCid(0);
		record.setOid(0);
		record.setFlag(1);
		dsFinanceService.insert(record);
	}

	@Override
	public List<FinanceBean> transferRecord(String userName) {
		List<FinanceBean> beanList = rFinanceMapper.getListByUserName(userName);
		return beanList;
	}

	public int insert(FinanceBean bean) {
		return rFinanceMapper.insert(bean);

	}

	@Override
	public List<FinanceBean> getListByUserName(String userName) {
		return rFinanceMapper.getListByUserName(userName);
	}

}
