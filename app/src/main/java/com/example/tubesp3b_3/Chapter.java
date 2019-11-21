package com.example.tubesp3b_3;

import java.util.Date;

public class Chapter {
    private int chapter;
    private String id, title;
    private Date lastUpdated;

    public Chapter(int chapter,Date lastUpdated, String title, String id) {
        this.chapter = chapter;
        this.id = id;
        this.title = title;
        this.lastUpdated = lastUpdated;
    }

    public int getChapter() {
        return chapter;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }
}
