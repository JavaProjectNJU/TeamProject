package DataBase;

/**
* ���ݿ����ӳ���
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

public class DataBaseConnectionPool
{
	private Connection con = null;
	private int inUsed = 0;    //ʹ�õ�������
	private ArrayList<Connection> freeConnections = new ArrayList<Connection>();//��������������
	private int minConn;     //��С������
	private int maxConn;     //�������
	private String name;     //���ӳ�����
	private String password; //����
	private String url;      //���ݿ����ӵ�ַ
	private String driver;   //����
	private String user;     //�û���
	public Timer timer;      //��ʱ

	public DataBaseConnectionPool() {
		// TODO Auto-generated constructor stub
	}
/**
* �������ӳ�
* @param driver
* @param name
* @param URL
* @param user
* @param password
* @param maxConn
*/
	public DataBaseConnectionPool(String name, String driver,String URL, String user, String password, int maxConn)
	{
		this.name = name;
		this.driver = driver;
		this.url = URL;
		this.user = user;
		this.password = password;
		this.maxConn = maxConn;
	}

	public synchronized void freeConnection(Connection con) 
	{
		this.freeConnections.add(con);//��ӵ��������ӵ�ĩβ
		this.inUsed --;
	}

	public synchronized Connection getConnection(long timeout)
	{
		Connection con = null;
		if(this.freeConnections.size()>0)
		{
			con = (Connection)this.freeConnections.get(0);
			freeConnections.remove(con); //Right??????????????????
			
			if(con == null)
				con = getConnection(timeout); //�����������
		}
		else
		{
			con = newConnection(); //�½�����
		}
		if(this.maxConn == 0 || this.maxConn < this.inUsed)
		{
			con = null;//�ﵽ�������������ʱ���ܻ�������ˡ�
		}
		if(con != null)
		{
			this.inUsed++;
		}
		return con;
	}

	public synchronized Connection getConnection()
	{
		Connection con = null;
		if(this.freeConnections.size()>0)
		{
			con=(Connection)this.freeConnections.get(0);
			this.freeConnections.remove(0);//������ӷ����ȥ�ˣ��ʹӿ���������ɾ��
			if(con==null)con = getConnection(); //�����������
		}
		else
		{
			con=newConnection(); //�½�����
		}
		if(this.maxConn == 0 || this.maxConn<this.inUsed)
		{
			con = null;//�ȴ� �����������ʱ
		}
		if(con != null)
		{
			this.inUsed ++;
			System.out.println("�õ���" + this.name + "�������ӣ�����" + inUsed + "��������ʹ��!");
		}
		return con;
	}

	public synchronized void release()
	{
		@SuppressWarnings("rawtypes")
		Iterator allConns = this.freeConnections.iterator();
		while(allConns.hasNext())
		{
			Connection con = (Connection)allConns.next();
			try
			{
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}

		}
		this.freeConnections.clear();
	}

	private Connection newConnection()
	{
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sorry can't find db driver!");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("sorry can't create Connection!");
		}
		return con;
	}

	public synchronized void TimerEvent() 
	{
		//��ʱ��û��ʵ���Ժ����ϵ�
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public int getMaxConn() {
		return maxConn;
	}

	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	public int getMinConn() {
		return minConn;
	}

	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
