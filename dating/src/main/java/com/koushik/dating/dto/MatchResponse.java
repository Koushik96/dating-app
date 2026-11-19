package com.koushik.dating.dto;

public class MatchResponse {

    private Integer matchId;
    private Integer userId;
    private String name;
    private String email;

    public MatchResponse() {
    }

    public MatchResponse(Integer matchId, Integer userId, String name, String email) {
        this.matchId = matchId;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}