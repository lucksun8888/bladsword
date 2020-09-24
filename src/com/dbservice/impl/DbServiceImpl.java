package com.dbservice.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.dbservice.ConnectTionManager;
import com.dbservice.DbService;
import com.result.impl.R;
import com.result.impl.Rs;
import com.result.impl.Rslist;

//import org.apache.log4j.Logger;

public class DbServiceImpl implements DbService{

	Connection CONN;
	
	public void intiDB() throws Exception {
		CONN = ConnectTionManager.getConnection();
	}

	public Connection getCONN() {
		return CONN;
	}

	public void commitDB() throws Exception {
		CONN.commit();
	}

	public void insertDB(String tablename, HashMap<String, Object> map)
			throws Exception {
		CONN.createStatement().execute("");
	}

	public void updateDB(String tablename, HashMap<String, Object> map)
			throws Exception {
		CONN.createStatement().execute("");
	}

	public void deleteDB(String tablename, HashMap<String, Object> map)
			throws Exception {
		CONN.createStatement().execute("");
	}

	public Rslist queryDBs(String tablename, Map<String, Object> args)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " + tablename + " where 1=1 ");
		for (String key : args.keySet()) {
			sql.append(" and ").append(key).append(" = '")
					.append(args.get(key)).append("'");
		}
		System.out.println(sql);
		Statement st = CONN.createStatement();
		ResultSet rst = st.executeQuery(sql.toString());

		ResultSetMetaData rm = rst.getMetaData();
		int rmi = rm.getColumnCount();
		Rslist l = new Rslist();
		while (rst.next()) {
			Rs rs = new Rs();
			for (int i = 1; i < rmi+1; i++) {
				R r = new R(rm.getColumnName(i), rst.getObject(rm.getColumnName(i)), rm.getColumnType(i));
				rs.add(r);
			}
			l.add(rs);
		}
		st.close();

		return l;

	}
	
	
	public Rs queryDB(String tablename, Map<String, Object> args)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from " + tablename + " where 1=1 ");
		for (String key : args.keySet()) {
			sql.append(" and ").append(key).append(" = '")
					.append(args.get(key)).append("'");
		}
		System.out.println(sql);
		Statement st = CONN.createStatement();
		ResultSet rst = st.executeQuery(sql.toString());

		ResultSetMetaData rm = rst.getMetaData();
		int rmi = rm.getColumnCount();
		Rslist l = new Rslist();
		while (rst.next()) {
			Rs rs = new Rs();
			for (int i = 1; i < rmi+1; i++) {
				R r = new R(rm.getColumnName(i), rst.getObject(rm.getColumnName(i)), rm.getColumnType(i));
				rs.add(r);
			}
			l.add(rs);
		}
		st.close();
		
		return (Rs)l.get(0);

	}
	

	public Rslist excuteQuery(String sql) throws Exception {

		Statement st = CONN.createStatement();
		System.out.println(sql);
		
		ResultSet rst = st.executeQuery(sql.toString());
		ResultSetMetaData rm = rst.getMetaData();
		int rmi = rm.getColumnCount();
		Rslist l = new Rslist();
		while (rst.next()) {
			Rs rs = new Rs();
			for (int i = 1; i < rmi+1; i++) {
				R r = new R(rm.getColumnName(i), rst.getObject(rm.getColumnName(i)), rm.getColumnType(i));
				rs.add(r);
			}
			l.add(rs);
		}
		st.close();
		commitDB();
		return l;
	}
	
	public void excuteSql(String sql) throws Exception {

		Statement st = CONN.createStatement();
		System.out.println(sql);
		st.execute(sql); 
		st.close();
		commitDB();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map setValue(String condition, Object o) {
		Map m = new HashMap();
		m.put("condition", condition);
		m.put("value", o);
		return m;
	}

}
