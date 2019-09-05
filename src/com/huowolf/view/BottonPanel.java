package com.huowolf.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BottonPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	//在程序中定义，会使软件版本兼容，无论怎么改，都可以相互序列化和反序列化。
	
	private JButton startButton;
	private JButton pauseButton;
	private JButton endButton;
	private JLabel scoreLabel;
	private int score = 0;
	
	public BottonPanel() {

		//setFocusable(false);
		setLayout(null);//布局管理器
//		setBounds(455, 0, 235, 450);
		setBounds(710,0,235,700);//调整游戏右侧面板大小和位置
		
		startButton = new JButton("开始游戏");
		startButton.setBounds(42,20, 150, 50);
		add(startButton);
		
		pauseButton = new JButton("暂停游戏");
		pauseButton.setBounds(42, 90, 150, 50);
		add(pauseButton);	
		
		endButton = new JButton("结束游戏");
		endButton.setBounds(42,160, 150, 50);
		add(endButton);		
		
		scoreLabel = new JLabel("积分:");
		scoreLabel.setFont(new Font("Serif",Font.BOLD,20));//设置字体，风格，字号
		scoreLabel.setBounds(30, 450, 100, 30);
		add(scoreLabel);
		
		
		/*scorePanel = new JPanel();
		scorePanel.setBounds(30, 180, 150, 100);
		scorePanel.setBackground(Color.BLUE);
		add(scorePanel);*/
		
		Color c= new Color(0, 250,154);//定义右侧面板颜色
		this.setBackground(c);
		
		this.setFocusable(true);
		
	}
	
	
	public JButton getStartButton() {
		return startButton;
	}


	public JButton getPauseButton() {
		return pauseButton;
	}


	public JButton getEndButton() {
		return endButton;
	}



	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}

	
	public void paintComponent(Graphics g) {//调整积分颜色，字体，大小
		
		super.paintComponent(g);
		g.setColor(Color.red);
		g.setFont(new Font("Serif",Font.BOLD,100));
		g.drawString(score+"", 120, 500);
	}
	

}
