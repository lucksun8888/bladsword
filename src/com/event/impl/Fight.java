package com.event.impl;

import java.util.HashMap;
import java.util.Scanner;

import com.dbservice.impl.DbServiceImpl;
import com.event.GameEvent;
import com.message.SendMessage;
import com.param.Opitons;
import com.player.impl.Role;
import com.result.impl.Rs;
import com.result.impl.Rslist;


/**
 * 战斗采用设置技能顺序后自动对战，每次只能选出至多四种技能，技能可以重复使用
 * 
 * @author dev
 * 
 */
public class Fight implements GameEvent {
	DbServiceImpl DBS;
	Role ROLE_USER;
	Role ROLE;
	String ENDFLAG = "9";

	// 双方信息用于计算

	public Fight(DbServiceImpl dbs, Role role_user, Role role) {
		this.DBS = dbs;
		this.ROLE_USER = role_user;
		this.ROLE = role;
	}

	/**
	 * user为玩家角色
	 * 
	 * @param role_user
	 * @param role
	 * @return 
	 * @throws Exception 
	 */
	public String process() throws Exception {
		while (true) {
			// 打印自己信息
			SendMessage.sendMessage("姓名：" + ROLE_USER.getname() + "\n" + "描述："
					+ ROLE_USER.getnote() + "\n" + "功力：" + ROLE_USER.getexp()
					+ "	体力：" + ROLE_USER.gethp() + "		状态" + ROLE_USER.getstat()
					+ "\n" + "精通武学：" + ROLE_USER.getsk().getskindex() + "\n");

			// 打印敌人信息
			SendMessage.sendMessage("姓名：" + ROLE.getname() + "\n" + "描述："
					+ ROLE.getnote() + "\n" + "功力：" + ROLE.getexp() + "	体力："
					+ ROLE.gethp() + "		状态" + ROLE.getstat() + "\n" + "精通武学："
					+ ROLE.getsk().getskindex() + "\n");
			// 打印所有可用武学信息
			HashMap<Integer, String> m = new HashMap<Integer, String>();
			for (int i = 0; i < ROLE_USER.getsk().getskindex().size(); i++) {
				System.out.println(i + ":"
						+ ROLE_USER.getsk().getskindex().get(i));
				m.put(i, ROLE_USER.getsk().getskindex().get(i));
			}
			SendMessage.sendMessage("请选择四种武功，用','隔开武功,可重复\n" + ":");
			Scanner sc = new Scanner(System.in);
			String input = sc.next();
			String[] inputs = input.split(",");
			// 编排使用武功，最多四种
			Rslist skill_user_list = new Rslist();
			for (int i = 0; i < inputs.length; i++) {
				String skill_name = (String) m.get(Integer.decode(inputs[i]));
				skill_user_list.add(ROLE_USER.getsk().getskbyskillname(
						skill_name));
			}
			// npc初始化四种武功，暂时重复第一种武功
			Rslist skill_en_list = new Rslist();
			for (int i = 0; i < inputs.length; i++) {
				skill_en_list.add(ROLE.getsk().getskbyindex(1));
			}
			// 战斗，战斗根据功力强弱判断谁先出手，计算结果，记录武功使用情况用于升级武学或削减武学
			if (ROLE_USER.getexp() > ROLE.getexp()) {
				play(ROLE_USER, ROLE, skill_user_list);
				if ("1".equals(ENDFLAG)) {
					break;
				}
				play(ROLE, ROLE_USER, skill_en_list);
				if ("1".equals(ENDFLAG)) {
					break;
				}

			} else {
				play(ROLE, ROLE_USER, skill_en_list);
				if ("1".equals(ENDFLAG)) {
					break;
				}
				play(ROLE_USER, ROLE, skill_user_list);
				if ("1".equals(ENDFLAG)) {
					break;
				}
			}
		}
		// 打印战斗结果
		if (ROLE_USER.gethp() <= 0) {
			SendMessage.sendMessage("你输了。。。");
		} else {
			SendMessage.sendMessage("恭喜，战斗胜利！");
			// 如果胜利了，根据两者功力差距提升功力
			int expup = 0;
			int hpup = 0;
			if (ROLE_USER.getexp() > ROLE.getexp()) {
				double expd_user = ROLE_USER.getexp();
				double expd = ROLE.getexp();
				double expdupd = (expd_user - expd) * (expd / expd_user); // (功力/100
																			// +
																			// 1)*武功伤害作为伤害值，将来再进行优化
				double hpupd = (expd_user - expd) * (expd / expd_user); // (功力/100
																		// +
																		// 1)*武功伤害作为伤害值，将来再进行优化
				hpup = Integer.parseInt(new java.text.DecimalFormat("0")
						.format(hpupd));
				expup = Integer.parseInt(new java.text.DecimalFormat("0")
						.format(expdupd));
			} else {
				double expd_user = ROLE_USER.getexp();
				double expd = ROLE.getexp();
				double expdupd = (expd - expd_user) * (expd_user / expd); // (功力/100
																			// +
																			// 1)*武功伤害作为伤害值，将来再进行优化
				double hpupd = (expd_user - expd) * (expd / expd_user); // (功力/100
																		// +
																		// 1)*武功伤害作为伤害值，将来再进行优化
				hpup = Integer.parseInt(new java.text.DecimalFormat("0")
						.format(hpupd));
				expup = Integer.parseInt(new java.text.DecimalFormat("0")
						.format(expdupd));
			}
			ROLE_USER.update(expup, hpup, ROLE_USER.getstat(),
					ROLE_USER.getintel());

		}

		// 更新数据库完成战斗

		// 更新role
		// ROLE_USER.update(ROLE_USER);
		// 更新skill
		// ROLE_USER.getsk().updateskill(ROLE_USER.getsk().getsk());
		if (ROLE_USER.gethp() <= 0) {
			return "win";
		} else {
			return "lose";
		}

	}

	/**
	 * 
	 * @param role_jg
	 * @param role
	 * @param skill
	 */
	private void play(Role role_jg, Role role, Rslist skill) {
		// 循环读取技能，将技能事件依次执行
		for (int i = 0; i < skill.getLength(); i++) {
			SendMessage.sendMessage(role_jg.getname() + "使用技能："
					+ skill.get(i).getValue("skill_name"));
			// 根据技能类型进行不同的操作
			Rs sk = skill.get(i);
			if (Opitons.SKILL_TYPE_GJ.equals(skill.get(i)
					.getValue("skill_type"))) {
				double attd = (Integer) sk.getValue("att");
				double expd = role_jg.getexp();
				double att_ = attd * (expd / 100 + 1); // (功力/100 +
														// 1)*武功伤害作为伤害值，将来再进行优化
				int hpdown = Integer.parseInt(new java.text.DecimalFormat("0")
						.format(att_));
				SendMessage.sendMessage(role.getname() + "受到伤害：" + hpdown);
				role.sethpdown(hpdown);

				// 血量小于0时候战斗结束
				if (role.gethp() <= 0) {
					ENDFLAG = "1";
					break;
				}
			}
		}
	}

	@Override
	public String init() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String end() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
