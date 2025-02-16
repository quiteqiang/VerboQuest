package com.example.VerboQuest.controller;

import com.example.VerboQuest.pojo.ResponseMessage;
import com.example.VerboQuest.pojo.Word;
import com.example.VerboQuest.service.IWordService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ai.chat.client.ChatClient;

import java.util.*;

@RestController
@RequestMapping("/ai")
public class ChatController {

    @Resource
    private OpenAiChatModel chatModel;

    @Autowired
    IWordService wordService;

    private final List<Message> chatHistoryList = new ArrayList<>();

    /**
     * Test API for DeepSeek chat
     * */
    @GetMapping("/chat")
    public ChatResponse test(String message) {
        chatHistoryList.add(new UserMessage(message));
        Prompt prompt = new Prompt(chatHistoryList);
        ChatResponse chatResponse = chatModel.call(prompt);
        if (chatResponse.getResult() != null && chatResponse.getResult().getOutput() != null) {
            chatHistoryList.add(chatResponse.getResult().getOutput());
        }
        return chatResponse;
    }

    /**
     * Generate word definition & sample sentence
     * */
    @GetMapping("/{id}/aiGen")
    public ResponseMessage<Word> aiGen(String message, @PathVariable Integer id) {
//        System.out.println("id   " + id + "  msg.  " + message);
        String word = "Use completely new simple english to explain " + message + "including: \n1.word definition\n2. sample sentence" ;
        chatHistoryList.add(new UserMessage(word));
        Prompt prompt = new Prompt(chatHistoryList);
        ChatResponse chatResponse = chatModel.call(prompt);
        if (chatResponse.getResult() != null && chatResponse.getResult().getOutput() != null) {
            chatHistoryList.add(chatResponse.getResult().getOutput());
        }

        /**     Sample structure
         *        String sampleResponse = "**1. Word Definition:**\n" +
         *                "\"Lynch\" means to kill someone, usually by hanging, without a legal trial. This is often done by a group of people who take the law into their own hands.\n" +
         *                "\n" +
         *                "**2. Sample Sentence:**\n" +
         *                "- In the past, some people were lynched because others believed they had committed a crime, even though there was no proof.";
         *
         * */

        List<String> items = Arrays.asList(chatResponse.getResult().getOutput().getText().split("[\\r\\n]+"));
        String definition = items.get(1);
        String sentence = items.get(3);
        Word wordObj = wordService.getWord(id);
        wordObj.setDefinition(definition);
        wordObj.setSentence(sentence);
        // 需要吧 definition 和 sentence 分出来 -> path to DB
        wordService.update(wordObj);

        return ResponseMessage.success(wordObj);
    }



}
