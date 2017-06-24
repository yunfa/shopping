package com.alpha.common.timer;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alpha.permission.service.RoleService;

/**
 * 定时任务恢复数据
 * 
 * @author Li Yunfa
 * @date 2017年6月21日
 */
@Component
public class ToTimer {

	@Resource
	private RoleService roleService;

	@Scheduled(cron = "0/20 * * * * ? ")
	public void run() {
		/**
		 * 调用存储过程，重新创建表，插入初始化数据。
		 */
		// roleService.initData();
	}

}
