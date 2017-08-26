/**
 * 
 */
function validateData() {
	var userName = $("#username");
	var userPwd = $("#userpwd");
	if (userName.val().length <= 0) {
		alert("请输入用户名!");
		userName.focus();
		return false;
	}
	if (userPwd.val().length <= 0) {
		alert("请输入密码!");
		userPwd.focus();
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
			 url : $('#contextPath').val() + "/admins/editSubmit.action?task=" + (new Date()).getTime(),
			 dataType : "json",
			 success : function(data) {
				 var result = data.result;
				 var errorMsg = data.errorMsg;
				 if (result == "success") {
					 alert("提交成功");
					 window.location.href = $('#contextPath').val() + "/admins/main.action";
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
