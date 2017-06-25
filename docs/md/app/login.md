# 用户登录

## 请求参数
	method:post
	
	url:https://rpay.8111157.com/app/login.shtml
	
	parameter:
	userName(登录账号),
	userPwd(登录密码),
	verifyCode(验证码)

## 链接示例
    https://rpay.8111157.com/app/login.shtml?userName=rpay&userPwd=bbb&verifyCode=2322
    https://rpay.8111157.com/app/login.shtml?userName=rpay&userPwd=bbb&verifyCode=2322
    https://rpay.8111157.com/app/login.shtml?userName=rpay&userPwd=bbb&verifyCode=2322
    
## 返回参数
	{
		"code": "200",
		"msg": "成功!",
		"timestamp": 1498303791079,
		"content": {
			"userId": 888,//登录后的请求都要带上userId
			"userName": "rpay",//用户名
			"token": "sdafksj2342k34jk",//登录后的请求都要带上token，以校验用户登录
			"rhAccount": 88.88 //rh账户金额
		}
	}
	//返回的用户信息需要在app端保存