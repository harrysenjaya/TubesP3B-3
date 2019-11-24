package com.example.tubesp3b_3.Model;

import java.util.Date;
import java.util.Comparator;

public class Manga {
    private String image;
    private String title;
    private String id;
    private String status;
    private Date last_chapter_date;
    private int hits;

    public Manga(String image, String title, String id, String status , Date last_chapter_date, int hits) {
        this.image = image;
        this.title = title;
        this.id = id;
        if(status.equals("2")) {
            this.status = "Completed";
        }
        else if(status.equals("1")){
            this.status = "Ongoing";
        }
        else if(status.equals("0")){
            this.status = "Suspended";
        }
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

    public Date getLast_chapter_date() {
        return last_chapter_date;
    }

    public int getHits() {
        return hits;
    }


    public static Comparator<Manga> atoZComparator = new Comparator<Manga>() {
        @Override
        public int compare(Manga manga, Manga t1) {
            return (int) (manga.getTitle().compareTo(t1.getTitle()));
        }
    };

    public static Comparator<Manga> hitsComparator = new Comparator<Manga>() {
        @Override
        public int compare(Manga manga, Manga t1) {
            return (t1.getHits() < manga.getHits() ? -1 :
                    (t1.getHits() == manga.getHits() ? 0 : 1));
        }
    };

    public static Comparator<Manga> ztoAComparator  = new Comparator<Manga>() {
        @Override
        public int compare(Manga manga, Manga manga2) {
            return (int) (manga2.getTitle().compareTo(manga.getTitle()));
        }
    };

}