package com.player.impl;

import com.dbservice.impl.DbServiceImpl;
import com.message.SendMessage;
import com.param.Opitons;
import com.result.impl.Rs;
import com.result.impl.Rslist;

public class Item {

	DbServiceImpl DBS;
	Role ROLE;
	Rslist RL; // ���е���

	/**
	 * @throws Exception
	 * 
	 */
	public Item(DbServiceImpl dbs, Role role) throws Exception {
		this.DBS = dbs;
		this.ROLE = role;
		loadAllItem(role.getRoleid());
	}

	/**
	 * װ�ص����嵥
	 * 
	 * @param role_id
	 * @throws Exception
	 */
	public void loadAllItem(String role_id) throws Exception {
		String sql = "select * from item where role_id ='" + role_id + "'";
		RL = DBS.excuteQuery(sql);
	}

	/**
	 * 
	 * @param pitem_id
	 * @return
	 * @throws Exception
	 */
	public Rs getItem(String pitem_id) throws Exception {
		Rs rs = null;
		for (int i = 0; i < RL.getLength(); i++) {
			if (pitem_id.equals(RL.get(i).getValue("pitem_id"))) {
				rs = RL.get(i);
			}
		}
		return rs;
	}

	/**
	 * �������е���
	 * 
	 * @return
	 */
	public Rslist getAllRl() {
		return RL;
	}

	/**
	 * ����ָ�����͵���
	 * 
	 * @param pitem_type
	 * @return
	 */
	public Rslist getRlByType(String pitem_type) {
		Rslist rl = new Rslist();
		for (int i = 0; i < RL.getLength(); i++) {
			if ("pitem_type".equals(RL.get(i).getValue("pitem_type"))) {
				rl.add(RL.get(i));
			}
		}
		return rl;
	}

	/**
	 * ����ָ���������
	 * 
	 * @param pitem_kind
	 * @return
	 */
	public Rslist getRlByKind(String pitem_kind) {
		Rslist rl = new Rslist();
		for (int i = 0; i < RL.getLength(); i++) {
			if ("pitem_kind".equals(RL.get(i).getValue("pitem_kind"))) {
				rl.add(RL.get(i));
			}
		}
		return rl;
	}

	/**
	 * ��ȡ����,����Ѿ�ӵ�У����һ����������һ��
	 * 
	 * @param pitem_id
	 * @param count
	 * @param role
	 * @throws Exception
	 */
	public void copyItem(String pitem_id, int count, Role role) throws Exception {
		String sql = "";
		if (getItem(pitem_id) != null) {
			sql = "update item set item_count = item_count + " + count + " where pitem_id ='" + pitem_id +"' and role_id ='"+ role.getRoleid() +"'";
		} else {
			sql = "insert into item (role_id,item_count,pitem_id,pitem_name,pitem_type,pitem_kind,hp_up,hp_up_p,"
					+ "state_r,exp_up,exp_up_p,att_up,att_up_p,def_up,def_up_p,money, note) " + "select '"
					+ role.getRoleid() + "' as role_id," + count
					+ " as item_count,  pitem_id,  pitem_name,  pitem_type,  " + "pitem_kind,hp_up,hp_up_p,state_r,"
					+ "exp_up,exp_up_p,att_up,att_up_p,def_up,def_up_p,money,note from pitem where pitem_id = '"
					+ pitem_id + "'";
		}

		DBS.excuteSql(sql);
		SendMessage.sendMessage("��õ��ߣ�"+getItem(pitem_id).getValue("pitem_name")+";������"+ count);
	}

	/**
	 * ���ٵ��ߣ���������һ������ɾ��������¼
	 * 
	 * @param pitem_id
	 * @param count
	 * @param role
	 * @throws Exception
	 */
	public void desItem(String pitem_id, int count, Role role) throws Exception {
		String sql = "";
		if (getItem(pitem_id) != null && (Integer) getItem(pitem_id).getValue("item_count") > count) {
			sql = "update item set item_count = item_count - " + count + " where pitem_id ='" + pitem_id +"' and role_id ='"+ role.getRoleid() +"'";
		} else {
			sql = "delete * from item where role_id = '" + role.getRoleid() + "' and pitem_id = '" + pitem_id + "'";
		}
		DBS.excuteSql(sql);
	}

	/**
	 * ʹ�õ���
	 * 
	 * @param pitem_id
	 * @throws Exception
	 */
	public void use(String pitem_id) throws Exception {
		Rs item = getItem(pitem_id);
		if (Opitons.PITEM_TYPE_0.equals(item.getValue("pitem_type"))) {

		} else if (Opitons.PITEM_TYPE_1.equals(item.getValue("pitem_type"))) {

		} else if (Opitons.PITEM_TYPE_2.equals(item.getValue("pitem_type"))) {
			SendMessage.sendMessage("װ���޷��ڴ�ʹ�ã�");
		} else if (Opitons.PITEM_TYPE_3.equals(item.getValue("pitem_type"))) {
			// ѧϰ����
			Skill skill = new Skill(ROLE.getRoleid(), DBS);
			skill.LoadSkill();
			
			
			
		} else if (Opitons.PITEM_TYPE_4.equals(item.getValue("pitem_type"))) {

		}
	}
}
