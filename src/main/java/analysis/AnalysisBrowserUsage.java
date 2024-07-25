package main.java.analysis;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.java.reader.LogData;

public class AnalysisBrowserUsage {
    // LogData 목록으로 부터 각 브라우저의 요청 비율 집계
    public List<SimpleEntry<String, Double>> calculateBrowserUsagePercentage(List<LogData> logDataList) {
        double totalLogs = logDataList.size();

        // 브라우저별 로그 데이터 개수 집계
        Map<String, Long> browserCounts = logDataList.stream()
                .collect(Collectors.groupingBy(LogData::getBrowser, Collectors.counting()));

        // 브라우저별 사용 비율 계산 (백분율 형태)
        List<SimpleEntry<String, Double>> browserUsagePercentage = new ArrayList<>();
        browserCounts.forEach((browser, count) -> {
            double percentage = (count / totalLogs) * 100;
            browserUsagePercentage.add(new SimpleEntry<>(browser, percentage));
        });

        // 비율이 가장 높은 순으로 내림차순 정렬
        browserUsagePercentage.sort(Comparator.comparing(SimpleEntry<String, Double>::getValue).reversed());

        return browserUsagePercentage;
    }
}