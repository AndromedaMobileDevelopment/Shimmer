package com.development.mobile.andromeda.shimmer;

class ModelItem {
    private String author;
    private int imgId;

    private String headerTag;
    private String headerDesk;
    private String headerAuthor;
    private int imgIdHeader;

    ModelItem(String author, int imgId){
        this.author = author;
        this.imgId = imgId;
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

    int getImgId(){
        return imgId;
    }
    String getAuthor(){
        return author;
    }

}
