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
    public static Connection conn = null;
    private static DataBaseConnectionPool dBPool = new DataBaseConnectionPool("Local", driver, url,
    		user, password, 1000);
    
    //��������
    public static Connection connect(){
        /*try{
            Class.forName(driver);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        } 
        //���ø��������ݿ�λ�ã��û����������������ݿ�
	    try{
            conn = DriverManager.getConnection(url, user, password);
            if(!conn.isClosed()){
                System.out.println("�ɹ��������ݿ⣡");
            }   
        }catch(SQLException e){
            e.printStackTrace();
        }*/
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
      /*  try {
	    conn.close();
	} catch (SQLException e) {
            e.printStackTrace();
	}
					
	try {
		if(conn.isClosed()){
			System.out.println("�رյ����ݿ������~");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
    	dBPool.freeConnection(currentConn);
	}
    public static void closeAll(){
    	dBPool.release();
    }
}
