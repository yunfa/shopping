# 修改密码

## 请求参数
	method:post
	
	url:http://rpay.8111157.com:8080/app/update_pwd.shtml
	
	parameter:
	userName(登录账号),
	userPwd(新密码),
	verifyCode(验证码)

## 链接示例
    http://rpay.8111157.com:8080/app/update_pwd.shtml?userName=rpay&userPwd=bbb&verifyCode=2322
    http://rpay.8111157.com:8080/app/update_pwd.shtml?userName=rpay&userPwd=bbb&verifyCode=2322
    http://rpay.8111157.com:8080/app/update_pwd.shtml?userName=rpay&userPwd=bbb&verifyCode=2322
    
## 返回参数
	{
		"code": "200",
		"msg": "成功!",
		"timestamp": 1498303791079
	}