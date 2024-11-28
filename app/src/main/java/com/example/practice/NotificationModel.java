package com.example.practice;

public class NotificationModel {
    private int img;
    private String title;
    private String description;
    private String date;

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    private String time;

    // Constructor
   public NotificationModel(int img, String title, String description, String date, String time){
       this.img = img;
       this.title = title;
       this.description = description;
       this.date = date;
       this.time = time;
   }

}
