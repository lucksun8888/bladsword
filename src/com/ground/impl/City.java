package com.ground.impl;

import com.dbservice.impl.DbServiceImpl;
import com.ground.Ground;
import com.result.impl.Rslist;

public class City implements Ground {
	DbServiceImpl DBSVR;
	String NAME;
	String TYPE;
	public City(Object value, DbServiceImpl dBSVR) {
		this.DBSVR = dBSVR;
	}

	@Override
	public Rslist getGround() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from city ");
		Rslist rl = DBSVR.excuteQuery(sql.toString());
		return rl;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}

	@Override
	public String getGroundType() {
		TYPE="city";
		return TYPE;
	}

}
