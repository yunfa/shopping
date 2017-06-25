# 转账记录

## 请求参数
	method:get
	
	url:https://rpay.8111157.com/app/dealRecord.shtml
	
	parameter:userId=888&token=jfakljdfaldj

## 链接示例
    https://rpay.8111157.com/app/dealRecord.shtml?userId=888&token=jfakljdfaldj
    https://rpay.8111157.com/app/dealRecord.shtml?userId=888&token=jfakljdfaldj
    https://rpay.8111157.com/app/dealRecord.shtml?userId=888&token=jfakljdfaldj
    
## 返回参数
	{
		"code": "200",
		"msg": "成功!",
		"timestamp": 1498303791079,
		"content": [{
			"recordName": "账户收款-来自水水",//记录名
			"perAmount": "26.60",//转账金额
			"dealTime": 1498303791079 //转账时间
		},{
			"recordName": "账户付款-来自土土",//记录名
			"perAmount": "-26.60",//转账金额
			"dealTime": 1498303791079 //转账时间
		},{
			"recordName": "账户收款-来自水水",//记录名
			"perAmount": "26.60",//转账金额
			"dealTime": 1498303791079 //转账时间
		}]
	}