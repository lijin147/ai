package com.ahu.mcpclient;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class McpController {
    @Autowired
    private OpenAiChatModel openAiChatModel;
    @Autowired
    private ToolCallbackProvider tools;


    @PostMapping("/functionCallback")
    public ResponseEntity<String> functionCallback(@RequestParam String message) {

        try {
            SystemMessage systemMessage = new SystemMessage("你是一个助手,请用中文回答。");
            UserMessage userMessage = new UserMessage(message);

            FunctionCallback[] toolCallbacks = tools.getToolCallbacks();

            Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
            String response = ChatClient.builder(openAiChatModel)
                    .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                    .defaultTools(toolCallbacks)
                    .build()
                    .prompt(prompt)
                    .call().content();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("服务器错误: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }
}
