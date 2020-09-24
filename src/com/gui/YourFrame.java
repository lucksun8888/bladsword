package com.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class YourFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	Panel flowLayoutPanel0;
	Panel flowLayoutPanel1;
	private Image image = null;
	private ImageIcon icon = new ImageIcon("d:\\Sunset.jpg");

	private void generateFlowLayoutPanel0() {
		flowLayoutPanel0 = new Panel();
		flowLayoutPanel0.setBackground(Color.black);
		// flowLayoutPanel.setLayout(new GridLayout(10, 10));
		// setLayout(new GridLayout(2, 2));
		// Button button1 = new Button("button1");
		// Button button2 = new Button("button2");
		// Button button3 = new Button("button3");
		// Button button4 = new Button("button4");
		// Button button5 = new Button("button5");
		// button1.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// ((Button) e.getSource()).setLabel("welcome ");
		// }
		// });
		// flowLayoutPanel.add(button1);
		// flowLayoutPanel.add(button2);
		// flowLayoutPanel.add(button3);
		// flowLayoutPanel.add(button4);
		// flowLayoutPanel.add(button5);
	}

	private void generateFlowLayoutPanel1() {
		flowLayoutPanel1 = new Panel();
		flowLayoutPanel1.setLayout(new GridLayout(5, 1));
		// setLayout(new GridLayout(2, 2));
		Button button1 = new Button("button1");
		Button button2 = new Button("button2");
		Button button3 = new Button("button3");
		Button button4 = new Button("button4");
		Button button5 = new Button("button5");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((Button) e.getSource()).setLabel("welcome ");
			}
		});
		flowLayoutPanel1.add(button1);
		flowLayoutPanel1.add(button2);
		flowLayoutPanel1.add(button3);
		flowLayoutPanel1.add(button4);
		flowLayoutPanel1.add(button5);
	}

	public YourFrame(String panelName) {
		super("panelName");
		// generateBorderLayoutPanel();
		generateFlowLayoutPanel0();
		// generateGridLayoutPanel();
		generateFlowLayoutPanel1();
		setLayout(new GridLayout(2, 2));
		// add(borderLayoutPanel);
		add(flowLayoutPanel0);
		// add(gridLayoutPanel);
		add(flowLayoutPanel1);
		setSize(480, 800);
		setLocation(100, 100);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		YourFrame yourFrame = new YourFrame("welcome");
		yourFrame.setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		image = icon.getImage();
		g.drawImage(image, 0, 0, null);
	}
}
