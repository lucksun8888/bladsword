package com.gui;

import javax.swing.JFrame;


public class GameFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private static int chang = 480;
	private static int kuan = 800;

	public GameFrame() {
		// 设置标题
		this.setTitle("bladesword");
		// 设置大小
		this.setSize(chang, kuan);
		// 定位居中
		this.setLocationRelativeTo(null);
		// 添加关闭操作
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 禁止重置大小
		this.setResizable(false);
		// 显示
		this.setVisible(true);
	}
}
