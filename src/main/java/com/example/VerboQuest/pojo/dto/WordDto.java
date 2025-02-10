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
    private String definition;
    private Integer known;
    private Integer forgot;

    @Override
    public String toString() {
        return "WordDto{" +
                "wordId=" + wordId +
                ", word='" + word + '\'' +
                ", timeCreated=" + timeCreated +
                ", definition='" + definition + '\'' +
                ", known=" + known +
                ", forgot=" + forgot +
                ", sentence='" + sentence + '\'' +
                '}';
    }

    public Integer getKnown() {
        return known;
    }

    public void setKnown(Integer known) {
        this.known = known;
    }

    public Integer getForgot() {
        return forgot;
    }

    public void setForgot(Integer forgot) {
        this.forgot = forgot;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    private String sentence;

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
