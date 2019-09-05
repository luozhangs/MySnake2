package com.huowolf.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameMenu extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu colorMenu,speeceMenu,guanKMenu,abortMenu,musicMenu;//菜单
	private JMenuItem item1,item2,item3,item4;
	private JMenuItem spItem1,spItem2,spItem3,spItem4;
	private JMenuItem guanKItem1,guanKItem2,guanKItem3;
	private JMenuItem abItem;
	private JMenuItem musicItem1,musicItem2;
	
	
	public JMenu getColorMenu() {
		return colorMenu;
	}



	public JMenu getSpeeceMenu() {
		return speeceMenu;
	}



	public JMenu getMapMenu() {
		return guanKMenu;
	}



	public JMenu getAbortMenu() {
		return abortMenu;
	}
	
	public JMenu getMusicMenu() {
		return musicMenu;
	}



	public JMenuItem getItem1() {
		return item1;
	}



	public JMenuItem getItem2() {
		return item2;
	}



	public JMenuItem getItem3() {
		return item3;
	}



	public JMenuItem getItem4() {
		return item4;
	}



	public JMenuItem getSpItem1() {
		return spItem1;
	}



	public JMenuItem getSpItem2() {
		return spItem2;
	}



	public JMenuItem getSpItem3() {
		return spItem3;
	}



	public JMenuItem getSpItem4() {
		return spItem4;
	}



	public JMenuItem getMapItem1() {
		return guanKItem1;
	}



	public JMenuItem getMapItem2() {
		return guanKItem2;
	}



	public JMenuItem getMapItem3() {
		return guanKItem3;
	}



	public JMenuItem getAbItem() {
		return abItem;
	}
	
	public JMenuItem getMusicItem1() {
		return musicItem1;
	}
	
	public JMenuItem getMusicItem2() {
		return musicItem2;
	}



	public GameMenu() {
		
		colorMenu = new JMenu("设置颜色");
		add(colorMenu);
		
		speeceMenu = new JMenu("设置速度");
		add(speeceMenu);
		
		guanKMenu = new JMenu("关卡");
		add(guanKMenu);
		
		abortMenu = new JMenu("帮助");
		add(abortMenu);
		
		musicMenu=new JMenu("放松一下");
		add(musicMenu);
		
		item1 = new JMenuItem("背景颜色");
		item2 = new JMenuItem("食物颜色");
		item3 = new JMenuItem("蛇头颜色");
		item4 = new JMenuItem("蛇身颜色");
		
		colorMenu.add(item1);
		colorMenu.add(item2);
		colorMenu.add(item3);
		colorMenu.add(item3);
		colorMenu.add(item4);
		
		spItem1 = new JMenuItem("单车");
		spItem2 = new JMenuItem("汽车");
		spItem3 = new JMenuItem("高铁");
		spItem4 = new JMenuItem("飞机");
		
		speeceMenu.add(spItem1);
		speeceMenu.add(spItem2);
		speeceMenu.add(spItem3);
		speeceMenu.add(spItem4);
		
		guanKItem1 = new JMenuItem("关卡一");
		guanKItem2 = new JMenuItem("关卡二");
		guanKItem3 = new JMenuItem("关卡三");
		
		guanKMenu.add(guanKItem1);
		guanKMenu.add(guanKItem2);
		guanKMenu.add(guanKItem3);
		
		abItem = new JMenuItem("使用说明");
		abortMenu.add(abItem);
		
		musicItem1=new JMenuItem("播放音乐",1);
		musicItem2=new JMenuItem("停止",0);
		
		musicMenu.add(musicItem1);
		musicMenu.add(musicItem2);
	}
	
}
