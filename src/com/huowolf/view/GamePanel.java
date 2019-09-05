package com.huowolf.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.huowolf.entities.Food;
import com.huowolf.entities.Ground;
import com.huowolf.entities.Snake;
import com.huowolf.util.Global;

public class GamePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private Snake snake;
	private Food food;
	private Ground ground;	
	public Color backgroundColor;
	
	public GamePanel() {
		setLocation(0, 0);
		/* 设置大小和布局 */
		this.setSize(Global.WIDTH * Global.CELL_SIZE, Global.HEIGHT
				* Global.CELL_SIZE);
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		this.setFocusable(true);
		
	}


	public void display(Snake snake,Food food,Ground ground) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		
		repaint();
	}

	
	//清空游戏面板（擦除效果）
	public void clearDraw(Graphics g) {
			if(backgroundColor==null) {
				g.setColor(new Color(0x00FFFF));
			}
			else {
				g.setColor(backgroundColor);
			}
			g.fillRect(0, 0, Global.WIDTH*Global.CELL_SIZE, Global.HEIGHT*Global.CELL_SIZE);
	}
	
	
	@Override
	public void paint(Graphics g) {
			clearDraw(g);
			//重新显示		
			if(ground != null && snake != null && food != null) {
					ground.drawMe(g);
					food.drawMe(g);
					snake.drawMe(g);
			}
			if(snake!=null && snake.isLife()==false)  {
				recover(g);
			}
	
		}

	
	//恢复工作
	public void recover(Graphics g) {
		clearDraw(g);
		
		//在游戏主面板区绘制“对不起，你输了！”
		g.setColor(Color.GREEN);
		g.setFont(new Font("Serif",Font.BOLD,50));
		g.drawString("对不起，你输了！", 150, 360);
		
	}
	
	
}
