package com.example.tubesp3b_3.Model;

import java.util.Date;

public class Chapter {
    private String id, title, chapter;
    private Date lastUpdated;

    public Chapter(String chapter,Date lastUpdated, String title, String id) {
        this.chapter = chapter;
        this.id = id;
        this.title = title;
        this.lastUpdated = lastUpdated;
    }

    public String getChapter() {
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
