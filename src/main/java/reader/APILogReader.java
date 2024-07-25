package main.java.reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class APILogReader {
    public List<LogData> readLogAndParse(String fileName) {
        List<LogData> parsedLines = new ArrayList<>();
        
        // 파일 input. 상태코드 200일 때만 해당 line read
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("[200]")) {
                    parsedLines.add(parseLine(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return parsedLines;
    }

    private LogData parseLine(String line) {
        String[] parts = line.split("\\]\\[");
        // URL 분해
        String baseUrl = "http://apis.daum.net/search/";
        String apiServiceId = "";
        String apiKey = "";
        String searchWord = "";
        int apiServiceIdEndIndex = parts[1].indexOf("?");
        if (apiServiceIdEndIndex != -1) {
            apiServiceId = parts[1].substring(baseUrl.length(), apiServiceIdEndIndex);
        }
        String[] queryParams = parts[1].substring(apiServiceIdEndIndex + 1).split("&");
        for (String param : queryParams) {
            if (param.startsWith("apikey=")) {
                apiKey = param.substring(7);
            } else if (param.startsWith("q=")) {
                searchWord = param.substring(2);
            }
        }

        // 상태 코드, 웹브라우저 파싱
        String statusCode = parts[0].replace("[", "");
        String browser = parts[2];

        // 날짜와 시간 파싱
        String dateTimeString = parts[3].replace("]", "");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime callTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);

        return new LogData(statusCode, browser, callTime, apiServiceId, apiKey, searchWord);
    }
}

