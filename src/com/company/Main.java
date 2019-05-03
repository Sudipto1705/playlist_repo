package com.company;

import java.util.*;

public class Main {

    static ArrayList<Album> albums = new ArrayList<Album>();
    static LinkedList<Songs> playList = new LinkedList<Songs>();

    public static void main(String[] args) {
        createAlbum("Love");
        createAlbum("Sex");
        addSongToAlbum("Love","abc",3.45f);
        addSongToAlbum("Love","pqr",2.32f);
        addSongToAlbum("Love","lmn",1.19f);
        addSongToAlbum("Sex","plj",5.15f);
        addSongToAlbum("Sex","wer",3.22f);
        addSongToAlbum("Sex","qpu",3.11f);
        addSongToPlaylist("abc");
        addSongToPlaylist("pqr");
        addSongToPlaylist("lmn");
        addSongToPlaylist("plj");
        addSongToPlaylist("wer");
        addSongToPlaylist("qpu");

        Scanner scanner = new Scanner(System.in);
        printMenu();
        ListIterator<Songs> i = playList.listIterator();

        boolean quit = false, forward = true;
        while(!quit) {
            System.out.print("Enter Choice: ");
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    quit = true;
                    break;
                case 2:
                    if(forward) {
                        if(i.hasNext())
                            System.out.println(i.next().getName() + " playing");
                        else
                            System.out.println("You have reached the end");
                    }
                    else {
                        forward = true;
                        if(i.hasNext())
                            i.next();
                        else {
                            System.out.println("You have reached the end");
                            break;
                        }
                        if(i.hasNext())
                            System.out.println(i.next().getName() + " playing");
                        else
                            System.out.println("You have reached the end");
                    }
                    break;
                case 3:
                    if(!forward) {
                        if(i.hasPrevious())
                            System.out.println(i.previous().getName() + " playing");
                        else
                            System.out.println("You are at the beginning");
                    }
                    else {
                        if(i.hasPrevious())
                            i.previous();
                        else {
                            System.out.println("You are at the beginning");
                            break;
                        }
                        if(i.hasPrevious()) {
                            System.out.println(i.previous().getName() + " playing");
                            forward = false;
                        }
                        else
                            System.out.println("You are at the beginning");
                    }
                    break;
                case 4:
                    if(forward) {
                        forward = false;
                        System.out.println(i.previous().getName() + " playing");
                    }
                    else {
                        forward = true;
                        System.out.println(i.next().getName() + " playing");
                    }
                    break;
                case 5:
                    if(forward) {
                        System.out.println("Deleting "+i.previous().getName());
                        i.next();
                        i.remove();
                    }
                    else {
                        System.out.println("Deleting "+i.next().getName());
                        i.previous();
                        i.remove();
                    }
                    break;
                case 6:
                    printMenu();
                    break;
            }
        }
	}

    private static void printMenu() {
        System.out.println("Select:\n1. Quit\n2. Skip Forward\n3. Skip Backwards\n" +
                "4. Replay\n5. Delete Song\n6. Print Menu");
    }

    private static void createAlbum(String name) {
        if(!searchAlbum(name))
            albums.add(new Album(name));
    }

    private static void addSongToAlbum(String albumName, String songName,float duration) {
        ListIterator<Album> i = albums.listIterator();
        while(i.hasNext()) {
            if(i.next().getName().toLowerCase().equals(albumName.toLowerCase())) {
                i.previous().addSong(songName, duration);
                break;
            }
        }
    }

    private static boolean searchAlbum(String albumName) {
        ListIterator<Album> i = albums.listIterator();
        while(i.hasNext()) {
            if(i.next().getName().toLowerCase().equals(albumName))
                return true;
        }
        return false;
    }

    private static boolean searchSongs(String songName) {
        ListIterator<Songs> i = playList.listIterator();
        while(i.hasNext()) {
            if(i.next().getName().toLowerCase().equals(songName.toLowerCase()))
                return true;
        }
        return false;
    }


    private static void addSongToPlaylist(String songName) {
        Return r = new Return(false,-1);
        Scanner scanner = new Scanner(System.in);
        if(!searchSongs(songName)) {
            ListIterator<Album> i = albums.listIterator();
            while(i.hasNext()) {
                r = i.next().searchSong(songName);
                if(r.isA()) {
                    playList.add(new Songs(songName,r.getDuartion()));
                    break;
                }
            }
            if(!r.isA()) {
                System.out.println("Song not present in any album");
                System.out.println("Choose Action:\n1. Add song to existing album\n2. Create new album and add song to it");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch(choice) {
                    case 1:
                        System.out.printf("Enter album name: ");
                        String n = scanner.nextLine();
                        System.out.printf("Enter song duration:");
                        float d = scanner.nextFloat();
                        scanner.nextLine();
                        addSongToAlbum(n,songName,d);
                        playList.add(new Songs(songName,d));
                        break;
                    case 2:
                        System.out.printf("Enter album name: ");
                        n = scanner.nextLine();
                        createAlbum(n);
                        System.out.printf("Enter song duration:");
                        d = scanner.nextFloat();
                        scanner.nextLine();
                        addSongToAlbum(n,songName,d);
                        playList.add(new Songs(songName,d));
                        break;
                }
            }
        }
    }

    private static void displayPlaylist() {
        Iterator<Songs> i = playList.iterator();
        while(i.hasNext()) {
            System.out.println(i.next().getName());
        }
    }
}