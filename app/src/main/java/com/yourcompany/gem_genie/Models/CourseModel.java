package com.yourcompany.gem_genie.Models;

public class CourseModel {
    int id;
    String avatar,title,description;

    public CourseModel(int id, String avatar, String title, String description) {
        this.id = id;
        this.avatar = avatar;
        this.title = title;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
