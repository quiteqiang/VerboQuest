package com.example.VerboQuest.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;

import java.util.*;

@RestController
@RequestMapping("/ai")
public class ChatController {

    @Resource
    private OpenAiChatModel chatModel;

    private final List<Message> chatHistoryList = new ArrayList<>();

    /**
     * Test API for DeepSeek chat
     * */
    @GetMapping("/chat")
    public ChatResponse test(String message) {
        chatHistoryList.add(new UserMessage(message));
        Prompt prompt = new Prompt(chatHistoryList);
        System.out.println("postman:   "  + message);
        ChatResponse chatResponse = chatModel.call(prompt);
        if (chatResponse.getResult() != null && chatResponse.getResult().getOutput() != null) {
            chatHistoryList.add(chatResponse.getResult().getOutput());
        }
        System.out.println(chatResponse.getResult().getOutput().getText());
        System.out.println("------------------------");
        System.out.println(chatResponse.getMetadata());
        return chatResponse;
    }

    @GetMapping("/aiGen")
    public ChatResponse aiGen(String message) {
        String word = "Use simple english to explain " + message + "including: \n1.word definition\n2. sample sentence" ;
        chatHistoryList.add(new UserMessage(word));
        Prompt prompt = new Prompt(chatHistoryList);
        System.out.println("postman:   "  + message);
        ChatResponse chatResponse = chatModel.call(prompt);
        if (chatResponse.getResult() != null && chatResponse.getResult().getOutput() != null) {
            chatHistoryList.add(chatResponse.getResult().getOutput());
        }
        System.out.println(chatResponse.getResult().getOutput().getText());

//        String sampleResponse = "**1. Word Definition:**\n" +
//                "\"Lynch\" means to kill someone, usually by hanging, without a legal trial. This is often done by a group of people who take the law into their own hands.\n" +
//                "\n" +
//                "**2. Sample Sentence:**\n" +
//                "- In the past, some people were lynched because others believed they had committed a crime, even though there was no proof.";
        System.out.println("------------Response spliting----------");
        List<String> items = Arrays.asList(chatResponse.getResult().getOutput().getText().split("[\\r\\n]+"));
        String definition = items.get(1);
        String sentence = items.get(3);
        System.out.println(definition);
        System.out.println("----------------------");
        System.out.println(sentence);
        // 需要吧 definition 和 sentence 风出来
        return new ChatResponse(new List<Generation>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Generation> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Generation generation) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Generation> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Generation> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Generation get(int index) {
                return null;
            }

            @Override
            public Generation set(int index, Generation element) {
                return null;
            }

            @Override
            public void add(int index, Generation element) {

            }

            @Override
            public Generation remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Generation> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Generation> listIterator(int index) {
                return null;
            }

            @Override
            public List<Generation> subList(int fromIndex, int toIndex) {
                return List.of();
            }
        });
    }



}
