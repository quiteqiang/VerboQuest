package com.example.VerboQuest.service;

import com.example.VerboQuest.pojo.Word;
import com.example.VerboQuest.pojo.dto.WordDto;

public interface IWordService {
    /**
     * Add word
     * @param word
     * */
    Word add(WordDto word);
}
