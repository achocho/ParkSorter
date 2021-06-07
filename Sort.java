package InternshipExam;
import java.sql.*;
import java.util.*;
public class Sort {
public static Connection conn() throws Exception {
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
private static List<Float> Areas(Connection conn)throws Exception
{
	List<Float> areas=new ArrayList<>();
	Statement stmt = conn.createStatement();
	String sql = "SELECT * from parks";
	ResultSet rs = stmt.executeQuery(sql);

	while(rs.next()) 
	{
		areas.add(rs.getFloat(2));
		
	}
	return areas;
}
private static List<String> Descriptions(Connection conn)throws Exception
{
	List<String> description=new ArrayList<>();
	Statement stmt = conn.createStatement();
	String sql = "SELECT * from parks";
	ResultSet rs = stmt.executeQuery(sql);

	while(rs.next()) 
	{
		description.add(rs.getString(4));
		
	}
	return description;
}
private static List<String> Users(Connection conn) throws Exception
{
	List<String> users=new ArrayList<>();
	Statement stmt = conn.createStatement();
	String sql = "SELECT * from parks";
	ResultSet rs = stmt.executeQuery(sql);

	while(rs.next()) 
	{
		users.add(rs.getString(3));
		
	}
	return users;
}
private static List<Integer> Nums(Connection conn) throws Exception 
{
	List<Integer> nums=new ArrayList<>();
	Statement stmt = conn.createStatement();
	String sql = "SELECT * from parks";
	ResultSet rs = stmt.executeQuery(sql);

	while(rs.next()) 
	{
		nums.add(rs.getInt(1));
		
	}
	return nums;
}
private static List<String> AllInOne(List<Integer> nums,List<Float> areas,List<String> users,List<String> description)
{
	List<String> All=new ArrayList<>();
	for(int i=0;i<nums.size();i++) 
	{
	
	
			All.add(Integer.toString(nums.get(i))+" "+Float.toString(areas.get(i))+"m\u00B2 "+users.get(i)+" "+description.get(i));	
			
		
	}
	
	return All;
}
public static void Print() throws Exception
{
	Connection conn=conn();
	List<Integer> nums=Nums(conn);
	List<Float> areas=Areas(conn);
	List<String> users=Users(conn);
	List<String> description=Descriptions(conn);
	List<String> All=AllInOne(nums,areas,users,description);
	Sort(All,areas,users,description);
}
private static String[] Sort(List<String> All,List<Float> areas,List<String> users,List<String> description) 
{
String[] sort=new String[All.size()];
String temp="";
String tempDescription="";
float tempArea=0;
String tempUsers="";
int x=0;
for(int i=0;i<users.size();i++) 
{
	users.set(i, users.get(i).toLowerCase());
}
for(int i=0;i<sort.length;i++) 
{
	sort[i]=All.get(i);
}

for(int i=0;i<sort.length-1;i++) 
{
	for(int j=0;j<sort.length-1;j++) 
	{
		float area1=areas.get(j);
		float area2=areas.get(j+1);
		if(area1>area2) 
		{
		temp=sort[j];	
		sort[j]=sort[j+1];
		sort[j+1]=temp;
		tempArea=area1;
		areas.set(j, areas.get(j+1));
		areas.set(j+1, tempArea);
		tempUsers=users.get(j);
		users.set(j, users.get(j+1));
		users.set(j+1, tempUsers);
		tempDescription=description.get(j);
		description.set(j,description.get(j+1));
		description.set(j+1, tempDescription);
		} else if(area1==area2) 
		{

	for(int z=0;z<users.size()-1;z++) 
	{
	try {
		while(users.get(z).charAt(x)==users.get(z+1).charAt(x)) 
		{
			
			x++;
			
		
			
		}
	}catch(Exception e) 
	{
		continue;
	}
		if(users.get(z).charAt(x)>users.get(z+1).charAt(x)) 
		{
			temp=sort[j];	
			sort[j]=sort[j+1];
			sort[j+1]=temp;
			tempArea=area1;
			areas.set(j, areas.get(j+1));
			areas.set(j+1, tempArea);
			tempUsers=users.get(j);
			users.set(j, users.get(j+1));
			users.set(j+1, tempUsers);
			tempDescription=description.get(j);
			description.set(j,description.get(j+1));
			description.set(j+1, tempDescription);
		}
		
	}
		}
		
	}
}
for(int i=0;i<sort.length;i++) 
{
System.out.println(sort[i]);	
}
return sort;
}
}
