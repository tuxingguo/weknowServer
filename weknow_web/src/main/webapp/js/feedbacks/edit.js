/*
 * 涂兴国
 * 投诉建议模块前台修改投诉建议内容对应的js文件，参照admins/edit.js完成
 */
/**
 * 
 */
function validateData() {
	var userid = $("#userid");
	var content = $("#content");
	if (userid.val().length <= 0) {
		alert("请输入用户名!");
		userid.focus();
		return false;
	}
	if (content.val().length <= 0) {
		alert("请输入建议投诉内容!");
		content.focus();
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
			 url : $('#contextPath').val() + "/feedbacks/editSubmit.action?task=" + (new Date()).getTime(),
			 dataType : "json",
			 success : function(data) {
				 var result = data.result;
				 var errorMsg = data.errorMsg;
				 if (result == "success") {
					 alert("提交成功");
					 window.location.href = $('#contextPath').val() + "/feedbacks/main.action";
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
