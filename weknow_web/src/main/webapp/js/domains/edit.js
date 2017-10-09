/*

 * 李海旺

 * 区域管理模块前台修改投诉建议内容对应的js文件，参照admins/edit.js完成

 */

function validateData() {

	var parentid = $("#parentid");
	var name = $("#name");
	var id = $("#id")
	if (id.val().length <= 0) {
		alert("请输入区域名代码!");
		id.focus();
		return false;
	}

	if (parentid.val().length <= 0) {

		alert("请输入区域名称!");
		parentid.focus();
		return false;
	}
	if (name.val().length <= 0) {

		alert("请输入区域所属地代码!");
		name.focus();
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
			 url : $('#contextPath').val() + "/domains/editSubmit.action?task=" + (new Date()).getTime(),
			 dataType : "json",
			 success : function(data) {
				 var result = data.result;
				 var errorMsg = data.errorMsg;
				 if (result == "success") {
					 alert("提交成功");
					 window.location.href = $('#contextPath').val() + "/domains/main.action";
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