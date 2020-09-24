package main.game;

import java.sql.Connection;

import com.dbservice.impl.DbServiceImpl;
import com.event.impl.Play;
import com.event.impl.Talk;



public class Test {
	Connection CONN;
	DbServiceImpl DBSVR;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Test t = new Test();
		t.initDb();

		LogIn li = new LogIn("lucksun", t.DBSVR);
		li.loadLogIn();
		Run r = new Run(li, t.DBSVR);
		try {
			r.process();
		} finally {
			t.DBSVR.commitDB();
		}

	}

	private void initDb() throws Exception {
		DBSVR = new DbServiceImpl();
		DBSVR.intiDB();
		CONN = DBSVR.getCONN();

		// 检查数据库完整性，如果缺少则重建数据结构，并初始化数据
		// 创建持久化连接直到程序结束

	}
}
