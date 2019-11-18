package com.example.tubesp3b_3;

import java.util.Date;

public class Manga {
    private String image;
    private String title;
    private String id;
    private String status;
    private String category;
    private Date last_chapter_date;
    private int hits;

    public Manga(String image, String title, String id, String status, String category, Date last_chapter_date, int hits) {
        this.image = image;
        this.title = title;
        this.id = id;
        if(status.equals("1")) {
            this.status = "Complete";
        }
        else{
            this.status = "Ongoing";
        }
        this.category = category;
        this.last_chapter_date = last_chapter_date;
        this.hits = hits;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public Date getLast_chapter_date() {
        return last_chapter_date;
    }

    public int getHits() {
        return hits;
    }
}