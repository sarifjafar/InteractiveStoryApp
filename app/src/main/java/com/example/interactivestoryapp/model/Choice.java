package com.example.interactivestoryapp.model;

public class Choice {
    private int textId;
    private int nextPageId;

    public Choice(int textId, int nextPageId) {
        this.textId = textId;
        this.nextPageId = nextPageId;
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getNextPageId() {
        return nextPageId;
    }

    public void setNextPageId(int nextPageId) {
        this.nextPageId = nextPageId;
    }
}
