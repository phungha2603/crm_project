package crm_project_02.config;

import java.sql.Connection;
import java.sql.DriverManager;

//Cấu hình JDBC kết nối tới server MySQL
public class MysqlConfig {
	
	public static Connection getConnection() {
		
		try {
			//Khai báo Driver sử dụng cho JDBC tương ứng với CSDL cần kết nối
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Khai báo Driver sẽ mở kết nối tới CSDL nào
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/Project","root","admin123");
		}catch(Exception e) {
			//Lỗi xảy ra sẽ chạy vào đây
			System.out.println("Lỗi kết nối database " + e.getLocalizedMessage());
			return null;
		}
		
		
		
		
		
	}
	
}
