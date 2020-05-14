package dto.model;

import dataacces.Entity;

import java.util.Date;

public class Token extends Entity {
    private String tokenText;

    private Date creationDate;

    //Time to live -> how many seconds is this token valid?
    private int timeToLive;

    private long playerId;

    public Token(String tokenText, Date creationDate, int timeToLive, long playerId) {
        this.tokenText = tokenText;
        this.creationDate = creationDate;
        this.timeToLive = timeToLive;
        this.playerId = playerId;
    }

    public String getTokenText() {
        return tokenText;
    }

    public void setTokenText(String tokenText) {
        this.tokenText = tokenText;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
}
