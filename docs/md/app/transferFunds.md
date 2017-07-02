# 收付转账

## 请求参数
	method:post
	
	url:https://www.rpay66.com/app/transfer_funds.shtml
	
	parameter:userName=cnuser&token=jfakljdfaldj
	payUserName(付款方userName),
	receiveUserName(收款方userName),
	perAmount(一次转账金额),
	transferType(转账类型,01付款，02收款)

## 链接示例
    https://www.rpay66.com/app/transfer_funds.shtml?userName=cnuser&token=jfakljdfaldj&payUserName=cnuser&receiveUserName=twuser&perAmount=110&transferType=01
    https://www.rpay66.com/app/transfer_funds.shtml?userName=cnuser&token=jfakljdfaldj&payUserName=cnuser&receiveUserName=twuser&perAmount=110&transferType=01
    https://www.rpay66.com/app/transfer_funds.shtml?userName=cnuser&token=jfakljdfaldj&payUserName=cnuser&receiveUserName=twuser&perAmount=110&transferType=01
    
## 返回参数
	{
		"code": "200",
		"msg": "成功!",
		"timestamp": 1498303791079
	}