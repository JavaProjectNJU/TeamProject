package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserManager {
	private static Connection conn = null;
	private static ArrayList<User> onlineUser;
	public static boolean createUser(String account,String Pw){
		boolean change = false;
		try {
			conn = DataBase.connect();
			Statement statement = conn.createStatement();
			String sql = "insert into USERTABLE(username,password) values('"
					+account+"','"+Pw+"');";
			change = statement.execute(sql);
			DataBase.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return change;
	}
	
	public static boolean addFriend(String account1, String account2){
		boolean change = false;
		try {
			conn = DataBase.connect();
			Statement statement = conn.createStatement();
			String sql = "insert into FriendRelation(username1,username2) values('"
					+account1+"','"+account2+"');";
			change = statement.execute(sql);
			DataBase.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return change;
	}
	
	public static boolean delFriend(String account1, String account2){
		boolean change = false;
		try {
			conn = DataBase.connect();
			Statement statement = conn.createStatement();
			String sql = "delete from FriendRelation where username1='"
					+account1+"' and username2='"+account2+"';";
			change = statement.execute(sql);
			DataBase.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return change;
	}
	
	public static boolean changePassword(String account,String oldPw,String newPw){
		boolean change = false;
		try {
			conn = DataBase.connect();
			Statement statement = conn.createStatement();
			String sql = "select username,password from user;";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()){
				if(account.equals(result.getString("username"))
						&&oldPw.equals(result.getString("password"))){
					change = statement.execute("update user set password = '"+newPw+"' where username = '"+account+"';");
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DataBase.close(conn);
		return change;
	}
	
	@SuppressWarnings("finally")
	public static boolean identityVerify(String account,String Pw){
		boolean isValid = false;
		try {
			conn = DataBase.connect();
			Statement statement = conn.createStatement();
			String sql = "select username,password from user;";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()){
				if(account.equals(result.getString("username"))
						&&Pw.equals(result.getString("password"))){
					isValid = true;
					break;
				}
			}
			DataBase.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			return isValid;
		}
	}
	
	@SuppressWarnings("finally")
	public static boolean friendJudge(String account1,String account2){
		boolean isFriend = false;
		try {
			conn = DataBase.connect();
			Statement statement = conn.createStatement();
			String sql = "select username1,username2 from FriendRelation "
					+ "where username1 = '"+ account1 +"' and username2 = '"+ account2 +"';";
			ResultSet result = statement.executeQuery(sql);
			if(result.next())
				isFriend = true;
			DataBase.close(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			return isFriend;
		}
	}
	
	public static boolean addOnlineUser(User user){
		boolean change = false;
		change = onlineUser.add(user);
		return change;
	}
	
	public static boolean delOnlineUser(User user){
		boolean change = false;
		change = onlineUser.remove(user);
		return change;
	}
	
	public static ArrayList<User> getOnlineUser(){
		return onlineUser;
	}
}