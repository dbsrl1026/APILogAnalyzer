package test.java.analysis;

import org.junit.Before;
import org.junit.Test;

import main.java.analysis.AnalysisTopServiceID;
import main.java.reader.LogData;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;

public class AnalysisTopServiceIDTest {

    private List<LogData> logDataList;

    @Before
    public void setUp() throws Exception {
        logDataList = Arrays.asList(
                new LogData("200", "Chrome", LocalDateTime.now(), "Service1", "APIKey1", "Search1"),
                new LogData("200", "Firefox", LocalDateTime.now(), "Service2", "APIKey2", "Search2"),
                new LogData("200", "Safari", LocalDateTime.now(), "Service1", "APIKey3", "Search3"),
                new LogData("200", "Chrome", LocalDateTime.now(), "Service3", "APIKey4", "Search4"),
                new LogData("200", "Firefox", LocalDateTime.now(), "Service2", "APIKey5", "Search5"),
                new LogData("200", "Chrome", LocalDateTime.now(), "Service1", "APIKey6", "Search6"),
                new LogData("200", "Safari", LocalDateTime.now(), "Service3", "APIKey7", "Search7"),
                new LogData("200", "Chrome", LocalDateTime.now(), "Service2", "APIKey8", "Search8"),
                new LogData("200", "Firefox", LocalDateTime.now(), "Service3", "APIKey9", "Search9"),
                new LogData("200", "Chrome", LocalDateTime.now(), "Service1", "APIKey10", "Search10"),
                new LogData("200", "Chrome", LocalDateTime.now(), "Service4", "APIKey11", "Search11"));
    }

    @Test
    public void testFindTopThreeApiServiceUsage() {
        AnalysisTopServiceID analysisTopServiceID = new AnalysisTopServiceID();
        List<SimpleEntry<String, Long>> result = analysisTopServiceID.findTopThreeApiServiceUsage(logDataList);

        assertEquals(3, result.size());
        assertEquals(new SimpleEntry<>("Service1", 4L), result.get(0));
        assertEquals(new SimpleEntry<>("Service2", 3L), result.get(1));
        assertEquals(new SimpleEntry<>("Service3", 3L), result.get(2));
    }
}