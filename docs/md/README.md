# 介绍
    
## 请求参数
	请求参数都要带上userId,token,请求方式包括：get,post
	
## 环境切换说明
	https://www.rpay66.com/	开发环境
	https://www.rpay66.com/	测试环境
	https://www.rpay66.com/	正式环境
	
## 参数说明
	1、时间类型自 1970 年 1 月 1 日 00:00:00 开始的毫秒数
	2、分页从第0页开始
	
## 枚举类型
### enum HttpCode:
	success_200("200", "成功!"), 
	error_500("500", "系统内部错误!"),
	business_600("600", "业务错误!");
	
### enum TransferType:
	pay("01", "付款"), 
	receive("02", "收款");
