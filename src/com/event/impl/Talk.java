package com.event.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.dbservice.impl.DbServiceImpl;
import com.event.GameEvent;
import com.result.impl.Rslist;

import main.game.LogIn;


public class Talk implements GameEvent{
	private boolean flag = true;
	private DbServiceImpl DBSVR;
	private LogIn LOGIN;
	
	public void process(LogIn login,String talk_id, DbServiceImpl dBSVR) throws Exception {
		// Ĭ�ϴ�numΪ1���г�ʼ
		this.DBSVR = dBSVR;
		this.LOGIN = login;
		getMessage(talk_id, "1");
	}

	/**
	 * �ݹ�����֧����
	 * @param string
	 * @param string
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void getMessage(String id, String num) throws Exception {

		while (flag) {
			Map args = new HashMap<String,String>();
			args.put("talk_id", id);
			args.put("talk_num", num);
			// ��ȡ�ű����жԻ�
			Rslist rl = DBSVR.queryDBs("ptalk", args);
			
			// ������¼�������д���,�������¼�����˳�򴥷�
			if (rl.get(0).getValue("event_id") != null && !"".equals(rl.get(0).getValue("event_id"))) {
				String[] event_ids = ((String)rl.get(0).getValue("event_id")).split(",");
				Play e = new Play(LOGIN,DBSVR);
				for(int i=0;i<event_ids.length;i++){
					e.process(event_ids[i]);
				}
			}
			
			// չʾ�԰ײ���ȡѡ�������ѡ��������֧
			System.out.println(rl.get(0).getValue("text"));

			// Ҷ�ӽڵ������ݹ飬���ٱ��������ڵ�
			if (rl.get(0)!=null&&(rl.get(0).getValue("next_num") == null)||"".equals(rl.get(0).getValue("next_num"))) {
				flag = false;
				break;
			}			
			
			// ��ӡ���п�ѡ���
			String[] nextnum = ((String)rl.get(0).getValue("next_num")).split(",");
			StringBuffer querysql = new StringBuffer(
					"select * from ptalk where talk_id = ").append(
							rl.get(0).getValue("talk_id")).append(" and ( 0 = 1 ");
			for (int n = 0; n < nextnum.length; n++) {
				querysql.append(" or ").append("talk_num ='").append(nextnum[n]).append("'");
			}
			querysql.append(")");
			Rslist rs0 = DBSVR.excuteQuery(querysql.toString());		

			// ��ȡ�ű����жԻ�
			Rslist rs1;
			// �������map���ṩӳ��ѡ��
			Map<Integer,String> ansm = new HashMap<Integer,String>();
			for(int k =0;k<rs0.getLength();k++)
			{
				// ������һ����ѯ��ѡ�������ӡ
				String talk_num = nextnum[k];
				args = new HashMap();
				args.put("talk_id", id);
				args.put("talk_num", talk_num);
				// ��ȡ�ű����жԻ�
				rs1 = DBSVR.queryDBs("ptalk", args);
				System.out.println(k + ":" + rs1.get(0).getValue("ans"));
				ansm.put(k, (String) rs1.get(0).getValue("talk_num"));
			}
			System.out.print(":");

			Scanner sc = new Scanner(System.in);
			int name0 = sc.nextInt();		

			getMessage(id, ansm.get(name0));
		}
	}

	@Override
	public String process() {
		return null;
		// TODO Auto-generated method stub
		
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
