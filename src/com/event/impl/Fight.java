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
 * ս���������ü���˳����Զ���ս��ÿ��ֻ��ѡ���������ּ��ܣ����ܿ����ظ�ʹ��
 * 
 * @author dev
 * 
 */
public class Fight implements GameEvent {
	DbServiceImpl DBS;
	Role ROLE_USER;
	Role ROLE;
	String ENDFLAG = "9";

	// ˫����Ϣ���ڼ���

	public Fight(DbServiceImpl dbs, Role role_user, Role role) {
		this.DBS = dbs;
		this.ROLE_USER = role_user;
		this.ROLE = role;
	}

	/**
	 * userΪ��ҽ�ɫ
	 * 
	 * @param role_user
	 * @param role
	 * @return 
	 * @throws Exception 
	 */
	public String process() throws Exception {
		while (true) {
			// ��ӡ�Լ���Ϣ
			SendMessage.sendMessage("������" + ROLE_USER.getname() + "\n" + "������"
					+ ROLE_USER.getnote() + "\n" + "������" + ROLE_USER.getexp()
					+ "	������" + ROLE_USER.gethp() + "		״̬" + ROLE_USER.getstat()
					+ "\n" + "��ͨ��ѧ��" + ROLE_USER.getsk().getskindex() + "\n");

			// ��ӡ������Ϣ
			SendMessage.sendMessage("������" + ROLE.getname() + "\n" + "������"
					+ ROLE.getnote() + "\n" + "������" + ROLE.getexp() + "	������"
					+ ROLE.gethp() + "		״̬" + ROLE.getstat() + "\n" + "��ͨ��ѧ��"
					+ ROLE.getsk().getskindex() + "\n");
			// ��ӡ���п�����ѧ��Ϣ
			HashMap<Integer, String> m = new HashMap<Integer, String>();
			for (int i = 0; i < ROLE_USER.getsk().getskindex().size(); i++) {
				System.out.println(i + ":"
						+ ROLE_USER.getsk().getskindex().get(i));
				m.put(i, ROLE_USER.getsk().getskindex().get(i));
			}
			SendMessage.sendMessage("��ѡ�������书����','�����书,���ظ�\n" + ":");
			Scanner sc = new Scanner(System.in);
			String input = sc.next();
			String[] inputs = input.split(",");
			// ����ʹ���书���������
			Rslist skill_user_list = new Rslist();
			for (int i = 0; i < inputs.length; i++) {
				String skill_name = (String) m.get(Integer.decode(inputs[i]));
				skill_user_list.add(ROLE_USER.getsk().getskbyskillname(
						skill_name));
			}
			// npc��ʼ�������书����ʱ�ظ���һ���书
			Rslist skill_en_list = new Rslist();
			for (int i = 0; i < inputs.length; i++) {
				skill_en_list.add(ROLE.getsk().getskbyindex(1));
			}
			// ս����ս�����ݹ���ǿ���ж�˭�ȳ��֣�����������¼�书ʹ���������������ѧ��������ѧ
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
		// ��ӡս�����
		if (ROLE_USER.gethp() <= 0) {
			SendMessage.sendMessage("�����ˡ�����");
		} else {
			SendMessage.sendMessage("��ϲ��ս��ʤ����");
			// ���ʤ���ˣ��������߹��������������
			int expup = 0;
			int hpup = 0;
			if (ROLE_USER.getexp() > ROLE.getexp()) {
				double expd_user = ROLE_USER.getexp();
				double expd = ROLE.getexp();
				double expdupd = (expd_user - expd) * (expd / expd_user); // (����/100
																			// +
																			// 1)*�书�˺���Ϊ�˺�ֵ�������ٽ����Ż�
				double hpupd = (expd_user - expd) * (expd / expd_user); // (����/100
																		// +
																		// 1)*�书�˺���Ϊ�˺�ֵ�������ٽ����Ż�
				hpup = Integer.parseInt(new java.text.DecimalFormat("0")
						.format(hpupd));
				expup = Integer.parseInt(new java.text.DecimalFormat("0")
						.format(expdupd));
			} else {
				double expd_user = ROLE_USER.getexp();
				double expd = ROLE.getexp();
				double expdupd = (expd - expd_user) * (expd_user / expd); // (����/100
																			// +
																			// 1)*�书�˺���Ϊ�˺�ֵ�������ٽ����Ż�
				double hpupd = (expd_user - expd) * (expd / expd_user); // (����/100
																		// +
																		// 1)*�书�˺���Ϊ�˺�ֵ�������ٽ����Ż�
				hpup = Integer.parseInt(new java.text.DecimalFormat("0")
						.format(hpupd));
				expup = Integer.parseInt(new java.text.DecimalFormat("0")
						.format(expdupd));
			}
			ROLE_USER.update(expup, hpup, ROLE_USER.getstat(),
					ROLE_USER.getintel());

		}

		// �������ݿ����ս��

		// ����role
		// ROLE_USER.update(ROLE_USER);
		// ����skill
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
		// ѭ����ȡ���ܣ��������¼�����ִ��
		for (int i = 0; i < skill.getLength(); i++) {
			SendMessage.sendMessage(role_jg.getname() + "ʹ�ü��ܣ�"
					+ skill.get(i).getValue("skill_name"));
			// ���ݼ������ͽ��в�ͬ�Ĳ���
			Rs sk = skill.get(i);
			if (Opitons.SKILL_TYPE_GJ.equals(skill.get(i)
					.getValue("skill_type"))) {
				double attd = (Integer) sk.getValue("att");
				double expd = role_jg.getexp();
				double att_ = attd * (expd / 100 + 1); // (����/100 +
														// 1)*�书�˺���Ϊ�˺�ֵ�������ٽ����Ż�
				int hpdown = Integer.parseInt(new java.text.DecimalFormat("0")
						.format(att_));
				SendMessage.sendMessage(role.getname() + "�ܵ��˺���" + hpdown);
				role.sethpdown(hpdown);

				// Ѫ��С��0ʱ��ս������
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
