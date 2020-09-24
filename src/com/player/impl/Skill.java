package com.player.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.dbservice.impl.DbServiceImpl;
import com.result.impl.Rs;
import com.result.impl.Rslist;


public class Skill {
	Rslist rl;
	DbServiceImpl DBS;
	String ROLE_ID;
	HashMap<String, Rs> SKILLMAPID;
	HashMap<String, Rs> SKILLMAPNAME;
	ArrayList<String> SKILLNAMEAL;

	public Skill(String role_id, DbServiceImpl dbs) throws Exception {
		this.ROLE_ID = role_id;
		this.DBS = dbs;

	}

	public void LoadSkill() throws Exception {
		String sql = "select * from skill where role_id = '" + ROLE_ID + "'";
		rl = DBS.excuteQuery(sql);
		SKILLMAPID = new HashMap<String, Rs>();
		SKILLMAPNAME  = new HashMap<String, Rs>();
		SKILLNAMEAL = new ArrayList<String>();
		for (int i = 0; i < rl.getLength(); i++) {
			SKILLMAPID.put((String) rl.get(i).getValue("skill_id"), rl.get(i));
			SKILLMAPNAME.put((String) rl.get(i).getValue("skill_name"), rl.get(i));
			SKILLNAMEAL.add((String) rl.get(i).getValue("skill_name"));
		}

	}

	public Rslist getsk() {
		return rl;
	}

	public Rs getskbyindex(int i) {
		return rl.get(i);
	}
	
	public Rs getskbyskillid(String skill_id) {
		return SKILLMAPID.get(skill_id);
	}

	public Rs getskbyskillname(String skill_name) {
		return SKILLMAPNAME.get(skill_name);
	}
	
	public ArrayList<String> getskindex() {
		return SKILLNAMEAL;
	}

	public void updateskill(Rslist rslist) throws Exception {
		for (int i = 0; i < rslist.getLength(); i++) {
			Rs rs = rslist.get(i);
			String sql = "update skill set att=" + rs.getValue("att") + ",skill_flag= '" + rs.getValue("skill_flag")
					+ "' where skill_id = '" + rs.getValue("skill_id") + "'";
			DBS.excuteSql(sql);
		}
	}
	
	public void updateskill(Rs rs) throws Exception {
			String sql = "update skill set att=" + rs.getValue("att") + ",skillflag= '" + rs.getValue("skillflag")
					+ "' where skill_id = '" + rs.getValue("skill_id") + "'";
			DBS.excuteSql(sql);
	}
	
	/**
	 * 学习技能，将技能复制并增加到自己的列表中
	 * @param role
	 * @param skill_id
	 * @throws Exception 
	 */
	public void leanSkill(Role role,String skill_id) throws Exception{
		// 如果已经学会技能，则不进行学习
		if(!getskindex().contains(skill_id)){
			String sql = "insert into skill("+ role.getRoleid() 
			+", skill_id, skill_name, skill_type, att, note, skill_flag) select * from pskill where skill_id = '"+skill_id+"'";
			DBS.excuteSql(sql);
			LoadSkill();
			System.out.println("学会新技能："+ getskbyskillid(skill_id).getValue("skill_name"));
		}	
	}
}
