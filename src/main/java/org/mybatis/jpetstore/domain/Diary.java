package org.mybatis.jpetstore.domain;

import java.io.Serializable;

public class Diary implements Serializable {

    private static final long serialVersionUID = 8751282103532159742L;

    private int no;
    private String imgurl;
    private String userid;
    private String categoryid;
    private String date;
    private String title;
    private int comments;
    private int likes;

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public int getLikes() {
        return likes;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getUserid() {
        return userid;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getViews() {
        return views;
    }

    private String content;
    private int views;


}
