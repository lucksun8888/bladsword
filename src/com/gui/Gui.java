package com.gui;

import java.util.HashMap;

import com.result.impl.Rslist;


public class Gui {
	/**
	 * ���ݲ˵�ѡ��ض�Ӧ������
	 */
	public static HashMap<Integer, String> printMenu(Rslist rl,String print_name,String print_id){
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 0; i < rl.getLength(); i++) {
			System.out.println(i + ":" + rl.get(i).getValue(print_name));
			map.put(i, (String) rl.get(i).getValue(print_id));
		}
		return map;
	}
}
