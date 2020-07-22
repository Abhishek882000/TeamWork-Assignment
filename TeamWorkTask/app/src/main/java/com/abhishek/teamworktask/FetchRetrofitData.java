package com.abhishek.teamworktask;

public class FetchRetrofitData {

    private int Albums;
    private int Id;
    private String Title;
    private String Url;
    private String ThumbnailUrl;

    public FetchRetrofitData(int albums, int id, String title, String url, String thumbnailUrl) {
        Albums = albums;
        Id = id;
        Title = title;
        Url = url;
        ThumbnailUrl = thumbnailUrl;
    }

    public int getAlbums() {
        return Albums;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getUrl() {
        return Url;
    }

    public String getThumbnailUrl() {
        return ThumbnailUrl;
    }
}
