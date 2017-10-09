function fn_image(){ 
	
	if(confirm("你确定删除已上传的图片吗？"))
	{
		var obj=document.getElementById("imageurl");
			obj.value="";				   
	}
}
function fn_audio(){ 
	
	if(confirm("你确定删除已上传的音频吗？"))
	{
		var obj=document.getElementById("audiourl");
			obj.value="";				   
	}
}
function fn_video(){ 
	
	if(confirm("你确定删除已上传的视频吗？"))
	{
		var obj=document.getElementById("videourl");
			obj.value="";				   
	}
}
function validateData() {
	
	var title = $("#title");
	var content = $("#content");
	var authorid = $("#authorid");
	
	if (title.val().length <= 0) {
		alert("请输入标题!");
		title.focus();
		return false;
	}

	if (content.val().length <= 0) {

		alert("请输入内容!");
		content.focus();
		return false;
	}
	
	if (authorid.val().length <= 0) {

		alert("请输入作者id!");
		authorid.focus();
		return false;
	}
	
	return true;

}

 $(document).ready(function() {
	 $('#btnSubmit').click(function() {
		 if (validateData() == false)
			 return;
		 $("#tab").ajaxSubmit({
			 type : "POST",
			 url : $('#contextPath').val() + "/news/editSubmit.action?task=" + (new Date()).getTime(),
			 dataType : "json",
			 success : function(data) {
				 var result = data.result;
				 var errorMsg = data.errorMsg;
				 if (result == "success") {
					 alert("提交成功");
					 window.location.href = $('#contextPath').val() + "/news/main.action";
				 } else {
					 alert("提交失败：" + errorMsg);
				 }
			 },
			 error : function(xhr, textStatus, errorThrown) {
				 if (textStatus != null)
					 alert(textStatus);
				 else if (errorThrown)
					 alert(errorThrown);
				 else
					 alert("提交失败-未知错误");
			 }
		 });
	 });
 });