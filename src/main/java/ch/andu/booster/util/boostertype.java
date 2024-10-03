package ch.andu.booster.util;

import java.util.HashMap;

public class boostertype {

    private int level;
    private long time;
    private String name;
    public boostertype(String name){
        this.name = name;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
