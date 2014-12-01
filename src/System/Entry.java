package System;

import DataBase.DataBase;
import DataBase.User;

public class Entry {
	public static void main(String[] args)
	{
		System.out.println("Hello,This is the sever!!");
		//DataBase.connect();
		User usr = new User();
		usr.createUser("roy3", "123456");
	}
}
