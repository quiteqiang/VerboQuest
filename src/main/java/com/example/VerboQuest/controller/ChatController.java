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
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/ai")
@CrossOrigin
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
    public ResponseMessage<Word> aiGen(String message, @PathVariable Integer id) throws InterruptedException {
        String word = "Use completely new simple english to explain " + message + "including: \n1.word definition\n2. sample sentence\n\n Only give one Definition and one Sample Sentence";
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
        // Hard code for now
        String definition = items.get(1);
        String sentence = items.get(3);
        // Debug purpose only, definition logic needs enahce
//        for (String s : items) {
//            System.out.println(s);
//            System.out.println("---------------------");
//        }
        Word wordObj = wordService.getWord(id);
        wordObj.setDefinition(definition);
        wordObj.setSentence(sentence);
        // 需要吧 definition 和 sentence 分出来 -> path to DB
        wordService.update(wordObj);

//        Word wordObj = new Word();
//        wordObj.setSentence("- *Physical meaning:* \\\"She couldn't read the sign on the road because she was myopic and forgot her glasses.\\\"  ");
//        wordObj.setDefinition("\\\"Myopic\\\" means being unable to see things clearly when they are far away. It can also describe someone who only thinks about what is happening right now or in the near future, without considering the bigger picture or long-term effects.");
//        wordObj.setWordId(id);
//        wordObj.setWord(message);
//        TimeUnit.SECONDS.sleep((long)2.0);
//        return ResponseMessage.error();

        return ResponseMessage.success(wordObj);
    }



}
