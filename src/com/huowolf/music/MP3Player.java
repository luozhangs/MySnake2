package com.huowolf.music;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by zhang on 2019/6/13.
 * MP3播放器
 */
public class MP3Player extends Thread{

    //定义一个文件路径前缀常量（项目根目录）
    private final static String filePrefixPath = System.getProperty("user.dir");
    //初始化一个歌曲列表数组
    private static String[] filePaths ={
            filePrefixPath+"/resource/泠鸢yousa1.mp3",
            filePrefixPath+"/resource/泠鸢yousa2.mp3",
            filePrefixPath+"/resource/泠鸢yousa3.mp3",
    };

    private PlayOption playOption = new PlayOption();

    public MP3Player() {
        //默认歌曲列表
        playOption.setFileList(filePaths);
    }

    public MP3Player(PlayOption playOption) {
        //读取设置项
       this.setPlayOption(playOption);
    }

    //定义播放器
    private static Player player;
    //定义播放音乐的线程
    private Music music;
    //全局定义一个歌曲下标（用于获取音乐文件）
    private int index = 0;

    //用于标识是否打开音乐或者关闭
    private Boolean isOpen = false;

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    /**
     * 初始化音乐播放器
     * @param index
     */
    public String initPlayer(int index){
        String file = filePaths[index];
        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(file));
            this.player = new Player(buffer);
            playOption.setIndex(index);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    public synchronized void open()  {
        this.isOpen = true;
        notify();
    }

    public synchronized void stopd(){
        this.isOpen = false;
        notify();
    }

    public PlayOption getPlayOption() {
        return playOption;
    }

    //外部类设置播放参数的方法
    public void setPlayOption(PlayOption playOption) {
        //读取设置项
        if(playOption.getFileList()!=null&&playOption.getFileList().length>0){
            this.playOption.setFileList(playOption.getFileList());
            this.filePaths = playOption.getFileList();
        }
        this.playOption.setPlayType(playOption.getPlayType());
    }

    public void restart(){
        player.close();
        music.stop();
        this.open();
    }

    /**
     * 下一曲
     */
    public void next(){
        index++;
        if(index>=filePaths.length){
            index=0;
        }
        this.restart();
    }

    /**
     * 上一曲
     */
    public void previous(){
        index--;
        if(index<0){
            index=0;
        }
        this.restart();
    }

    public  void change(int index)  {
        this.index = index;
        player.close();
        music.stop();
        this.open();
    }

    /**
     * 通过递归遍历某个目录下所有的歌曲（包括子目录下的歌曲）
     * @param file
     * @param list
     */
    public void setFilePaths(File file,List list){
        for (File file1:file.listFiles()){
            if(file1.isDirectory()){
                setFilePaths(file1,list);
            }else{
                String fileName = file1.getName();
                String suffix = fileName.split("[.]")[1];
                if("mp3".equals(suffix)){
                    list.add(file1.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir"));
        Scanner in = new Scanner(System.in);
        MP3Player mp3Player = new MP3Player();
        mp3Player.open();
        mp3Player.start();
        List<String> list = new ArrayList<>();
        //批量导入播放列表
        File file  = new File("D:\\music");
        mp3Player.setFilePaths(file,list);
        PlayOption option = new PlayOption();
        option.setFileList(list.toArray(filePaths));
        mp3Player.setPlayOption(option);
        System.err.println("请输入数字操作！！！");
        System.out.println("可以通过输入1-5来控制播放效果  8,9控制上一曲下一曲,0控制停止");
        while (true){
            int a = in.nextInt();
            //可以通过输入0-5来控制播放效果  8,9控制上一曲下一曲
            if(a==0){//结束播放
                mp3Player.stopd();
            }else if (a==8){
                mp3Player.previous();
            }else if(a==9){
                mp3Player.next();
            }else{
                mp3Player.playOption.setPlayType(a);
                mp3Player.restart();
            }
        }
    }

    @Override
    public void run() {
        synchronized (this){//使用同步锁用来操作阻塞唤醒操作
            while (true){
                //判断是否停止
//                System.out.println(isOpen);
                if(isOpen){
                    initPlayer(index);
                    music = new Music(player,playOption);
                    music.start();
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    if(music!=null){
                        player.close();//停止播放音乐（必须执行改行代码用于关闭流，不然不能重新创建播放器对象）
                        music.stop();//停止播放音乐线程（因为是同一个对象，可以这样调用）
                    }
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
