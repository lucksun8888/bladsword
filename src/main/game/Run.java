package main.game;


import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import com.dbservice.impl.DbServiceImpl;
import com.event.impl.Play;
import com.ground.Ground;
import com.ground.impl.Building;
import com.ground.impl.City;
import com.gui.Gui;
import com.message.SendMessage;
import com.result.impl.Rslist;

import main.game.menu.Menu;


public class Run {
	LogIn LOGIN;
	DbServiceImpl DBSVR;

	Run(LogIn login, DbServiceImpl dBSVR) {
		this.LOGIN = login;
		this.DBSVR = dBSVR;
	}

	void refresh() {

	}

	public void process() throws Exception {

		SendMessage.sendMessage("λ��:" + LOGIN.getLoginfo().getValue("city_name") + "  " + "ʱ��:"
				+ LOGIN.getLoginfo().getValue("nowtime") + "\n");

		while (true) {
			// ��ӡ�����ͳ��ǲ˵�
			Ground bd = new Building(LOGIN.getLoginfo().getValue("city_id"), DBSVR);
			Rslist rl = bd.getGround();
			HashMap<Integer, String> bdmap = Gui.printMenu(rl, "build_name", "build_id");
			SendMessage.sendMessage("x:����\n");
			SendMessage.sendMessage("m:�˵�\n" + ":");
			
			Scanner sc = new Scanner(System.in);
			String name = sc.next();

			if (!"x".equals(name)&&!"m".equals(name)) {
				// ���뽨������
				while (true) {
					// ��ѯ�¼������+�����¼�
					Play ev = new Play(LOGIN,DBSVR);
					Rslist evrl = ev.getEvList(bdmap.get(Integer.decode(name)),
							(Date) LOGIN.getLoginfo().getValue("nowtime"));
					// �����¼�
//					ev.processSpecail(bdmap.get(Integer.decode(name)), (Date) LOGIN.getLoginfo()
//							.getValue("nowtime"));

					// ��������¼�
					HashMap<Integer, String> evmap = Gui.printMenu(evrl, "event_name", "event_id");
					SendMessage.sendMessage("x:�뿪\n" );
					SendMessage.sendMessage("m:�˵�\n" + ":");
					sc = new Scanner(System.in);
					String name1 = sc.next();

					if ("x".equals(name1)) {
						break;
					}if("m".equals(name1)){

						Menu menu = new Menu(DBSVR, LOGIN);
						menu.printGameMenu();
					
					}
					else {
						// ִ���¼�
						ev.process(evmap.get(Integer.decode(name1)));
					}
				}
			} else if("m".equals(name)){

				Menu menu = new Menu(DBSVR, LOGIN);
				menu.printGameMenu();
			
			}
			else{

				// ���Ǵ���
				Ground ct = new City(null, DBSVR);
				Rslist ctrl = ct.getGround();

				// ����ǲ˵�
				HashMap<Integer, String> citymap = Gui.printMenu(ctrl, "city_name", "city_id");	
				SendMessage.sendMessage("m:�˵�\n");
				SendMessage.sendMessage(":");
				// ���³��в���������
				sc = new Scanner(System.in);
				String index = sc.next();
				if("m".equals(index)){
					Menu menu = new Menu(DBSVR, LOGIN);
					menu.printGameMenu();
				}
				
				String indexstr = citymap.get(Integer.decode(index));
				// ������Ծ��Ҫ����������㣬����Ϊֱ�߾���30�����ʱ1/4�졣ʱ��ֻ����Ϊxxxx-xx-xx 3:00/9:00
				// /15:00 /21:00/
				Date nowtime = calcTime();
				LOGIN.updateLogIn(indexstr, nowtime);
				continue;

			}
		}
	}

	private Date calcTime() {
		Date d = (Date) LOGIN.getLoginfo().getValue("nowtime");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, c.get(Calendar.DATE) + 1);
		return (Date) c.getTime();
	}
}
