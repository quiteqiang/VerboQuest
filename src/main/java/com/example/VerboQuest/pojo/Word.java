package com.example.VerboQuest.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.sql.Timestamp;

@Table(name="tb_word")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="word_id")
    private Integer wordId;

    @Column(name="word")
    private String word;

    @CreationTimestamp
    @Column(name="time_created")
    private Timestamp timeCreated;

    @Column(name="definition")
    private String definition;

    @Column(name="sentence")
    private String sentence;

    @Override
    public String toString() {
        return "Word{" +
                "wordId=" + wordId +
                ", word='" + word + '\'' +
                ", timeCreated=" + timeCreated +
                ", definition='" + definition + '\'' +
                ", sentence='" + sentence + '\'' +
                ", known=" + known +
                ", forgot=" + forgot +
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

    @Column(name="known")
    private Integer known=0;

    @Column(name="forgot")
    private Integer forgot=0;

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }
}
