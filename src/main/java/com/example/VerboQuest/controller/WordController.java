package com.example.VerboQuest.controller;

import com.example.VerboQuest.pojo.ResponseMessage;
import com.example.VerboQuest.pojo.User;
import com.example.VerboQuest.pojo.Word;
import com.example.VerboQuest.pojo.dto.WordDto;
import com.example.VerboQuest.service.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    //查询
    @GetMapping("/{wordId}")        // localhost:8088/user/1
    public ResponseMessage get(@PathVariable Integer wordId) {
        Word wordNew = wordService.getWord(wordId);
        return ResponseMessage.success(wordNew);
    }

    //删除
    @DeleteMapping("/{wordId}")        // localhost:8088/user/1
    public ResponseMessage delete(@PathVariable Integer wordId) {
        wordService.delete(wordId);
        return ResponseMessage.success();
    }
}
