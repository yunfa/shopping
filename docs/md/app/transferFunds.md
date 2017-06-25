# 收付转账

## 请求参数
	method:post
	
	url:http://rpay.8111157.com:8080/app/transfer_funds.shtml
	
	parameter:userId=888&token=jfakljdfaldj
	payUserId(付款方userId),
	receiveUserId(收款方userId),
	perAmount(一次转账金额),
	transferType(转账类型,01付款，02收款)

## 链接示例
    http://rpay.8111157.com:8080/app/transfer_funds.shtml?userId=888&token=jfakljdfaldj&payUserId=888&receiveUserId=999&perAmount=110&transferType=01
    http://rpay.8111157.com:8080/app/transfer_funds.shtml?userId=888&token=jfakljdfaldj&payUserId=888&receiveUserId=999&perAmount=110&transferType=01
    http://rpay.8111157.com:8080/app/transfer_funds.shtml?userId=888&token=jfakljdfaldj&payUserId=888&receiveUserId=999&perAmount=110&transferType=01
    
## 返回参数
	{
		"code": "200",
		"msg": "成功!",
		"timestamp": 1498303791079
	}