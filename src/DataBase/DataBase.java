package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

    /**
     * @param args the command line arguments
     */
    //jdbc驱动的位置
    private static String driver = "com.mysql.jdbc.Driver";
    //测试用数据库“myfirstdbexample”在网络上的位置
    private static String url = "jdbc:mysql://localhost:3306/JavaProject"+
		"?useUnicode=true&characterEncoding=utf8";
    //用户名和密码
    private static String user = "root";
    private static String password = "zhangruiyi";
    //连接句柄
    public static Connection conn = null;
    
    //建立连接
    public static Connection connect(){
        try{
            Class.forName(driver);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        } 
        //利用给定的数据库位置，用户名和密码连接数据库
	    try{
            conn = DriverManager.getConnection(url, user, password);
            if(!conn.isClosed()){
                System.out.println("成功连接数据库！");
            }   
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void close(){
        try {
	    conn.close();
	} catch (SQLException e) {
            e.printStackTrace();
	}
					
	try {
		if(conn.isClosed()){
			System.out.println("关闭到数据库的连接~");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
