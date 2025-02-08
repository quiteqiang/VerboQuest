package com.example.VerboQuest.service;

import com.example.VerboQuest.pojo.User;
import com.example.VerboQuest.pojo.Word;
import com.example.VerboQuest.pojo.dto.WordDto;

import java.util.List;

public interface IWordService {
    /**
     * Add word
     * @param word
     * */
    Word add(WordDto word);

    /**
     * Get a word
     * @param wordId
     * */
    Word getWord(Integer wordId);

    /**
     * Delete word
     *
     * @param wordId
     */
    void delete(Integer wordId);

    List<Word> getRandomWords(Integer num);


}
