package com.player.impl;

import com.dbservice.impl.DbServiceImpl;
import com.param.Opitons;

/**
 * 装备的安装和解除
 * 
 * @author dev
 * 
 */
public class Weapon {
	DbServiceImpl DBS;
	Role ROLE;

	public Weapon(DbServiceImpl dbs, Role role) {
		this.DBS = dbs;
		this.ROLE = role;
	}

	public void weapon(String weapon_id, String pitem_kind) throws Exception {
		if (pitem_kind.equals(Opitons.PITEM_KIND_5) || pitem_kind.equals(Opitons.PITEM_KIND_6)) {
			String sql = "update role set weapon_att = '" + weapon_id + "' where role_id ='"+ ROLE.getRoleid() +"'";
			DBS.excuteSql(sql);
		} else if (pitem_kind.equals(Opitons.PITEM_KIND_7) || pitem_kind.equals(Opitons.PITEM_KIND_8)) {
			String sql = "update role set weapon_def = '" + weapon_id + "' where role_id ='"+ ROLE.getRoleid() +"'";
			DBS.excuteSql(sql);
		}
	}

	public void weapdown(String weapon_id, String pitem_kind) throws Exception {
		if (pitem_kind.equals(Opitons.PITEM_KIND_5) || pitem_kind.equals(Opitons.PITEM_KIND_6)) {
			String sql = "update role set weapon_att = '' where role_id ='"+ ROLE.getRoleid() +"'";
			DBS.excuteSql(sql);
		} else if (pitem_kind.equals(Opitons.PITEM_KIND_7) || pitem_kind.equals(Opitons.PITEM_KIND_8)) {
			String sql = "update role set weapon_def = '' where role_id ='"+ ROLE.getRoleid() +"'";
			DBS.excuteSql(sql);
		}
	}
}
