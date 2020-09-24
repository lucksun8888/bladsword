package main.game;

import java.util.Date;

import com.dbservice.impl.DbServiceImpl;
import com.result.impl.Rs;
import com.result.impl.Rslist;


public class LogIn {

	String NAME;
	DbServiceImpl DB;

	Rs rs;

	public LogIn(String name, DbServiceImpl db) throws Exception {
		this.NAME = name;
		this.DB = db;
		loadLogIn();

	}

	public void loadLogIn() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select a.user_id,a.role_name,a.role_id,b.city_id,b.city_name,b.x,b.y,a.nowtime "
				+ "from cuser a,city b where b.city_id = a.city_id and a.role_name = '" + NAME + "'");
		Rslist rl = DB.excuteQuery(sql.toString());
		rs = rl.get(0);
	}

	public void updateLogIn(String city_id, Date time) throws Exception {
		java.sql.Date sqlDate = new java.sql.Date(time.getTime());
		StringBuffer sql = new StringBuffer();
		sql.append("update cuser set city_id = '" + city_id + "',nowtime = '" + sqlDate + "' where role_id ='"
				+ rs.getValue("role_id") + "'");
		DB.excuteSql(sql.toString());
		loadLogIn();
	}

	public Rs getLoginfo() {
		return rs;
	}

}
