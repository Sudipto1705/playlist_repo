package com.company;

public class Songs {
    private String name;
    private float duration;

    public Songs(String name, float duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public float getDuration() {
        return duration;
    }
}
