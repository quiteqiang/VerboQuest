package com.example.VerboQuest.service;

import com.example.VerboQuest.pojo.ResponseMessage;
import com.example.VerboQuest.pojo.Word;
import com.example.VerboQuest.pojo.dto.WordDto;
import com.example.VerboQuest.repository.WordRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<Word> getRandomWords(Integer num) {

        List<Word> allWords = (List<Word>) wordRepository.findAll();
        int min = 1;
        int max = allWords.size();
        int range = max - min + 1;   // TODO : hack trick here

        List<Word> ans = new ArrayList<>();
        for (int i =0; i<num; i++) {
            int rand = (int)(Math.random() * range) + min;
            ans.add(allWords.get(rand));
        }
        return ans;
    }

    @Override
    public Word getFilteredRandomWord(Integer[] nums) {

        List<Word> allWords = (List<Word>) wordRepository.findAll();
        int min = 1;
        int max = allWords.size();
        int range = max - min + 1;   // TODO : hack trick here

        Word ans = new Word();
        int tryCount = 5;
        for (int i =0; i<tryCount; i++) {
            int rand = (int)(Math.random() * range) + min;
            for (int n : nums) {
                if (n ==rand) continue;
                else ans = allWords.get(rand);
            }
        }
        return ans;
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

    @Override
    public void update(Word word) {
         wordRepository.save(word);
    }
}
