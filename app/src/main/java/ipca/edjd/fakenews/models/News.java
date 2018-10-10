package ipca.edjd.fakenews.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class News {

    String  author      ;
    String  title       ;
    String  description ;
    String  url         ;
    Date    publishedAt ;
    String  content     ;
    String  urlToImage  ;
    int     categoryId  ;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public News(String author, String title, String description, String url, Date publishedAt, String content, String urlToImage, int categoryId) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.publishedAt = publishedAt;
        this.content = content;
        this.urlToImage = urlToImage;
        this.categoryId = categoryId;
    }

    public News (){
        this.author      = "";
        this.title       = "";
        this.description = "";
        this.url         = "";
        this.publishedAt = new Date();
        this.content     = "";
        this.urlToImage  = "";
        this.categoryId  = 0;
    }

    public static News getNewsFromJson (JSONObject jsonObject){

        News newsItem = new News();
        try {
            newsItem.author         = jsonObject.getString("author");
            newsItem.title          = jsonObject.getString("title");
            newsItem.description    = jsonObject.getString("description");
            newsItem.url            = jsonObject.getString("url");
            //newsItem.publishedAt  = ;
            newsItem.content        = jsonObject.getString("content");
            newsItem.urlToImage     = jsonObject.getString("urlToImage");
            //newsItem.categoryId   = ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsItem;
    }


}
