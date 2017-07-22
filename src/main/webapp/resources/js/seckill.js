// 存放主要交换逻辑js代码
// javascript 模块化
var seckill = {
		//存放秒杀相关的ajax URL
		URL:{
			now : function(){
				return "/seckill/time/now";
			},
			exposer : function(seckillId){
				return  "/seckill/"+ seckillId+"/exposer";
			}, 
			execution : function (seckillId ,md5){
				return "/seckill/" + seckillId + "/" + md5 + "/execution";
			}
			
		},
		
		handleSeckill : function(seckillId ,node){
			//处理秒杀逻辑
			node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
			$.post(seckill.URL.exposer(seckillId),{},function(res){
				if(res && res['success']){
					var exposer = res['data'];
					if(exposer['exposed']){
						//开启秒杀
						var md5 = exposer['md5'];
						var killUrl = seckill.URL.execution(seckillId, md5);
						//绑定一次按钮
						$('#killBtn').one("click",function(){
							// 执行秒杀请求
							//先禁用按钮
							$(this).addClass('disabled');
							// 发送秒杀请求
							$.post(killUrl,{},function(result){
								if(result && result['success']){
									var killResult = result['data'];
									var state = killResult['state'];
									var stateInfo = killResult['stateInfo'];
									//显示秒杀结果
									node.html('<span class="label label-success">' + stateInfo + '</span>');
								}
							});
						});
						node.show();
					}else{
						var nowTime = exposer['now'];
						var startTime = exposer['start'];
						var endTime = exposer['end'];
						// 重新计算计时逻辑
						seckill.countdown(nowTime,startTime,seckillId ,endTime);
					}
				}else{
					console.log('result=' + res);
				}
				
			});
		},
		//验证手机号
		validatePhone : function(phone){
			if(phone && phone.length == 11 && !isNaN(phone)){
				return true;
			}else{
				return false;
			}
		},
		countdown : function(nowTime,startTime,seckillId ,endTime){
			var seckillBox = $('#seckillBox');
			if(nowTime > endTime){
				//秒杀结束
				seckillBox.html('秒杀结束!');
			}else if(nowTime < startTime){
				//秒杀未开始
				var killTime = new Date(startTime + 1000);
				seckillBox.countdown(killTime,function(event){
					// 时间格式
					var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
					seckillBox.html(format);
				}).on('finsh.countdown', function() {
					// 时间走完,获取秒杀地址，控制显示逻辑，执行秒杀
					seckill.handleSeckill(seckillId, seckillBox);
				});
			}else{
				seckill.handleSeckill(seckillId, seckillBox);
			}
		},
		//详情页秒杀逻辑
		detail:{
			//详情页初始化
			init : function(params){
				//用户手机验证和登录，计时交互
				//规划我们的交互流程
				var killPhone = $.cookie('killPhone');
				var startTime = params['startTime'];
				var endTime = params['endTime'];
				var seckillId = params['seckillId'];
				//验证手机号
				if(!seckill.validatePhone(killPhone)){
					//绑定phone
					//控制输出
					var killPhoneModal = $('#killPhoneModal');
					//显示弹出层
					killPhoneModal.modal({
						show : true,// 显示弹出层
						backdrop : 'static',// 禁止位置关闭
						keyboard : false// 关闭键盘事件
					});
					//绑定提交的点击事件
					$('#killPhoneBtn').click(function(){
						var inputPhone = $('#killphoneKey').val();
						if(seckill.validatePhone(inputPhone)){
							//见电话写入cookie,设置有效期7天，绑定路径/seckill
							$.cookie('killPhone',inputPhone,{expires:7 , path:'/seckill'});
							//刷新页面
							window.location.reload();
						}else{
							//验证不通过，提示手机号错误
							$('#killphoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);//300秒内完成
						}
					});				
				}
				//已登录，计时交互
				$.get(seckill.URL.now(),{},function(result){
					if(result && result['success'] ){
						var nowTime = result['data'];
						seckill.countdown(nowTime,startTime,seckillId ,endTime);
						// 时间判断，计时交互
					}
				});
			}
		}
};