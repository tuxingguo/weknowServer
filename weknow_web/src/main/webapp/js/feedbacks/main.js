/*
 * 涂兴国
 * 投诉建议模块前台浏览投诉建议对应的js文件，参照admins/main.js完成
 */
/**
 * 
 */
function onAdd() {
	window.location.href = $('#contextPath').val() + "/feedbacks/add.action";
}

var deleteId;
function onSetId(id) {
	deleteId = id;
}

function onDelete() {
	$.ajax({
		type : "POST",
		url : $('#contextPath').val() + "/feedbacks/delete.action?id=" + deleteId + "&task="
				+ (new Date()).getTime(),
		dataType : "json",
		success : function(data) {
			var result = data.result;
			var errorMsg = data.errorMsg;
			if (result == "success") {
				alert("删除成功");
				window.location.href = $('#contextPath').val() + "/feedbacks/main.action";
			} else {
				alert("删除失败：" + errorMsg);
			}
		},
		error : function(xhr, textStatus, errorThrown) {
			if (textStatus != null)
				alert(textStatus);
			else if (errorThrown)
				alert(errorThrown);
			else
				alert("删除失败-未知错误");
		}
	});
}