package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Album {
    private String name;
    private ArrayList<Songs> songs;

    public Album(String name) {
        this.name = name;
        this.songs = new ArrayList<Songs>();
    }

    public void addSong(String name, float duration) {
        if(!searchSong(name).isA())
            songs.add(new Songs(name,duration));
    }

    public boolean removeSong(String name) {
        Iterator<Songs> i = songs.iterator();
        while(i.hasNext()) {
            if(i.next().getName().toLowerCase().equals(name.toLowerCase())) {
                i.remove();
                return true;
            }
        }
        return false;
    }

    public Return searchSong(String name) {
        ListIterator<Songs> i = songs.listIterator();
        while(i.hasNext()) {
            if(i.next().getName().toLowerCase().equals(name.toLowerCase()))
                return (new Return(true,i.previous().getDuration()));
        }
        return (new Return(false,-1f));
    }

    public String getName() {
        return name;
    }

    public ArrayList<Songs> getSongs() {
        return songs;
    }
}