import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	private static Connection conn = null;
	public static Connection getConnection() {
		
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/D:\\Work space\\PC\\Uni\\Java OOP\\h2\\myDB", "sa", "");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver problem");
		} catch (SQLException e) {
			System.out.println("DriverManager exception");
		}
		return conn;	
	}
}
