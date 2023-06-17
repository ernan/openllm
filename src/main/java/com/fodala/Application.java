package com.fodala;

import com.fodala.service.DataLoader;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatMessage;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private DataLoader dataLoader;

    public static void main(String[] args) {
//        ChatGPT chatGPT = ChatGPT.builder()
//                .apiKey("")
//                .build()
//                .init();
//
//        String res = chatGPT.chat("hello！");
//        System.out.println(res);
        logger.info("Running application.");

        SpringApplication.run(Application.class, args);
    }
    @Autowired
    private Environment env;

    @Autowired
    private ChatgptService chatgptService;

    @Override
    public void run(String... args) throws Exception {
        dataLoader.loadData("data/schema.sql");
        dataLoader.loadData("data/data.sql");
    }


    public void test(){
        String responseMessage = chatgptService.multiChat(Arrays.asList(new MultiChatMessage("user","how are you?")));
        System.out.print(responseMessage); //\n\nAs an AI language model, I don't have feelings, but I'm functioning well. Thank you for asking. How can I assist you today?
    }
}
