package DataBase;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBase {

    /**
     * @param args the command line arguments
     */
    //jdbc������λ��
    private static String driver = "com.mysql.jdbc.Driver";
    //���������ݿ⡰myfirstdbexample���������ϵ�λ��
    private static String url = "jdbc:mysql://localhost:3306/JavaProject"+
		"?useUnicode=true&characterEncoding=utf8";
    //�û���������
    private static String user = "root";
    private static String password = "zhangruiyi";
    //���Ӿ��
    private static DataBaseConnectionPool dBPool = new DataBaseConnectionPool("Local", driver, url,
    		user, password, 1000);
    public static Connection conn = null;
    
    //��������
    public static Connection connect(){
    	conn = dBPool.getConnection();
    	try {
			if(!conn.isClosed()){
			    System.out.println("�ɹ��������ݿ⣡");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return conn;
    }
    public static void close(Connection currentConn){
    	dBPool.freeConnection(currentConn);
	}
    public static void closeAll(){
    	dBPool.release();
    }
}
