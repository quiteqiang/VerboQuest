package com.example.VerboQuest.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;

public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="word_id")
    private Integer wordId;

    @Column(name="word")
    private Integer word;

    @CreatedDate
    @Column(name="time_created")
    private Date timeCreated;

    @Override
    public String toString() {
        return "Word{" +
                "wordId=" + wordId +
                ", word=" + word +
                ", timeCreated=" + timeCreated +
                '}';
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public Integer getWord() {
        return word;
    }

    public void setWord(Integer word) {
        this.word = word;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
