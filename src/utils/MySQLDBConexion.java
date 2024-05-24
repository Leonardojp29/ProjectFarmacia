package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLDBConexion {

	public static Connection getConexion() {
		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/BD_Farmacia", "root", "mysql");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
}