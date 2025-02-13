package com.example.VerboQuest.controller;

import com.example.VerboQuest.pojo.ResponseMessage;
import com.example.VerboQuest.pojo.User;
import com.example.VerboQuest.pojo.Word;
import com.example.VerboQuest.pojo.dto.WordDto;
import com.example.VerboQuest.service.IWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/word")
@CrossOrigin
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

    /**
     * Delete multiple
     * */
    @PostMapping("/{deleteBatchWord}")
    public ResponseMessage deleteBatchWord(@RequestBody Integer[] ids) {
        // 暂时直接删除，不做response 处理
        for (Integer id: ids) {
            wordService.delete(id);
        }
        return ResponseMessage.success();
    }

    @GetMapping("/random/{num}")
    public ResponseMessage<List<Word>> fetchRandomWords(@PathVariable Integer num) {
        // Generate random
        List<Word> ans = wordService.getRandomWords(num);
        return ResponseMessage.success(ans);
    }

    @PostMapping("/nextRandom/")
    public ResponseMessage<Word> nextWord(@RequestBody Integer[] ids) {
        // Generate random
        Word ans = wordService.getFilteredRandomWord(ids);
        return ResponseMessage.success(ans);
    }

    @PatchMapping("/known/{wordId}")
    public ResponseMessage addKnown(@PathVariable Integer wordId) {
        // 暂时直接删除，不做response 处理
        Word word = wordService.getWord(wordId);
        word.setKnown(word.getKnown()+1);
        wordService.update(word);
        return ResponseMessage.success();
    }

    @PatchMapping("/forgot/{wordId}")
    public ResponseMessage addForgot(@PathVariable Integer wordId) {
        // 暂时直接删除，不做response 处理
        Word word = wordService.getWord(wordId);
        word.setForgot(word.getForgot()+1);
        wordService.update(word);
        return ResponseMessage.success();
    }

    /**
     * /TODO: 增加 / 修改word
     */
//    @PatchMapping("/{updateWords}")
//    public ResponseMessage updateWords (@Validated @RequestBody WordDto word) {
//
//    }
}
