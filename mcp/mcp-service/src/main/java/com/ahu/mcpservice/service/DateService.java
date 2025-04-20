package com.ahu.mcpservice.service;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateService {

    public static class AddressRequest {
        private String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class DateResponse {
        private String result;

        public DateResponse(String result) {
            this.result = result;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    @Tool(description = "获取指定地点的当前时间")
    public DateResponse getAddressDate(AddressRequest request) {
        String result = String.format("%s的当前时间是%s",
                request.getAddress(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return new DateResponse(result);
    }
}

