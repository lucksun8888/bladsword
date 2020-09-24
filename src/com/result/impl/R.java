package com.result.impl;

import java.sql.Date;
import java.util.HashMap;

public class R {
	HashMap<String,Object> r;
	
	public R(String keyname,Object value,int type) {
		r = new HashMap<String,Object>();
		r.put("keyname", keyname.toLowerCase());
		r.put("value", value);
		r.put("type", typeOf(type));
	}
	
	String getKey(){
		return ((String) r.get("keyname")).toLowerCase();
	}
	
	String getType(){
		return (String) r.get("type");
	}
	
	void setValue(String keyname,Object value){
		r.put("keyname", keyname.toLowerCase());
		r.put("value", value);
	}
	
	
	
	
	public Object getValue(String keyname){
		if("int".equals(r.get("type"))){
			return (Integer)r.get(keyname.toLowerCase());
		}else if("string".equals(r.get("type"))){
			return (String)r.get(keyname.toLowerCase());
		}else if("date".equals(r.get("type"))){
			return (Date)r.get(keyname.toLowerCase());
		}else{
			return r.get(keyname.toLowerCase());
		}
	}
	
	
	protected String typeOf(int sqltype)
    {
        if(sqltype == -6 || sqltype == 5 || sqltype == 4)
            return "int";
        if(sqltype == -5)
            return "long";
        if(sqltype == 2 || sqltype == 3)
            return "decimal";
        if(sqltype == 1 || sqltype == 12)
            return "string";
        if(sqltype == 91)
            return "date";       
        if(sqltype == 2004)
            return "binarystreamtype";
        if(sqltype == 2005)
            return "characterstreamtype";
        else
        	return "string";
    }


}
