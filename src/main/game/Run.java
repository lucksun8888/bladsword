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

		SendMessage.sendMessage("位置:" + LOGIN.getLoginfo().getValue("city_name") + "  " + "时间:"
				+ LOGIN.getLoginfo().getValue("nowtime") + "\n");

		while (true) {
			// 打印场景和出城菜单
			Ground bd = new Building(LOGIN.getLoginfo().getValue("city_id"), DBSVR);
			Rslist rl = bd.getGround();
			HashMap<Integer, String> bdmap = Gui.printMenu(rl, "build_name", "build_id");
			SendMessage.sendMessage("x:出城\n");
			SendMessage.sendMessage("m:菜单\n" + ":");
			
			Scanner sc = new Scanner(System.in);
			String name = sc.next();

			if (!"x".equals(name)&&!"m".equals(name)) {
				// 进入建筑处理
				while (true) {
					// 查询事件：随机+任务事件
					Play ev = new Play(LOGIN,DBSVR);
					Rslist evrl = ev.getEvList(bdmap.get(Integer.decode(name)),
							(Date) LOGIN.getLoginfo().getValue("nowtime"));
					// 特殊事件
//					ev.processSpecail(bdmap.get(Integer.decode(name)), (Date) LOGIN.getLoginfo()
//							.getValue("nowtime"));

					// 遍历随机事件
					HashMap<Integer, String> evmap = Gui.printMenu(evrl, "event_name", "event_id");
					SendMessage.sendMessage("x:离开\n" );
					SendMessage.sendMessage("m:菜单\n" + ":");
					sc = new Scanner(System.in);
					String name1 = sc.next();

					if ("x".equals(name1)) {
						break;
					}if("m".equals(name1)){

						Menu menu = new Menu(DBSVR, LOGIN);
						menu.printGameMenu();
					
					}
					else {
						// 执行事件
						ev.process(evmap.get(Integer.decode(name1)));
					}
				}
			} else if("m".equals(name)){

				Menu menu = new Menu(DBSVR, LOGIN);
				menu.printGameMenu();
			
			}
			else{

				// 出城处理
				Ground ct = new City(null, DBSVR);
				Rslist ctrl = ct.getGround();

				// 打出城菜单
				HashMap<Integer, String> citymap = Gui.printMenu(ctrl, "city_name", "city_id");	
				SendMessage.sendMessage("m:菜单\n");
				SendMessage.sendMessage(":");
				// 更新城市并跳出操作
				sc = new Scanner(System.in);
				String index = sc.next();
				if("m".equals(index)){
					Menu menu = new Menu(DBSVR, LOGIN);
					menu.printGameMenu();
				}
				
				String indexstr = citymap.get(Integer.decode(index));
				// 城市跳跃需要按照坐标计算，规则为直线距离30，则耗时1/4天。时间只保存为xxxx-xx-xx 3:00/9:00
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
