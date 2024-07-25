package test.java.writer;

import org.junit.Test;

import main.java.writer.ReportFileWriter;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

public class ReportFileWriterTest {

    @Test
    public void testWriteReport() throws IOException {
        // 임시 파일 경로 생성
        Path tempFile = Files.createTempFile("testOutput", ".log");

        // 테스트 데이터 준비
        String mostUsedApiKey = "testApiKey";
        List<SimpleEntry<String, Long>> topThreeApiServiceUsage = Arrays.asList(
                new SimpleEntry<>("serviceId1", 10L),
                new SimpleEntry<>("serviceId2", 8L),
                new SimpleEntry<>("serviceId3", 5L));
        List<SimpleEntry<String, Double>> browserUsagePercentage = Arrays.asList(
                new SimpleEntry<>("Chrome", 60.5),
                new SimpleEntry<>("Firefox", 30.2),
                new SimpleEntry<>("Safari", 9.3));

        // ReportFileWriter 인스턴스 생성 및 메소드 실행
        ReportFileWriter writer = new ReportFileWriter();
        writer.writeReport(tempFile.toString(), mostUsedApiKey, topThreeApiServiceUsage, browserUsagePercentage);

        // 파일 내용 읽기
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile.toFile()))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                contentBuilder.append(currentLine).append("\n");
            }
        }
        String content = contentBuilder.toString();

        // 검증
        assertTrue(content.contains("최다 호출 API KEY\n" + mostUsedApiKey));
        assertTrue(content.contains("상위 3개의 API Service ID와 각각의 요청 수"));
        assertTrue(content.contains("serviceId1 : 10"));
        assertTrue(content.contains("serviceId2 : 8"));
        assertTrue(content.contains("serviceId3 : 5"));
        assertTrue(content.contains("Chrome : 60.50%"));
        assertTrue(content.contains("Firefox : 30.20%"));
        assertTrue(content.contains("Safari : 9.30%"));

        // 임시 파일 삭제
        Files.delete(tempFile);
    }
}
