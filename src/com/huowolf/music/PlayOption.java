package com.huowolf.music;

/**
 * Created by zhang on 2019/6/14.
 * 音乐播放器设置类
 */
public class PlayOption {


    /**
     * 播放模式（单曲播放、顺序播放、单曲循环、全部循环、随机播放）
     * 默认为单曲播放
     */
    private int playType = PlayType.SINGLE.getValue();

    /**
     * 文件列表
     */
    private String[] fileList;

    /**
     * 当前播放音乐下标
     */
    private int index;


    public int getPlayType() {
        return playType;
    }

    public void setPlayType(int playType) {
        this.playType = playType;
    }

    public String[] getFileList() {
        return fileList;
    }

    public void setFileList(String[] fileList) {
        this.fileList = fileList;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
