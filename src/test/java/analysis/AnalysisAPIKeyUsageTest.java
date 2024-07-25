package test.java.analysis;

import main.java.analysis.AnalysisAPIKeyUsage;
import main.java.reader.LogData;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnalysisAPIKeyUsageTest {

    private AnalysisAPIKeyUsage analysisAPIKeyUsage;

    @Before
    public void setUp() {
        analysisAPIKeyUsage = new AnalysisAPIKeyUsage();
    }

    @Test
    public void testFindMostUsedApiKey() {
        List<LogData> logDataList = Arrays.asList(
                new LogData("200", "Chrome", LocalDateTime.now(), "service1", "apiKey1", "searchWord1"),
                new LogData("200", "Firefox", LocalDateTime.now(), "service2", "apiKey2", "searchWord2"),
                new LogData("200", "Chrome", LocalDateTime.now(), "service3", "apiKey1", "searchWord3"),
                new LogData("200", "Safari", LocalDateTime.now(), "service1", "apiKey3", "searchWord1"),
                new LogData("200", "Chrome", LocalDateTime.now(), "service2", "apiKey1", "searchWord4")
        );

        String result = analysisAPIKeyUsage.findMostUsedApiKey(logDataList);

        assertEquals("apiKey1", result);
    }
}
