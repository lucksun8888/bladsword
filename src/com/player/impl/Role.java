package com.player.impl;


import com.dbservice.impl.DbServiceImpl;
import com.result.impl.Rslist;

/**
 * װ�ؽ�ɫ���ԣ���ɫ���Ա��
 * @author dev
 *
 */
public class Role {
	DbServiceImpl DBSVR;
	String STAT; //״̬ 0-��ͨ 1-���� 2-�ж� 8-���� 9-���� 
	String INTELLIGENCE; // ����
	String ROLEID;
	
	int HP; // ����
	int EXP; // ����
	String NAME; // ��ɫ����
	Skill SK; // �书
	String NOTE;// ����
	
	
	public Role(DbServiceImpl DBSVR){
		this.DBSVR = DBSVR;
	}
	
	public void createRole() throws Exception{
		String sql = "";
		DBSVR.excuteSql(sql);
	}
	
	public void loadRole(String role_id) throws Exception{
		String sql = "select * from role where role_id ='" + role_id +"'";
		Rslist rl = DBSVR.excuteQuery(sql);
		ROLEID = role_id;
		HP = (Integer) rl.get(0).getValue("hp");
		EXP = (Integer) rl.get(0).getValue("exp");
		STAT = (String) rl.get(0).getValue("role_stat");
		INTELLIGENCE = (String) rl.get(0).getValue("intelligence");
		NAME = (String) rl.get(0).getValue("role_name");	
		NOTE = (String) rl.get(0).getValue("note");
		SK = new Skill(role_id,DBSVR);
		SK.LoadSkill();
		
		
	}
	
	void update() throws Exception{
		String sql = "";
		DBSVR.excuteSql(sql);		
	}
	

	public String getname() {
		return NAME;
	}

	public int gethp() {
		return HP;
	}

	public String getstat() {
		return STAT;
	}

	public String getintel() {
		return INTELLIGENCE;
	}

	public int getexp() {
		return EXP;
	}
	
	
	public void sethpup(int i) {
		HP = HP - i;
	}

	
	public void sethpdown(int i) {
		HP = HP - i;
	}
	
	
	public void setexpup(int i) {
		EXP = EXP + i;
	}

	public void setexpdown(int i) {
		EXP = EXP + i;
	}

	public void updatestat(String i) {
		STAT = i;
	}
	
	public void updateintel(String i) {
		INTELLIGENCE = i;
	}
	
	public Skill getsk(){
		return SK;
	}

	public String getnote() {
		return NOTE;
	}


	public void update(int expup, int hpup, String getstat, String getintel) throws Exception {
		String sql = "update role set exp = exp + " + expup + ",hp = hp+" + hpup + ",role_stat = '" + getstat
				+ "',intelligence ='" + getintel + "' where role_id ='" + ROLEID + "'";
		DBSVR.excuteSql(sql);
	}
	
	public String getRoleid(){
		return ROLEID;
	}

}
