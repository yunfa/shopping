# 我的订单

## 请求参数
	method:get
	
	url:http://rpay.8111157.com:8080/app/my_orders.shtml
	
	parameter:userId=888&token=jfakljdfaldj

## 链接示例
    http://rpay.8111157.com:8080/app/my_orders.shtml?userId=888&token=jfakljdfaldj
    http://rpay.8111157.com:8080/app/my_orders.shtml?userId=888&token=jfakljdfaldj
    http://rpay.8111157.com:8080/app/my_orders.shtml?userId=888&token=jfakljdfaldj
    
## 返回参数
	{
		"code": "200",
		"msg": "成功!",
		"timestamp": 1498303791079,
		"content": {
			[{
			"orderId": 170625112659123165,//订单编号
			"goodsName": "鸡蛋",//商品名称
			"goodsPrice": "26.60",//商品单价
			"goodsNum": 1 ,//商品数量
			"orderTime": 1498303791079 //订单时间
			},{
			"orderId": 170625112659123165,
			"goodsName": "牛奶",
			"goodsPrice": "15.65",
			"goodsNum": 2 ,
			"orderTime": 1498303791079 
			},{
			"orderId": 170625112659123165,
			"goodsName": "花生",
			"goodsPrice": "45.00",
			"goodsNum": 1,
			"orderTime": 1498303791079
			}]
		}
	}
	//暂不进行分页查询