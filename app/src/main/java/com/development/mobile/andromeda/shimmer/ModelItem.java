package com.development.mobile.andromeda.shimmer;

class ModelItem {
    private String author;
    private int imgId;

    private String headerTag;
    private String headerDesk;
    private String headerAuthor;
    private int imgIdHeader;
    private String urlToImage;

    ModelItem(String author, int imgId){
        this.author = author;
        this.imgId = imgId;
    }
    ModelItem(String headerAuthor, String url, String headerTag, String headerDesk){
        this.headerAuthor = headerAuthor;
        this.urlToImage = url;
        this.headerDesk = headerDesk;
        this.headerTag = headerTag;
    }
    ModelItem(String headerAuthor, int imgIdHeader, String headerTag, String headerDesk){
        this.headerAuthor = headerAuthor;
        this.imgIdHeader = imgIdHeader;
        this.headerDesk = headerDesk;
        this.headerTag = headerTag;
    }


    int getImgIdHeader() {return imgIdHeader;}
    String getHeaderTag() {return headerTag;}
    String getHeaderAuthor() {return headerAuthor;}
    String getHeaderDesk() {return headerDesk;}
    String getUrlToImage() {return urlToImage;}

    int getImgId(){
        return imgId;
    }
    String getAuthor(){
        return author;
    }

}
