/**
*说明:User js 脚本
*作者:@haipenge
*/
var User={
  init:function(){
	  $('#init-security').click(function(){User.initSecurity();});
  },
  initSecurity:function(){
	  alert(1);
	  $.ajax({
		  url:'/security/user/initSecurity',
		  type:'post',
		  success:function(data,textStatus,xhr){
			  var msg=new Msg({msg:'权限初始化完成'});
			  msg.show();
		  }
	  });
  }
};
$(document).ready(function(){User.init();});