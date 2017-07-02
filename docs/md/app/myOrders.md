# 我的订单

## 请求参数
	method:get
	
	url:https://www.rpay66.com/app/my_orders.shtml
	
	parameter:userName=cnuser&token=jfakljdfaldj

## 链接示例
    https://www.rpay66.com/app/my_orders.shtml?userName=cnuser&token=jfakljdfaldj
    https://www.rpay66.com/app/my_orders.shtml?userName=cnuser&token=jfakljdfaldj
    https://www.rpay66.com/app/my_orders.shtml?userName=cnuser&token=jfakljdfaldj
    
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