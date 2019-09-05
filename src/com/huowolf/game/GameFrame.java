package com.huowolf.game;


import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.huowolf.controller.Controller;
import com.huowolf.entities.Food;
import com.huowolf.entities.Ground;
import com.huowolf.entities.Snake;
import com.huowolf.music.MP3Player;
import com.huowolf.util.Global;
import com.huowolf.view.BottonPanel;
import com.huowolf.view.GameMenu;
import com.huowolf.view.GamePanel;

public class GameFrame extends JFrame {

	/**	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new GameFrame(new Controller(new Snake(), new Food(), new Ground(), 
				new GamePanel(), new GameMenu(),new BottonPanel(),new MP3Player()));

	}

	
	//各对象
	private GamePanel gamePanel;
	private GameMenu gameMenu;
	private Snake snake;
	//private Food food;
	//private Ground ground;
	private Controller controller;	
	private JPanel buttonPanel;

	
	
	public GameFrame(Controller c) {
		this.controller = c;
		snake = controller.getSnake();
		gameMenu = controller.getGameMenu();
		gamePanel = controller.getGamePanel();
		buttonPanel = controller.getBottonPanel();

		setTitle("听风吟贪吃蛇");
		setBounds(300,100,Global.WIDTH*Global.CELL_SIZE+250,Global.HEIGHT*Global.CELL_SIZE+60);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = this.getContentPane(); 		
		this.setJMenuBar(gameMenu);
		
		contentPane.add(gamePanel);
		contentPane.add(buttonPanel);
		
		setResizable(false);
		setVisible(true);

		
		/* 让窗口居中 */
		this.setLocation(this.getToolkit().getScreenSize().width / 2
				- this.getWidth() / 2, this.getToolkit().getScreenSize().height
				/ 2 - this.getHeight() / 2);
		
		
		gamePanel.addKeyListener(controller);
		snake.addSnakeListener(controller);	
		controller.newGame();

		//启动音乐线程
		//可以在此处设置播放参数（比如循环播放，随机播放等，也可以增加播放歌曲列表）
		MP3Player mp3Player = controller.getMp3Player();
		mp3Player.start();
		
	}
	
}
