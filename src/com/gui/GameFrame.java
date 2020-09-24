package com.gui;

import javax.swing.JFrame;


public class GameFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private static int chang = 480;
	private static int kuan = 800;

	public GameFrame() {
		// ���ñ���
		this.setTitle("bladesword");
		// ���ô�С
		this.setSize(chang, kuan);
		// ��λ����
		this.setLocationRelativeTo(null);
		// ��ӹرղ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ��ֹ���ô�С
		this.setResizable(false);
		// ��ʾ
		this.setVisible(true);
	}
}
