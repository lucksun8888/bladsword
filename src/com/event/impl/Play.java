package com.event.impl;

import java.util.Date;

import com.dbservice.impl.DbServiceImpl;
import com.event.GameEvent;
import com.param.Opitons;
import com.player.impl.Item;
import com.player.impl.Role;
import com.result.impl.Rs;
import com.result.impl.Rslist;


import main.game.LogIn;
import main.task.impl.TaskImpl;




public class Play implements GameEvent{
	
	LogIn LOGIN;
	DbServiceImpl DBS;
	/**
	 * 根据id查询事件并执行事件
	 * @param event_id
	 * @throws Exception 
	 */
	public void process(String event_id) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from event where event_id ='" + event_id + "'" );		
		Rslist rl = DBS.excuteQuery(sql.toString()); 
		Rs rs = rl.get(0);
		if(Opitons.EVENT_TYPE_TALK.equals((String)rs.getValue("event_type"))){
			Talk tk = new Talk();
			tk.process(LOGIN,(String) rs.getValue("event_id_"), DBS);
		}else if(Opitons.EVENT_TYPE_FIGHT.equals((String)rs.getValue("event_type"))){
			
			String role_id = (String) rs.getValue("event_sql");
			Role role = new Role(DBS);
			role.loadRole(role_id);
			Role role_user = new Role(DBS);
			role_user.loadRole((String) LOGIN.getLoginfo().getValue("role_id"));		
			Fight ft = new Fight(DBS,role_user,role);
			ft.process();
		}else if(Opitons.EVENT_TYPE_GETITEM.equals((String)rs.getValue("event_type"))){
			String pitem_id =  ((String) rs.getValue("event_sql")).split(",")[0];
			Role role_user = new Role(DBS);
			role_user.loadRole((String) LOGIN.getLoginfo().getValue("role_id"));			
			Item item = new Item(DBS, role_user);
			item.copyItem(pitem_id, Integer.decode(((String) rs.getValue("event_sql")).split(",")[1]), role_user);
		}
		
		// 检查是任务事务则更新任务
		if(checkTask(event_id,rs)){
			TaskImpl task = new TaskImpl(LOGIN,DBS);
			task.loadTask();
			task.update(event_id,Opitons.TASK_FLAG_1);
		}
	}

	private boolean checkTask(String event_id, Rs rs) {
		// TODO Auto-generated method stub
		return (Opitons.EVENT_TYPE_TASK.equals((String)rs.getValue("event_type")));
	}

	/**
	 * 特殊事件，用于实现任务系统
	 * @param build_id
	 * @param date
	 * @throws Exception 
	 */
	public void processSpecail(String build_id, Date date) throws Exception {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		StringBuffer sql = new StringBuffer();
		sql.append("select event_id from event where build_ids like '%" + build_id 
				+ "%' and (nvl(nowtimes,date '9999-9-9') = date '9999-9-9' or nowtimes ='" + sqlDate +"')" );		
		Rslist rl = DBS.excuteQuery(sql.toString()); 
		if(rl!=null&&rl.getLength()>0){
			// 遍历事件依次处理
			for(int i=0;i<rl.getLength();i++){
				process((String) rl.get(i).getValue("event_id"));
			}
		}
	}
	
	
	public Play(LogIn login, DbServiceImpl dbs) {
		this.DBS = dbs;
		this.LOGIN = login;
		
	}
	
	public Play(String string, DbServiceImpl DBSVR) {
		
	}

	void fight(){
		
	}
	
	void talk(){
		
	}
	
	void Update(){
		
	}

	/**
	 * 获取随机事件清单
	 * @return
	 * @throws Exception 
	 */
	public Rslist getEvList(String build_id,Date nowtime) throws Exception {
		java.sql.Date sqlDate = new java.sql.Date(nowtime.getTime());
		StringBuffer sql = new StringBuffer();
		sql.append("select * from event where event_type = '1' and build_ids like '%" + build_id + "%' and (nvl(nowtimes,date '9999-9-9') = date '9999-9-9' or nowtimes ='" + sqlDate +"')" );	
		Rslist rl = DBS.excuteQuery(sql.toString()); 
		return rl;
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
