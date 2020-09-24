package main.game;



import java.util.Scanner;

import com.dbservice.ConnectTionManager;
import com.dbservice.impl.DbServiceImpl;
import com.message.SendMessage;



public class Intro {
	DbServiceImpl DBSVR;
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Intro bs = new Intro();
		bs.initDb();
		
		SendMessage.sendMessage(
				  "-------------Blade Sword-------\n"
				+ "-------------LogIn-------------\n"
				+ "input name:");
		
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		LogIn login = new LogIn(name,bs.DBSVR);
		Run r = new Run(login,bs.DBSVR);
		try {
			r.process();
		} finally {
			r.DBSVR.commitDB();
			ConnectTionManager.close();
		}
		

	}

	private void initDb() throws Exception {
		DBSVR = new DbServiceImpl();
		DBSVR.intiDB();
		
		//������ݿ������ԣ����ȱ�����ؽ����ݽṹ������ʼ������		
		//�����־û�����ֱ���������
	}

}
