$(document).ready(function(){
	
	//Đăng ký sự kiện click: $("tên_thẻ || tên_class || id").click()
	//class => .
	//id => #
	
	//Đăng ký sự kiện click cho toàn bộ class có tên là btn-xoa
	$(".btn-edit").click(function(){
		//Lấy giá trị của thuộc tính bên thẻ có class là .btn-xoa
		//$(this): đại diện cho function đang thực thi
		var id = $(this).attr('id-user')
		var name  = $("#name").val()
		var description  = $("#description").val()
		
		
		
		//RestTemplate, HttpClient
		$.ajax({
			  method: "POST",
			  url: `http://localhost:8080/crm_project_02/api/role/edit`, //string literals
			  data: { id:id , name:name, description:description } //Tham số data chỉ dành cho phương thức POST
			})
			  .done(function( result ) {
				  if(result.data==true){
					  alert("Cập nhật thành công")
				  }else{
					  alert("Cập nhật thất bại")
				  }
				  window.location.href = 'http://localhost:8082/crm_project_02/role-table'
			});
		
	})
	
	
	
	
	
})