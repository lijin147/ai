package com.ahu.mcpclient;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class McpClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpClientApplication.class, args);
    }


//    @Bean
//    ChatClient chatClient(ChatModel chatModel, SyncMcpToolCallbackProvider syncMcpToolCallbackProvider) {
//        return ChatClient
//                .builder(chatModel)
//                .defaultTools(syncMcpToolCallbackProvider.getToolCallbacks())
//                .build();
//    }
}
