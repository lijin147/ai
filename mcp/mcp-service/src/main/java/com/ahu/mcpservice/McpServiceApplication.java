package com.ahu.mcpservice;


import com.ahu.mcpservice.service.DateService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class McpServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServiceApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider weatherTools(DateService dateService){
        return MethodToolCallbackProvider.builder().toolObjects(dateService).build();
    }
}
