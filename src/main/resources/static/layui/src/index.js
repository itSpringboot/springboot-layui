var $,tab,dataStr,layer;
layui.config({
	base : "../src/"
}).extend({
	"bodyTab" : "bodyTab"
})
layui.use(['bodyTab','form','element','layer','jquery'],function(){
    var doc = document, config = {
        modules: {} //记录模块物理路径
        ,status: {} //记录模块加载状态
        ,timeout: 10 //符合规范的模块请求最长等待秒数
        ,event: {} //记录模块自定义事件
    }
    //获取layui所在目录
    getPath = function(){
        var jsPath = doc.currentScript ? doc.currentScript.src : function(){
            var js = doc.scripts
                ,last = js.length - 1
                ,src;
            for(var i = last; i > 0; i--){
                if(js[i].readyState === 'interactive'){
                    src = js[i].src;
                    break;
                }
            }
            return src || js[last].src;
        }();
        return jsPath.substring(0, jsPath.lastIndexOf('/') + 1);
    }()
	var form = layui.form,
		element = layui.element;
		$ = layui.$;
    	layer = parent.layer === undefined ? layui.layer : top.layer;
		tab = layui.bodyTab({
			topMenu:window.sessionStorage.getItem("menutype")==2?true:false,//是否展现顶部菜单
			maxTopMenus:"5",//顶部菜单展现最多数量
			openTabNum : "50",  //最大可打开窗口数量
			url : getPath + "/service/api/bsp/menus/topmenus?userId="+UsrName+"&menuTypeId="+menuTypeId //获取菜单json地址
		});
	$(document).ready(function () {
		getTopMenu();
    });
	function getTopMenu(){
		$.get(tab.tabConfig.url,function(ret){
			if(ret == "error"||(eval("("+ret+")")&&eval("("+ret+")").error)){
				layer.open({
					type: 1
					,title: false //不显示标题栏
					,closeBtn: false
					,area: '300px;'
					,shade: 0.8
					,id: 'LAY_layuipro' //设定一个id，防止重复弹出
					,btn: ['重新登录', '取消']
					,btnAlign: 'c'
					,moveType: 1 //拖拽模式，0或者1
					,content: '<div style="padding: 30px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 500; text-align: center">会话已失效，请重新登录</div>'
					,success: function(layero){
						var btn = layero.find('.layui-layer-btn');
						btn.find('.layui-layer-btn0').attr({
							href: context
						});
					}
				});
				return ;
			}
			var data = eval("("+ret+")");
			var topMenus = data.menu.rows;
			var data;
			if(typeof(topMenus) == "string"){
				var data = JSON.parse(topMenus); //部分用户解析出来的是字符串，转换一下
			}else{
				data = topMenus;
			}
			var ulHtml = '';
			var noTopMenu = '<li class="layui-nav-item layui-this"><a data-url="page/main.html"><i class="layui-icon" data-icon=""></i><cite>首页</cite></a></li>';
			var mobilHtml = '';
			var defaultMenu = '';
			for(var i=0;i<data.length;i++){
				if(i==tab.tabConfig.maxTopMenus-1&&data.length>tab.tabConfig.maxTopMenus){
					ulHtml+='<li class="layui-nav-item">';
					ulHtml+='<a href="javascript:;">';
					ulHtml += '<i class="layui-icon" data-icon="&#xe653;">&#xe653;</i>';
					ulHtml+='<cite>更多</cite></a>';
					ulHtml+='<dl class="layui-nav-child">';
				}
				if(data[i].default==true){
					if(i>=tab.tabConfig.maxTopMenus-1){
						ulHtml+='<dd class="layui-this" data-menu="'+data[i].id+'">';
					}else{
						ulHtml+='<li class="layui-nav-item layui-this" data-menu="'+data[i].id+'">';
					}

					mobilHtml+='<dd class="layui-this" data-menu="'+data[i].id+'">';
					noTopMenu+= '<li class="layui-nav-item layui-nav-itemed"  data-menu="'+data[i].id+'">';
					defaultMenu = data[i].id;
				}else{
					if(i>=tab.tabConfig.maxTopMenus-1){
						ulHtml+='<dd  data-menu="'+data[i].id+'">';
					}else{
						ulHtml+='<li class="layui-nav-item" data-menu="'+data[i].id+'">';
					}
					mobilHtml+='<dd  data-menu="'+data[i].id+'">';
					noTopMenu+= '<li class="layui-nav-item" data-menu="'+data[i].id+'">';
				}
				if(data[i].isLeaf=="true"){
					ulHtml += '<a data-url="'+data[i].url+'">';
					mobilHtml+='<a data-url="'+data[i].url+'">';
					noTopMenu+= '<a data-url="'+data[i].url+'">';
				}else{
					ulHtml+='<a href="javascript:;">';
					mobilHtml+='<a href="javascript:;">';
					noTopMenu += '<a>';
				}

				var icon = data[i].icon;
				if(icon==''||icon==null||icon.match(/\.(jpeg|jpg|gif|png)$/) != null){
					ulHtml += '<i class="layui-icon" data-icon="&#xe63c;">&#xe63c;</i>';
					mobilHtml += '<i class="layui-icon" data-icon="&#xe63c;">&#xe63c;</i>';
					noTopMenu+= '<i class="layui-icon" data-icon="&#xe63c;">&#xe63c;</i>';
				}else if(icon.indexOf("icon-") != -1){
					ulHtml += '<i class="seraph '+icon+'" data-icon="'+icon+'"></i>';
					mobilHtml += '<i class="seraph '+icon+'" data-icon="'+icon+'"></i>';
					noTopMenu += '<i class="seraph '+icon+'" data-icon="'+icon+'"></i>';
				}else{
					ulHtml += '<i class="layui-icon" data-icon="'+icon+'">'+icon+'</i>';
					mobilHtml += '<i class="layui-icon" data-icon="'+icon+'">'+icon+'</i>';
					noTopMenu += '<i class="layui-icon" data-icon="'+icon+'">'+icon+'</i>';
				}
				ulHtml+='<cite>'+data[i].text+'</cite></a>';
				mobilHtml+='<cite>'+data[i].text+'</cite></a>';
				noTopMenu +='<cite>'+data[i].text+'</cite>';
				if(i<tab.tabConfig.maxTopMenus-1){
					ulHtml+='</li>';
				}

				mobilHtml+='</li>';
				if(data[i].isLeaf=="false"){
					noTopMenu += '<span class="layui-nav-more"></span>';
					noTopMenu += '</a>';
					noTopMenu += '<dl class="layui-nav-child">';

					noTopMenu += '</dl>';
				}else{
					noTopMenu += '</a>';
				}
				noTopMenu += '</li>';
			}
			if(data.length>tab.tabConfig.maxTopMenus){
				ulHtml+='</dl>';
				ulHtml+='</li>';
			}
			if(tab.tabConfig.topMenu){
				$("#menu").html(ulHtml);
				$(".mobileTopLevelMenus .layui-nav-child").html(mobilHtml);
				getData(defaultMenu==""?data[0].id:defaultMenu);
				//页面加载时判断左侧菜单是否显示
				//通过顶部菜单获取左侧菜单
				$(".topLevelMenus li,.topLevelMenus li dd,.mobileTopLevelMenus dd").click(function(){
					if($(this).parents(".mobileTopLevelMenus").length != "0"){
						$(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
					}else{
						$(".mobileTopLevelMenus dd").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
					}
					$(".layui-layout-admin").removeClass("showMenu");
					$("body").addClass("site-mobile");
					getData($(this).data("menu"));
					//渲染顶部窗口
					tab.tabMove();
				});
			}else{
				$(".navBar").height($(window).height()-94);
				$(".navBar ul").html(noTopMenu);
				element.init();  //初始化页面元素
				$(window).resize(function(){
					$(".navBar").height($(window).height()-94);
				})
			}
			element.on('nav(tree)', function(elem){
				//console.log(elem)
				//layer.msg(elem.html());
				return;
			});


		});
	}
	//通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
	function getData(json){
		$.get(getPath+"/service/api/bsp/menus/"+json+"/submenus?userId="+UsrName+"&menuTypeId="+menuTypeId,function(data){
			if(data == "error"||(eval("("+data+")")&&eval("("+data+")").error)){
				layer.open({
					type: 1
					,title: false //不显示标题栏
					,closeBtn: false
					,area: '300px;'
					,shade: 0.8
					,id: 'LAY_layuipro' //设定一个id，防止重复弹出
					,btn: ['重新登录', '取消']
					,btnAlign: 'c'
					,moveType: 1 //拖拽模式，0或者1
					,content: '<div style="padding: 30px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 500; text-align: center">会话已失效，请重新登录</div>'
					,success: function(layero){
						var btn = layero.find('.layui-layer-btn');
						btn.find('.layui-layer-btn0').attr({
							href: context
						});
					}
				});
//提示登录
				return ;
			}
			dataStr = eval("("+data+")");
				//重新渲染左侧菜单
				tab.render();

		})
	}
	//通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
	function getDataNoTop(json,dl){
		$.get(getPath+"/service/api/bsp/menus/"+json+"/submenus?userId="+UsrName+"&menuTypeId="+menuTypeId,function(data){
			if(data == "error"||(eval("("+data+")")&&eval("("+data+")").error)){
				layer.open({
					type: 1
					,title: false //不显示标题栏
					,closeBtn: false
					,area: '300px;'
					,shade: 0.8
					,id: 'LAY_layuipro' //设定一个id，防止重复弹出
					,btn: ['重新登录', '取消']
					,btnAlign: 'c'
					,moveType: 1 //拖拽模式，0或者1
					,content: '<div style="padding: 30px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 500; text-align: center">会话已失效，请重新登录</div>'
					,success: function(layero){
						var btn = layero.find('.layui-layer-btn');
						btn.find('.layui-layer-btn0').attr({
							href: context
						});
					}
				});
//提示登录
				return ;
			}
			dataStr = eval("("+data+")");
			//重新渲染左侧菜单
			tab.renderNoTop(dl);

		})
	}
	$(".signOut").click(function(){
		layer.open({
			type: 1
			,title: false //不显示标题栏
			,closeBtn: false
			,area: '300px;'
			,shade: 0.8
			,id: 'LAY_layuipro' //设定一个id，防止重复弹出
			,btn: ['退出', '取消']
			,btnAlign: 'c'
			,moveType: 1 //拖拽模式，0或者1
			,content: '<div style="padding: 30px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 500; text-align: center">您确定要退出系统吗？</div>'
			,success: function(layero){
				var btn = layero.find('.layui-layer-btn');
				btn.find('.layui-layer-btn0').attr({
					href: context+"/logout"
				});
			}
		});
	});

	//隐藏左侧导航
	$(".hideMenu").click(function(){
		if($(".topLevelMenus li.layui-this a").data("url")){
			layer.msg("此栏目状态下左侧菜单不可展开");  //主要为了避免左侧显示的内容与顶部菜单不匹配
			return false;
		}
		$(".layui-layout-admin").toggleClass("showMenu");
		//渲染顶部窗口
		tab.tabMove();
	})

	//通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
	

	//手机设备的简单适配
    $('.site-tree-mobile').on('click', function(){
		$('body').addClass('site-mobile');
	});
    $('.site-mobile-shade').on('click', function(){
		$('body').removeClass('site-mobile');
	});

	// 添加新窗口
	$("body").on("click",".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')",function(){
		if(tab.tabConfig.topMenu){
			//如果不存在子级
			if($(this).siblings().length == 0){
				addTab($(this));
				$('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
			}
			$(this).parent("li").siblings().removeClass("layui-nav-itemed");
		}else{
			var dlLength = $(this).parent("li").children("dl").length;
			if(dlLength==1){
				dlLength = $(this).parent("li").children("dl").children().length;
				if(dlLength==0){
					getDataNoTop($(this).parent("li").data("menu"),$(this).parent("li").children("dl"));
				}
			}else{
				addTab($(this));
				$('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
			}
		}

	});

	//清除缓存
	$(".clearCache").click(function(){
		window.sessionStorage.clear();
        window.localStorage.clear();
        var index = layer.msg('清除缓存中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("缓存清除成功！");
        },1000);
    });

	//刷新后还原打开的窗口
    if(cacheStr == "true") {
        if (window.sessionStorage.getItem("menu") != null) {
            menu = JSON.parse(window.sessionStorage.getItem("menu"));
            curmenu = window.sessionStorage.getItem("curmenu");
            var openTitle = '';
            for (var i = 0; i < menu.length; i++) {
                openTitle = '';
                if (menu[i].icon) {
                    if (menu[i].icon.split("-")[0] == 'icon') {
                        openTitle += '<i class="seraph ' + menu[i].icon + '"></i>';
                    } else {
                        openTitle += '<i class="layui-icon">' + menu[i].icon + '</i>';
                    }
                }
                openTitle += '<cite>' + menu[i].title + '</cite>';
                openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + menu[i].layId + '">&#x1006;</i>';
                element.tabAdd("bodyTab", {
                    title: openTitle,
                    content: "<iframe src='" + menu[i].href + "' data-id='" + menu[i].layId + "'></frame>",
                    id: menu[i].layId
                })
                //定位到刷新前的窗口
                if (curmenu != "undefined") {
                    if (curmenu == '' || curmenu == "null") {  //定位到后台首页
                        element.tabChange("bodyTab", '');
                    } else if (JSON.parse(curmenu).title == menu[i].title) {  //定位到刷新前的页面
                        element.tabChange("bodyTab", menu[i].layId);
                    }
                } else {
                    element.tabChange("bodyTab", menu[menu.length - 1].layId);
                }
            }
            //渲染顶部窗口
            tab.tabMove();
        }
    }else{
		window.sessionStorage.removeItem("menu");
		window.sessionStorage.removeItem("curmenu");
	}
})

//打开新窗口
function addTab(_this){
	tab.tabAdd(_this);
}


//图片管理弹窗
function showImg(){
    $.getJSON('json/images.json', function(json){
        var res = json;
        layer.photos({
            photos: res,
            anim: 5
        });
    });
}