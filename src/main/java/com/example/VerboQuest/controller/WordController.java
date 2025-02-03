package com.example.VerboQuest.controller;

import com.example.VerboQuest.pojo.ResponseMessage;
import com.example.VerboQuest.pojo.Word;
import com.example.VerboQuest.pojo.dto.WordDto;
import com.example.VerboQuest.service.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word")
public class WordController {
    @Autowired
    IWordService wordService;

    @PostMapping
    public ResponseMessage<Word> add(@Validated @RequestBody WordDto word) {
        Word wordNew = wordService.add(word);
        return ResponseMessage.success(wordNew);
    }
}
