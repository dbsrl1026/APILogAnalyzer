package main.java.analysis;

import main.java.reader.LogData;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.AbstractMap.SimpleEntry;

public class AnalysisTopServiceID {

    // LogData 목록으로부터 상위 3개의 가장 많이 사용된 apiServiceId와 각각의 요청수를 탐색
    public List<SimpleEntry<String, Long>> findTopThreeApiServiceUsage(List<LogData> logDataList) {

        // apiServiceId별로 요청수를 집계
        Map<String, Long> apiServiceCounts = logDataList.stream()
                .collect(Collectors.groupingBy(LogData::getApiServiceId, Collectors.counting()));// apiServiceId 별로 그룹화하고, 각 그룹의 개수를 카운팅

        // 요청수 기준으로 내림차순 정렬하고, 요청수가 같을 경우 apiServiceId 기준으로 사전순 정렬, 상위 3개 선택
        List<SimpleEntry<String, Long>> topThreeServices = apiServiceCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry::getKey))
                .limit(3)
                .map(entry -> new SimpleEntry<>(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return topThreeServices;
    }
}