//生成弹出层的代码
xOffset = 0;//向右偏移量
yOffset = 35;//向下偏移量
var toshow = "treediv";//要显示的层的id
var target = "pId2";//目标控件----也就是想要点击后弹出树形菜单的那个控件id
$("#"+target).click(function (){
	$("#"+toshow)
	.css("position", "absolute")
	.css("left", $("#"+target).position().left+xOffset + "px")
	.css("top", $("#"+target).position().top+yOffset +"px").show();
});
//判断鼠标在不在弹出层范围内
 function   checkIn(id){
	var yy = 20;   //偏移量
	var str = "";
	var   x=window.event.clientX;   
	var   y=window.event.clientY;   
	var   obj=$("#"+id)[0];
	if(x>obj.offsetLeft&&x<(obj.offsetLeft+obj.clientWidth)&&y>(obj.offsetTop-yy)&&y<(obj.offsetTop+obj.clientHeight)){   
		return true;
	}else{   
		return false;
	}   
  }   
//点击菜单树给文本框赋值
function setvalue(id,deptName){
	$("#pId2").val(deptName);
	$("#did").val(id);
	$("#treediv").hide();
}
//鼠标移除idv关闭
$("#treediv").mouseout(function () {
    var s = event.toElement || event.relatedTarget;
    if (!this.contains(s)) { $(this).hide(); }
});