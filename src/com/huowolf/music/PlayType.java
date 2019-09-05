package com.huowolf.music;

/**
 * Created by zhang on 2019/6/14.
 * 播放模式枚举类
 */
public enum PlayType {
    SINGLE("单曲播放",1),
    CIRCLE("顺序播放",2),
    LOOP_SINGLE("单曲循环",3),
    LOOP_ALL("全部循环",4),
    RAND("随机播放",5),
    ;
    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    PlayType(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
