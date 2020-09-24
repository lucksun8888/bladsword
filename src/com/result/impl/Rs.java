package com.result.impl;

import java.util.ArrayList;
import java.util.HashMap;

public class Rs{
	HashMap<String,Object> rs;
	
	public Rs() {
		rs = new HashMap<String,Object>();		
	}
	
	public void add(R r){
		rs.put(r.getKey(), r);
	}
	
	void setValue(String keyname,Object value){
		((R)rs.get("keyname")).setValue(keyname,value);
	}
	
	public Object getValue(String keyname){
		if(!rs.containsKey(keyname.toLowerCase())){
			return null;
		}
		return ((R)rs.get(keyname)).getValue("value");
	}
	
	public ArrayList<String> getIndexName(){
		ArrayList<String> al = new ArrayList<String>();
		for(String key:rs.keySet()){
			al.add(key);
		}		
		return al;
	}
}
