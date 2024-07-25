package main.java.reader;

import java.time.LocalDateTime;

public class LogData {
    private String statusCode;
    private String browser;
    private LocalDateTime callTime;
    private String apiServiceId;
    private String apiKey;
    private String searchWord;

    // 생성자
    public LogData(String statusCode, String browser, LocalDateTime callTime, String apiServiceId, String apiKey, String searchWord) {
        this.statusCode = statusCode;
        this.browser = browser;
        this.callTime = callTime;
        this.apiServiceId = apiServiceId;
        this.apiKey = apiKey;
        this.searchWord = searchWord;
    }

    // getter 메소드들
    public String getStatusCode() {
        return statusCode;
    }

    public String getBrowser() {
        return browser;
    }

    public LocalDateTime getCallTime() {
        return callTime;
    }

    public String getApiServiceId() {
        return apiServiceId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getSearchWord() {
        return searchWord;
    }
}
