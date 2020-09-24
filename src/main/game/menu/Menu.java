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
			SendMessage.sendMessage("�˵�\n" + "1:״̬\n" + "2:����\n" + "3:����\n" + "4:װ��\n" + "x:�뿪\n" + ":");
			Scanner sc = new Scanner(System.in);
			String name = sc.next();
			if ("x".equals(name)) {
				break;
			} else if ("1".equals(name)) {
				// ��ӡ�Լ���Ϣ
				SendMessage.sendMessage("������" + ROLE.getname() + "\n" + "������" + ROLE.getnote() + "\n" + "������" + ROLE.getexp()
						+ "	������" + ROLE.gethp() + "		״̬" + ROLE.getstat() + "\n" + "��ͨ��ѧ��" + ROLE.getsk().getskindex()
						+ "\n");
			} else if ("2".equals(name)) {
				TaskImpl task = new TaskImpl(LOGIN, DBS);
				task.loadTask();
				// ����˵���ӡ�����б�
				while (true) {
					HashMap<Integer, String> map = Gui.printMenu(task.getMainRl(), "task_name", "task_id");
					SendMessage.sendMessage("x:�뿪\n" + ":");
					sc = new Scanner(System.in);
					name = sc.next();
					if ("x".equals(name)) {
						break;
					} else {
						// ��ӡ�Ӳ˵��鿴��������
						Gui.printMenu(task.getJoinRl(map.get(Integer.decode(name))), "task_note", "task_num");
						SendMessage.sendMessage("x:�뿪\n" + ":");
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
					SendMessage.sendMessage("x:�뿪\n" + ":");
					sc = new Scanner(System.in);
					name = sc.next();
					if ("x".equals(name)) {
						break;
					} else {
						// ��ӡ����˵���������ʹ������߿��Ե��ʹ��
						System.out.println(item.getItem(map.get(Integer.decode(name))).getValue("note"));
						SendMessage.sendMessage("y:ʹ��\n");
						SendMessage.sendMessage("n:ȡ��\n" + ":");
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
