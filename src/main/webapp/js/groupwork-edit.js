$(document).ready(function(){
	
	//Đăng ký sự kiện click: $("tên_thẻ || tên_class || id").click()
	//class => .
	//id => #
	
	//Đăng ký sự kiện click cho toàn bộ class có tên là btn-xoa
	$(".btn-edit").click(function(){
		//Lấy giá trị của thuộc tính bên thẻ có class là .btn-xoa
		//$(this): đại diện cho function đang thực thi
		var id = $(this).attr('id-project')
		var name  = $("#project-name").val()
		var startDate  = $("#startDate").val()
		var endDate  = $("#endDate").val()
		
		
		
		//RestTemplate, HttpClient
		$.ajax({
			  method: "POST",
			  url: `http://localhost:8080/crm_project_02/api/project/edit`, //string literals
			  data: { id:id , name:name, startDate:startDate, endDate:endDate } //Tham số data chỉ dành cho phương thức POST
			})
			  .done(function( result ) {
				  if(result.data==true){
					  alert("Cập nhật thành công")
				  }else{
					  alert("Cập nhật thất bại")
				  }
				  window.location.href = 'http://localhost:8082/crm_project_02/workgroup-table'
			});
		
	})
	
	
	
	
	
})