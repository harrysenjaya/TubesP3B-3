package com.example.tubesp3b_3;

import java.util.Date;


public class MangaInfo {

    private String image, title, artist, author, desc, status, category;
    private Date created, last_chapter_date;
    private String[] chapter;

    public MangaInfo(String image, String title, String artist, String author, String desc, String status, String category, Date created, Date last_chapter_date, String[] chapter) {
        this.image = image;
        this.title = title;
        this.artist = artist;
        this.author = author;
        this.desc = desc;
        this.status = status;
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

    public String[] getChapter() {
        return chapter;
    }
}
