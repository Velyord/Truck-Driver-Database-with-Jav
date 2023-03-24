import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	static Connection conn=null;
	
	static Connection getConnection() {
		
		
		try {
			Class.forName("org.h2.Driver");
			String item=IOUtil.readFileByRow("C:\\Users\\User\\Desktop\\2020\\path.txt").toString();
			//System.out.println(item);
			//conn=DriverManager.getConnection("jdbc:h2:tcp://localhost/C:\\Users\\User\\Desktop\\2020\\carDB", "sa", "");
			conn=DriverManager.getConnection("jdbc:h2:tcp://localhost/"+item.substring(item.indexOf("=")+1, item.length()-1), "sa", "");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}

}
