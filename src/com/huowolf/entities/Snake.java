package com.huowolf.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.huowolf.listener.SnakeListener;
import com.huowolf.util.Global;


public class Snake {

	//代表方向的常量
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	//监听器组
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();
	//存储蛇的链表结构
	private LinkedList<Point> body = new LinkedList<Point>();
	
	private boolean life;					//是否活着
	private boolean pause;					//是否暂停游戏
	private int oldDirection,newDirection;	//新，旧方向的引入（避免一次移动时间内的无效方向）
	private Point oldTail;					//旧的尾巴（在吃掉食物的时候使用）
	private int foodCount = 0;				//吃掉食物的数量
	private Color headColor;
	private Color bodyColor;
	private int sleepTime;
	
	public boolean isLife() {
			return life;
	}
	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}


	public void setHeadColor(Color headColor) {
		this.headColor = headColor;
	}


	public void setBodyColor(Color bodyColor) {
		this.bodyColor = bodyColor;
	}


	public void init() {
		int x = Global.WIDTH/2;
		int y = Global.HEIGHT/2;
		for(int i=0;i<3;i++) {
			body.addLast(new Point(x--,y));
		}
		
		oldDirection = newDirection = RIGHT;
		foodCount = 0;	
		life = true;
		pause = false;
		
		if(sleepTime==0) {
			sleepTime = 300;
		}
	}
	
	
	//清空蛇的节点的方法（用来开始一次新的游戏）
	public void clear() {
		body.clear();
	}
	

	public void setLife(boolean life) {
		this.life = life;
	}


	public boolean getPause() {
		return pause;
	}	
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	
	//用来改变pause常量的状态
	public void changePause() {
		pause = !pause;
	}

	
	//蛇死掉的方法
	public void die() {
		life = false;
	}
	
	
	//蛇移动的方法
	public void move() {
		if(!(oldDirection + newDirection==0)) {
			oldDirection = newDirection ;
		}
		
		//去尾
		oldTail = body.removeLast();
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		
		switch(oldDirection) {
			case UP:
				y--;
				if(y<0) {
					y = Global.HEIGHT -1 ;
				}
				break;
			case DOWN:
				y++;
				if(y >= Global.HEIGHT) {
					y = 0;
				}
				break;
			case LEFT:
				x--;
				if(x<0) {
					x = Global.WIDTH-1;
				}
				break;
			case RIGHT:
				x++;
				if(x >= Global.WIDTH) {
					x = 0;
				}
				break;
		}
		
		Point newHead = new Point(x, y);
		//加头
		body.addFirst(newHead);
	}
	
	
	//改变方向
	public void changeDirection(int direction) {
			newDirection = direction;		
	}
	
	
	//吃食物
	public void eatFood() {		
		body.addLast(oldTail);
		foodCount++;
	}
	
	
	//获取吃到的食物数量
	public int getFoodCount() {
		return foodCount;
	}
	
	
	//蛇是否吃到了自己的身体
	public boolean isEatBody() {
		for(int i=1;i<body.size();i++) {
			if(body.get(i).equals(this.getHead())) 
				return true;
		}
		
		return false;	
	}
	
	
	//获取代表蛇头的节点
	public Point getHead() {
		return body.getFirst();
	}
	
	
	//显示自己
	public void drawMe(Graphics g) {
		if(bodyColor==null) {
			g.setColor(new Color(0x3333FF));
		} else {
			g.setColor(bodyColor);
		}
		
		for(Point p : body) {
			
			g.fillRoundRect(p.x*Global.CELL_SIZE, p.y*Global.CELL_SIZE,
					Global.CELL_SIZE, Global.CELL_SIZE, 15,12 );
		}
		drawHead(g);
	}
	
	//画蛇头
	public void drawHead(Graphics g) {
		if(headColor==null)
			g.setColor(Color.YELLOW);
		else {
			g.setColor(headColor);
		}
		
		g.fillRoundRect(getHead().x * Global.CELL_SIZE, getHead().y* Global.CELL_SIZE, 
				Global.CELL_SIZE, Global.CELL_SIZE, 15,12);
	}
	
	
	//控制蛇移动的线程内部类
	private class SnakeDriver implements Runnable {
		public void run() {
			while(life) {
				if(pause==false) {
					move();			
					for(SnakeListener l : listeners)
						l.snakeMoved(Snake.this);
				}
					
				try {	
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//启动线程的方法
	public void begin() {
		new Thread(new SnakeDriver()).start();
		
	}

	
	//添加监听器
	public void addSnakeListener(SnakeListener l) {
		if(l != null) {
			listeners.add(l);
		}
	}

	//加速
	public void speedUp() {
		if(sleepTime>50) {
			sleepTime-=20;
		}
	}
	
	//减速
	public void speedDown() {
		if(sleepTime<700) {
			sleepTime+=20;
		}
	}

}
