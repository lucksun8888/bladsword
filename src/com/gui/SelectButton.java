package com.gui;

import java.awt.Button;

public class SelectButton extends Button{
	private static final long serialVersionUID = 1L;

	/**
	 * 初始化选择按钮，注册按钮内容和触发事件
	 */
	SelectButton(String value){
		this.setLabel(value);
		
	}
}
