package com.huowolf.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.huowolf.util.Global;


public class Food extends Point{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Color foodColor;
	
	

	public void setFoodColor(Color foodColor) {
		this.foodColor = foodColor;
	}

	public Color getFoodColor() {
		return foodColor;
	}

	public void newFood(Point p) {
		setLocation(p);
	}
	//新食物
	public boolean isFoodEated(Snake snake) {			
		return 	this.equals(snake.getHead());
	}
	//食物被吃掉
	public void drawMe(Graphics g) {
		if(foodColor==null) {
			g.setColor(Color.RED);
		}else {
			g.setColor(foodColor);
		}
		//更改颜色
		g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
		//用预定的颜色填充食物的矩形，引用global里的cell_size
	}
	/*
	 * 执行drawMe这个方法，Graphics类型的对象作为参数，
	 * Graphics g就是Graphics类型的对象g，
	 */

}
