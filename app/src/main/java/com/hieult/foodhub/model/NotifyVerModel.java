package com.hieult.foodhub.model;

public class NotifyVerModel {
    String titlenotify;
    String contentnotify;

    public NotifyVerModel(String titlenotify, String contentnotify) {
        this.titlenotify = titlenotify;
        this.contentnotify = contentnotify;
    }

    public String getTitlenotify() {
        return titlenotify;
    }

    public void setTitlenotify(String titlenotify) {
        this.titlenotify = titlenotify;
    }

    public String getContentnotify() {
        return contentnotify;
    }

    public void setContentnotify(String contentnotify) {
        this.contentnotify = contentnotify;
    }
}
