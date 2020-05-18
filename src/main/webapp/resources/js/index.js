/*var tab = "${tab}";
var url = "/?tab="+tab+"&"*/
$(function(){
  $("#shouye").addClass("active");
   
   function showScroll(){
    $(window).scroll( function(){ 
     var scrollValue=$(window).scrollTop();
			//console.log(scrollValue);
			if (scrollValue > 200)
			{
				$('#back2Top').css("display","flex");
			} else {
				$('#back2Top').css("display","none");
			}
		});	
  }
  showScroll();
  $("#back2Top").click(function(){
		//缓慢效果回到顶部
		$('body,html').animate({scrollTop:0},500);
		return false;
		//直接回到顶部
		//window.scroll(0,0);
	});

  /*积分榜*/
  (function () {
    $.ajax({
     url:"/api/user/top100",
     type:"get",
     dataType:"json",
     data:{limit:10},
     success:function(data){
      if(data.success == true){
    	  $(".panel .top100").append('\
    			  <ol></ol>\
    	  ');
    	  for(var i = 0;i < data.data.length;i++){
    		  $(".panel .top100 ol").append('\
    				  <li>\
    				  <span class="top_score">'+data.data[i].score+'</span>\
    				  <span class="user_name"><a href="/user/'+data.data[i].userName+'">'+data.data[i].userName+'</a></span>\
    				  </li>\
    		  ');
    	  }
      }
     },
     error:function(data){
    	 
 	}
    });
  })();
  
  //格式化时间
  $(".formate-date").each(function(i,e){
	  // console.log(formatDate(Date.parse($(this).text())));
	  $(this).text(formatDate(Date.parse($(this).text())));
  })
});