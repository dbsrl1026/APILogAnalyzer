package test.java.analysis;

import org.junit.Test;

import main.java.analysis.AnalysisBrowserUsage;
import main.java.reader.LogData;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;

public class AnalysisBrowserUsageTest {

    @Test
    public void testCalculateBrowserUsagePercentage() {

        LogData log1 = new LogData("200", "Chrome", LocalDateTime.now(), "serviceId1", "apiKey1", "searchWord1");
        LogData log2 = new LogData("200", "Firefox", LocalDateTime.now(), "serviceId2", "apiKey2", "searchWord2");
        LogData log3 = new LogData("200", "Chrome", LocalDateTime.now(), "serviceId3", "apiKey3", "searchWord3");

        List<LogData> logs = Arrays.asList(log1, log2, log3);

        AnalysisBrowserUsage analysisBrowserUsage = new AnalysisBrowserUsage();
        List<SimpleEntry<String, Double>> result = analysisBrowserUsage.calculateBrowserUsagePercentage(logs);

        result.forEach(entry -> {
            if (entry.getKey().equals("Chrome")) {
                assertEquals(66.66666666666667, entry.getValue(), 0.001);
            } else if (entry.getKey().equals("Firefox")) {
                assertEquals(33.333333333333336, entry.getValue(), 0.001);
            }
        });
    }
}