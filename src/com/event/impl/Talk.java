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
		// 默认从num为1的行初始
		this.DBSVR = dBSVR;
		this.LOGIN = login;
		getMessage(talk_id, "1");
	}

	/**
	 * 递归做分支工作
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
			// 读取脚本进行对话
			Rslist rl = DBSVR.queryDBs("ptalk", args);
			
			// 如果有事件，则进行触发,允许多个事件，按顺序触发
			if (rl.get(0).getValue("event_id") != null && !"".equals(rl.get(0).getValue("event_id"))) {
				String[] event_ids = ((String)rl.get(0).getValue("event_id")).split(",");
				Play e = new Play(LOGIN,DBSVR);
				for(int i=0;i<event_ids.length;i++){
					e.process(event_ids[i]);
				}
			}
			
			// 展示对白并获取选择项，根据选择项进入分支
			System.out.println(rl.get(0).getValue("text"));

			// 叶子节点跳出递归，不再遍历其他节点
			if (rl.get(0)!=null&&(rl.get(0).getValue("next_num") == null)||"".equals(rl.get(0).getValue("next_num"))) {
				flag = false;
				break;
			}			
			
			// 打印所有可选结果
			String[] nextnum = ((String)rl.get(0).getValue("next_num")).split(",");
			StringBuffer querysql = new StringBuffer(
					"select * from ptalk where talk_id = ").append(
							rl.get(0).getValue("talk_id")).append(" and ( 0 = 1 ");
			for (int n = 0; n < nextnum.length; n++) {
				querysql.append(" or ").append("talk_num ='").append(nextnum[n]).append("'");
			}
			querysql.append(")");
			Rslist rs0 = DBSVR.excuteQuery(querysql.toString());		

			// 读取脚本进行对话
			Rslist rs1;
			// 结果放在map中提供映射选项
			Map<Integer,String> ansm = new HashMap<Integer,String>();
			for(int k =0;k<rs0.getLength();k++)
			{
				// 根据下一跳查询可选结果并打印
				String talk_num = nextnum[k];
				args = new HashMap();
				args.put("talk_id", id);
				args.put("talk_num", talk_num);
				// 读取脚本进行对话
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
