package com.ground.impl;

import com.dbservice.impl.DbServiceImpl;
import com.ground.Ground;
import com.result.impl.Rslist;

public class Building implements Ground {
	String NAME;
	String TYPE;
	DbServiceImpl DB;
	
	@Override
	public Rslist getGround() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from building where city_id = '" + NAME + "'");
		Rslist rl = DB.excuteQuery(sql.toString());
		return rl;
	}

	public Building(Object value, DbServiceImpl dBSVR) {
		this.NAME = (String) value;
		this.DB = dBSVR;
	}

	@Override
	public String getGroundType() {
		TYPE = "building";
		return TYPE;
	}
	@Override
	public String getName() {
		return NAME;
	}

}
