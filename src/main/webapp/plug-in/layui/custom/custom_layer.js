//layer自定义弹出层
var layerUI = {
   currLayerIndex:null,
   layer:null,
   options:{},
   init:function(){
	   layui.use(['layer','element'], function(){
		   layerUI.element = layui.element();
		   layerUI.layer = layui.layer;
	   });  
   },
   closeAllLayer:function(){
	   layerUI.layer.closeAll();
   },
   showMsg:function(params){//提示框
	   var index = layerUI.layer.open({
		type:0,
		title:params.title==undefined?'提示信息':params.title,
		closeBtn:1,
        time: params.time==undefined?"2000":params.time, //2s后自动关闭
        zIndex:layerUI.layer.zIndex,
        content:params.content==undefined?"提示信息":params.content,
        end:function(){
        	if(params.closed!=undefined) params.closed(layerUI.layer,index);
        },
        success:function(){
        	$(".layui-layer-btn0").css("border-radius","5px");
        	$(".layui-layer-btn1").css("border-radius","5px");
        	$(".layui-layer").css("border-radius","10px");
        	$(".layui-layer-title").css("border-radius","10px");
        }
       });
   },
   confirm:function(params){//确认框
	   var index = layerUI.layer.open({
			type:0,
			title:params.title==undefined?'提示信息':params.title,
			closeBtn:1,
	        zIndex:9900,
	        content:params.content==undefined?"你确定要操作？":params.content,
	        btn: ["确定", "取消"],
	        yes:function(){
	        	if(params.yes!=undefined) {
	        		params.yes(layerUI.layer,index);
	        		layerUI.layer.close(index);
	        	}
	        },
	        cancel:function(){
	        	if(params.cancel!=undefined) {
	        		params.cancel(layerUI.layer,index);
	        	}else{
	        		layerUI.layer.close(index);
	        	}
	        },
	        success:function(){
				$(".layui-layer-btn0").css("border-radius","5px");
	        	$(".layui-layer-btn1").css("border-radius","5px");
	        	$(".layui-layer").css("border-radius","10px");
	        	$(".layui-layer-title").css("border-radius","10px");
			}
       });
   },
   openWin:function(options){//弹出层
	   var index = layerUI.layer.open({
		  id:options.id==undefined?'':options.id,
		  type: options.type==undefined?2:options.type,
		  title: options.title==undefined?false:options.title,
		  closeBtn:options.closeBtn==undefined?false:options.closeBtn,
		  //skin:'layui-layer-lan',
		  skin:'layui-layer-molv',
		  maxmin: options.maxmin==undefined?true:options.maxmin, //开启最大化最小化按钮
		  shade:options.shade==undefined?0.3:options.shade,
		  area: options.area==undefined?['900px', '500px']:options.area,
		  offset:options.offset==undefined?'100px':options.offset,
		  zIndex:options.zIndex==undefined?'':options.zIndex,
		  move:options.move==undefined?'.layui-layer-title':options.move,
		  moveType:1,
		  btn: options.btns,
		  btnAlign: options.btnAlign==undefined?'c':options.btnAlign,
		  scrollbar: false,
		  full:function(){
			  if(options.full!=undefined){
				  options.full(layerUI.layer, index);
			  }
		  },
		  min:function(){
			  if(options.min!=undefined){
				  options.min(layerUI.layer, index);
			  }
		  },
		  restore:function(){
			  if(options.restore!=undefined){
				  options.restore(layerUI.layer, index);
			  }
		  },
		  yes:function(){
			  if(options.yes!=undefined){
				  options.yes(layerUI.layer, index);
			  }
		  },
		  btn2:function(){
			  if(options.btn2!=undefined){
				  options.btn2(layerUI.layer, index);
			  }
			  return false;
		  },
		  btn3:function(){
			  if(options.btn3!=undefined){
				  options.btn3(layerUI.layer, index);
			  }
			  return false;
		  },
		  btn4:function(){
			  if(options.btn4!=undefined){
				  options.btn4(layerUI.layer, index);
			  }
			  return false;
		  },
		  cancel:function(){
			  if(options.cancel!=undefined){
				  options.cancel(layerUI.layer, index);
			  }
		  },
		  success:function(){
			  if(options.success!=undefined){
				  options.success(layerUI.layer, index);
			  }
			  
			  $(".layui-layer").css("border-radius","10px");
	          $(".layui-layer-title").css("border-radius","2px");
		  },
		  end:function(){
			  flag = false;
			  setTimeout(function(){
				  flag = true;
			  }, 200);
			  if($("#commentTips").length>0){
				  $("#commentTips").hide();
			  }
              if($("#deleteBtn").length>0){
            	  $("#deleteBtn").hide();
			  }
			  if(options.end!=undefined){
				  options.end();
			  }
		  },
		  content: options.content
		});
	   layerUI.currLayerIndex = index;
	   return index;
   },
   openTabWin:function(options){
	   if(!options.tabs) return;
	   var index = layerUI.layer.tab({
		   id:options.id==undefined?"":options.id,
		   area: options.area==undefined?['900px', '500px']:options.area,
		   type:2,
		   tab:options.tabs,
		   shade:options.shade==undefined?0.3:options.shade,
		   zIndex:options.zIndex==undefined?'':options.zIndex,
		   content:options.content==undefined?undefined:options.content,
		   btn: options.btns,
		   btnAlign: options.btnAlign==undefined?'c':options.btnAlign,
		    scrollbar: false,
		    full:function(){
			  if(options.full!=undefined){
				  options.full(layerUI.layer, index);
		  	  }
		    },
		    min:function(){
			  if(options.min!=undefined){
				  options.min(layerUI.layer, index);
			  }
		    },
		    restore:function(){
			  if(options.restore!=undefined){
				  options.restore(layerUI.layer, index);
			  }
		    },
		    yes:function(){
			  if(options.yes!=undefined){
				  options.yes(layerUI.layer, index);
			  }
		    },
		    btn2:function(){
			  if(options.btn2!=undefined){
				  options.btn2(layerUI.layer, index);
			  }
			  return false;
		    },
		    btn3:function(){
			  if(options.btn3!=undefined){
				  options.btn3(layerUI.layer, index);
			  }
			  return false;
		    },
		    btn4:function(){
			  if(options.btn4!=undefined){
				  options.btn4(layerUI.layer, index);
			  }
			  return false;
		    },
		    cancel:function(){
			  if(options.cancel!=undefined){
				  options.cancel(layerUI.layer, index);
			  }
		    },
		    success:function(){
			  if(options.success!=undefined){
				  options.success(layerUI.layer, index);
			  }
		    },
		    end:function(){
				  flag = false;
				  setTimeout(function(){
					  flag = true;
				  }, 200);
				  if(options.end!=undefined){
					  options.end();
		     }
		  }
	   })
	   
	   //tab点击事件绑定
	   if(options.tabclick){
		   $(".layui-layer-title>span").click(function(){
			   options.tabclick($(this), $(this).parent().next(".layui-layer-content").find("iframe"));
		   })
	   }
   }
}
//初始化layerUI
layerUI.init();