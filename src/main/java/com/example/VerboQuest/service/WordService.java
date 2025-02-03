package com.example.VerboQuest.service;

import com.example.VerboQuest.pojo.User;
import com.example.VerboQuest.pojo.Word;
import com.example.VerboQuest.pojo.dto.WordDto;
import com.example.VerboQuest.repository.WordRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService implements IWordService{

    @Autowired
    WordRepository wordRepository;

    @Override
    public Word getWord(Integer wordId) {
        return wordRepository.findById(wordId).orElseThrow( () -> {
            throw new IllegalArgumentException("Word doesn't exist, invalid parameter");
        });
    }

    @Override
    public Word add(WordDto word) {

        Word wordPojo = new Word();

        BeanUtils.copyProperties(word, wordPojo);

        //调用数据访问类的方法
        return wordRepository.save(wordPojo);
    }

    @Override
    public void delete(Integer wordId) {
        wordRepository.deleteById(wordId);
    }
}
