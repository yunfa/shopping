# 购买商品

## 请求参数
	method:post
	
	url:http://rpay.8111157.com:8080/app/buy_goods.shtml
	
	parameter:userId=888&token=jfakljdfaldjad
	goodsId(商品编号，多个用逗号分隔),
	goodsName(商品名称，多个用逗号分隔),
	goodsNum(商品数量，多个用逗号分隔),
	goodsPrice(商品单价，多个用逗号分隔)

## 链接示例
    http://rpay.8111157.com:8080/app/buy_goods.shtml?userId=888&token=jfakljdfaldj&goodsId=11,33,44&goodsName=鸡蛋,牛奶,花生&goodsNum=2,1,4&goodsPrice=23.4,45,12
    http://rpay.8111157.com:8080/app/buy_goods.shtml?userId=888&token=jfakljdfaldj&goodsId=11,33,44&goodsName=鸡蛋,牛奶,花生&goodsNum=2,1,4&goodsPrice=23.4,45,12
    http://rpay.8111157.com:8080/app/buy_goods.shtml?userId=888&token=jfakljdfaldj&goodsId=11,33,44&goodsName=鸡蛋,牛奶,花生&goodsNum=2,1,4&goodsPrice=23.4,45,12
    
## 返回参数
	{
		"code": "200",
		"msg": "成功!",
		"timestamp": 1498303791079
	}