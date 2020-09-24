package com.result.impl;

import java.util.ArrayList;

@SuppressWarnings({ "serial", "rawtypes" })
public class Rslist extends ArrayList {

	ArrayList list;
	
	public Rslist() {
		list = new ArrayList();
	}
	@SuppressWarnings("unchecked")
	public void add(Rs rs){
		list.add(rs);
	}
	
	public Rs get(int i){
		return (Rs)list.get(i);
	}
	

	
	public int getLength(){
		return list.size();
	}
}
