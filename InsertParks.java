package InternshipExam;
import java.sql.*;
public class InsertParks {
public static void Insert(float area,String name,String description) throws Exception
{
	Connection conn=conn();
	if(Count(conn)) {
	InsertPlace(conn,area,name,description);
	System.out.println("Park place Inserted");
	}else 
	{
		System.out.println("No space left!");
	}
}
private static Connection conn() throws Exception {
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "park";
	String driver = "com.mysql.cj.jdbc.Driver";
	String userName = "root";
	String password = "";
	Class.forName(driver).newInstance();
	conn = DriverManager.getConnection(url + dbName, userName, password);
	return conn;

}
private static void InsertPlace(Connection conn,float area,String user,String description) throws Exception
{
	PreparedStatement ps = conn.prepareStatement("insert into parks values(?,?,?,?)");
    ps.setInt(1, 0);
	ps.setFloat(2, area);
	ps.setString(3, user);
	ps.setString(4, description);
	ps.executeUpdate();
}
private static boolean Count(Connection conn) throws Exception
{
	PreparedStatement statement=null;
	String sql1 = "SELECT COUNT(*) FROM parks";
	 statement = null;
    ResultSet resultSet = null;
    statement = conn.prepareStatement(sql1);
    resultSet = statement.executeQuery();
    int out=0;
    if(resultSet.next()) {
      out=resultSet.getInt(1);

  
    }
    statement.close();
    if(out<154) 
    {
    	return true;
    	
    }
	return false;
}
}
