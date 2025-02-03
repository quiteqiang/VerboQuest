package com.example.VerboQuest.pojo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;

public class WordDto {

    private Integer wordId;
    @NotBlank(message="Word can't be empty")
    private String word;
    private Date timeCreated;

    @Override
    public String toString() {
        return "WordDto{" +
                "wordId=" + wordId +
                ", word='" + word + '\'' +
                ", timeCreated=" + timeCreated +
                '}';
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public @NotBlank(message = "Word can't be empty") String getWord() {
        return word;
    }

    public void setWord(@NotBlank(message = "Word can't be empty") String word) {
        this.word = word;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
}
