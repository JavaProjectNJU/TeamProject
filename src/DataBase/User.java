package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	public boolean createUser(String account,String Pw){
		boolean change = false;
		try {
			Statement statement = DataBase.connect().createStatement();
			String sql = "insert into USERTABLE(username,password) values('"
					+account+"','"+Pw+"');";
			statement.execute(sql);
			change = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return change;
	}
	
	public boolean changePassword(String account,String oldPw,String newPw){
		boolean change = false;
		try {
			Statement statement = DataBase.connect().createStatement();
			String sql = "select username,password from user;";
			ResultSet result = statement.executeQuery(sql);
			while(result.next()){
				if(account.equals(result.getString("username"))
						&&oldPw.equals(result.getString("password"))){
					statement.execute("update user set password = '"+newPw+"' where username = '"+account+"';");
					change = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return change;
	}
}
