package com.huowolf.music;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Created by zhang on 2019/6/13.
 */
public class Music extends Thread {

    private Player player;
    private PlayOption playOption ;
    private String file;
    private int index;
    public Music(Player player, PlayOption playOption) {
        this.player = player;
        this.playOption = playOption;
    }
    public Music(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            if(this.playOption.getPlayType()==PlayType.SINGLE.getValue()){
                player.play();
            }
            //判断是否循环播放
            if(this.playOption.getPlayType()!=PlayType.SINGLE.getValue()){
                while (true){
                    switch (playOption.getPlayType()){
                        case 2:
                            index = playOption.getIndex();
                            index++;
                            if(index>=playOption.getFileList().length){
                                file = null;
                            }
                            break;
                        case 3:file = playOption.getFileList()[playOption.getIndex()];break;
                        case 4:
                            index = playOption.getIndex();
                            index++;
                            if(index>=playOption.getFileList().length)index=0;
                            file = playOption.getFileList()[index];
                            break;
                        case 5:
                            index = new Random().nextInt(playOption.getFileList().length);
                            file = playOption.getFileList()[index];
                            break;
                    }
                    if(file == null) continue;
                    player = new Player(new BufferedInputStream(new FileInputStream(file)));
                    player.play();
                }
            }
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
