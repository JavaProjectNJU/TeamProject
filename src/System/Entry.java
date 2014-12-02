package System;

import net.FromBaidu;
import net.WordEngine;
import DataBase.DataBase;
import DataBase.User;

public class Entry {
	public static void main(String[] args)
	{
		System.out.println("Hello,This is the sever!!");
		//DataBase.connect();
		//User usr = new User();
		//usr.createUser("roy3", "123456");
		WordEngine baidu = new FromBaidu();
		baidu.search("give");
	}
}
