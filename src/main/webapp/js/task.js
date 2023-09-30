$(document).ready(function(){
	$(".btn-xoa").click(function(){
		var id = $(this).attr('id-task')
		var This = $(this)
		$.ajax({
			  method: "GET",
			  url: `http://localhost:8080/crm_project_02/api/task/delete?id=${id}`, //string literals
			  //data: { name: "John", location: "Boston" } //Tham số data chỉ dành cho phương thức POST
			})
			  .done(function( result ) {
				  if(result.data==true){
					  This.closest('tr').remove()
				  }
				  console.log(result)
			});
		
	})
	
})