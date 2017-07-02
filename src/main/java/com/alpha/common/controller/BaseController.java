package com.alpha.common.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alpha.common.base.BusException;
import com.alpha.common.base.HttpCode;
import com.alpha.common.model.DsUserBean;
import com.alpha.common.model.FinanceBean;
import com.alpha.common.model.OrderDetailBean;
import com.alpha.common.utils.CollectionUtils;
import com.alpha.common.utils.StringUtil;
import com.alpha.order.dto.OrderDetailDto;
import com.alpha.transfer.dto.TransterDto;
import com.alpha.user.dto.UserDto;
import com.google.common.collect.Lists;

/**
 * 基础Controller
 * 
 * @author Li Yunfa
 * @date 2017年6月18日
 */
public class BaseController {

	protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

	protected int pageNo = 1;

	public static int pageSize = 10;

	protected Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

	public static String URL404 = "/404.html";

	private final static String PARAM_PAGE_NO = "pageNo";

	protected String pageSizeName = "pageSize";

	protected static void setValue2Request(HttpServletRequest request, String key, Object value) {
		request.setAttribute(key, value);
	}

	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		BaseController.pageSize = pageSize;
	}

	public ModelAndView redirect(String redirectUrl, Map<String, Object> parament) {
		ModelAndView view = new ModelAndView(new RedirectView(redirectUrl));
		if (null != parament && parament.size() > 0) {
			view.addAllObjects(parament);
		}
		return view;
	}

	public ModelAndView redirect404() {
		return new ModelAndView(new RedirectView(URL404));
	}

	@SuppressWarnings("unchecked")
	protected Map<String, Object> prepareParams(Object obj, HttpServletRequest request) throws Exception {
		if (request != null) {
			String pageNoStr = (String) request.getParameter(PARAM_PAGE_NO),
					pageSizeStr = (String) request.getParameter(pageSizeName);
			if (StringUtil.isNotBlank(pageNoStr)) {
				pageNo = Integer.parseInt(pageNoStr);
			}
			if (StringUtil.isNotBlank(pageSizeStr)) {
				pageSize = Integer.parseInt(pageSizeStr);
			}
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params = BeanUtils.describe(obj);
		params = handleParams(params);
		return params;
	}

	private Map<String, Object> handleParams(Map<String, Object> params) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (null != params) {
			Set<Entry<String, Object>> entrySet = params.entrySet();

			for (Iterator<Entry<String, Object>> it = entrySet.iterator(); it.hasNext();) {
				Entry<String, Object> entry = it.next();
				if (entry.getValue() != null) {
					result.put(entry.getKey(), StringUtil.trim((String) entry.getValue()));
				}
			}
		}
		return result;
	}

	/**
	 * 处理全局异常
	 */
	@ExceptionHandler(Exception.class)
	public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws Exception {
		ModelMap modelMap = new ModelMap();
		modelMap.put("code", HttpCode.business_600.getName());
		modelMap.put("desc", ex.getMessage());
		if (ex instanceof BusException) {
			BusException be = (BusException) ex;
			modelMap.put("code", be.getCode());
			modelMap.put("desc", be.getMsg());
		} else {
			logger.error(ex.toString(), ex);
		}
		response.setContentType("application/json;charset=UTF-8");
		modelMap.put("timestamp", System.currentTimeMillis());
		logger.info("error json:{}", JSON.toJSON(modelMap));
		byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
		response.getOutputStream().write(bytes);
	}

	protected UserDto buildUserDto(DsUserBean bean) {
		UserDto dto = new UserDto();
		if (bean == null) {
			return dto;
		}
		dto.setRhAccount(StringUtil.toDecimalString(bean.getAmount()));
		dto.setToken(bean.getToken());
		dto.setUserId(bean.getUserId());
		dto.setUserName(bean.getUserName());
		return dto;
	}

	protected List<OrderDetailDto> buildOrderDetailDto(List<OrderDetailBean> beanList) {
		List<OrderDetailDto> dtoList = Lists.newArrayList();
		if (CollectionUtils.isEmpty(beanList)) {
			return null;
		}
		for (OrderDetailBean bean : beanList) {
			OrderDetailDto dto = new OrderDetailDto();
			dto.setGoodsName(bean.getGoodsName());
			dto.setGoodsNum(bean.getGoodsNum());
			DecimalFormat format = new DecimalFormat("0.00");
			dto.setGoodsPrice(format.format(bean.getPerAmount()));
			dto.setOrderId(bean.getOrderId());
			dto.setOrderTime(bean.getOrderBean().getCreateTime().getTime());
			dtoList.add(dto);
		}
		return dtoList;
	}

	protected List<TransterDto> buildFinanceDto(List<FinanceBean> beanList) {
		List<TransterDto> dtoList = Lists.newArrayList();
		if (CollectionUtils.isEmpty(beanList)) {
			return null;
		}
		for (FinanceBean bean : beanList) {
			TransterDto dto = new TransterDto();
			dto.setDealTime(bean.getCreateTime().getTime());
			DecimalFormat df = new DecimalFormat("0.00");
			dto.setPerAmount(df.format(bean.getAmount()));
			dto.setRecordName(bean.getSubject());
			dtoList.add(dto);
		}
		return dtoList;
	}
}
