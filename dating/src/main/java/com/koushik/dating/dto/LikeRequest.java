package com.koushik.dating.dto;

public class LikeRequest {

    private Integer likerId;
    private Integer likedUserId;

    public LikeRequest() {
    }

    public Integer getLikerId() {
        return likerId;
    }

    public void setLikerId(Integer likerId) {
        this.likerId = likerId;
    }

    public Integer getLikedUserId() {
        return likedUserId;
    }

    public void setLikedUserId(Integer likedUserId) {
        this.likedUserId = likedUserId;
    }
}