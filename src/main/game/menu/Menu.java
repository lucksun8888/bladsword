package main.game.menu;

import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Scanner;

import com.dbservice.impl.DbServiceImpl;
import com.gui.Gui;
import com.message.SendMessage;
import com.player.impl.Item;
import com.player.impl.Role;

import main.game.LogIn;
import main.task.impl.TaskImpl;

public class Menu {
	DbServiceImpl DBS;
	LogIn LOGIN;
	Role ROLE;

	public Menu(DbServiceImpl dbs, LogIn login) throws Exception {
		this.DBS = dbs;
		this.LOGIN = login;
		this.ROLE = getRole();
	}

	private Role getRole() throws Exception {
		Role role = new Role(DBS);
		role.loadRole((String) LOGIN.getLoginfo().getValue("role_id"));
		return role;
	}

	public void printGameMenu() throws Exception {
		while (true) {
			SendMessage.sendMessage("菜单\n" + "1:状态\n" + "2:任务\n" + "3:道具\n" + "4:装备\n" + "x:离开\n" + ":");
			Scanner sc = new Scanner(System.in);
			String name = sc.next();
			if ("x".equals(name)) {
				break;
			} else if ("1".equals(name)) {
				// 打印自己信息
				SendMessage.sendMessage("姓名：" + ROLE.getname() + "\n" + "描述：" + ROLE.getnote() + "\n" + "功力：" + ROLE.getexp()
						+ "	体力：" + ROLE.gethp() + "		状态" + ROLE.getstat() + "\n" + "精通武学：" + ROLE.getsk().getskindex()
						+ "\n");
			} else if ("2".equals(name)) {
				TaskImpl task = new TaskImpl(LOGIN, DBS);
				task.loadTask();
				// 两层菜单打印任务列表
				while (true) {
					HashMap<Integer, String> map = Gui.printMenu(task.getMainRl(), "task_name", "task_id");
					SendMessage.sendMessage("x:离开\n" + ":");
					sc = new Scanner(System.in);
					name = sc.next();
					if ("x".equals(name)) {
						break;
					} else {
						// 打印子菜单查看任务详情
						Gui.printMenu(task.getJoinRl(map.get(Integer.decode(name))), "task_note", "task_num");
						SendMessage.sendMessage("x:离开\n" + ":");
						sc = new Scanner(System.in);
						name = sc.next();
						if (true)
							break;
					}

				}
			} else if ("3".equals(name)) {
				Item item = new Item(DBS, ROLE);
				item.loadAllItem(ROLE.getRoleid());
				while(true){
					HashMap<Integer, String> map = Gui.printMenu(item.getAllRl(), "pitem_name", "pitem_id");
					SendMessage.sendMessage("x:离开\n" + ":");
					sc = new Scanner(System.in);
					name = sc.next();
					if ("x".equals(name)) {
						break;
					} else {
						// 打印道具说明，如果是使用类道具可以点击使用
						System.out.println(item.getItem(map.get(Integer.decode(name))).getValue("note"));
						SendMessage.sendMessage("y:使用\n");
						SendMessage.sendMessage("n:取消\n" + ":");
						sc = new Scanner(System.in);
						String n = sc.next();						
						if("y".equals(n)){
							item.use(map.get(Integer.decode(name)));
						}
					}
				}
			} else if ("4".equals(name)) {

			}

		}
	}
}
