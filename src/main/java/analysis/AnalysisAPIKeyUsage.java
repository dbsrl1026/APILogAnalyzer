package main.java.analysis;

import main.java.reader.LogData;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class AnalysisAPIKeyUsage {
        // LogData 객체의 리스트에서 apiKey를 추출하고, 가장 많이 사용된 api key 리턴
    public String findMostUsedApiKey(List<LogData> logDataList) {
       
        // apiKey 별로 요청수를 집계
        Map<String, Long> apiKeyCounts = logDataList.stream()
                .collect(Collectors.groupingBy(LogData::getApiKey, Collectors.counting())); // apiKey 별로 그룹화하고, 각 그룹의 개수를 카운팅

        return apiKeyCounts.entrySet().stream() // Optional 객체
                .max(Map.Entry.comparingByValue()) // 사용 횟수(value)가 가장 큰 entry 탐색
                .map(Map.Entry::getKey) // 해{당 entry에서 key(apiKey)만 추출
                .get();
    }
}