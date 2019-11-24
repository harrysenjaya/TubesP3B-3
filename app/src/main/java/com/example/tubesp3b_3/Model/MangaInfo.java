package com.example.tubesp3b_3.Model;

import java.util.ArrayList;
import java.util.Date;


public class MangaInfo {

    private String image, title, artist, author, desc, status, category;
    private Date created, last_chapter_date;
    private ArrayList<Chapter> chapter;

    public MangaInfo(String image, String title, String artist, String author, String desc, String status, String category, Date created, Date last_chapter_date, ArrayList<Chapter> chapter) {
        this.image = image;
        this.title = title;
        this.artist = artist;
        this.author = author;
        this.desc = desc;
        if(status.equals("2")) {
            this.status = "Completed";
        }
        else if(status.equals("1")){
            this.status = "Ongoing";
        }
        else if(status.equals("0")){
            this.status = "Suspended";
        }
        this.category = category;
        this.created = created;
        this.last_chapter_date = last_chapter_date;
        this.chapter = chapter;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAuthor() {
        return author;
    }

    public String getDesc() {
        return desc;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLast_chapter_date() {
        return last_chapter_date;
    }

    public ArrayList<Chapter> getChapter() {
        return chapter;
    }
}
