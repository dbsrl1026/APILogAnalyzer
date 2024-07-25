package main.java.writer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.List;

import java.util.AbstractMap.SimpleEntry;

public class ReportFileWriter {

    public void writeReport(String filePath, String mostUsedApiKey,
            List<SimpleEntry<String, Long>> topThreeApiServiceUsage,
            List<SimpleEntry<String, Double>> browserUsagePercentage) {
        // BufferedWriter를 OutputStreamWriter와 FileOutputStream을 사용해 생성
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"))) {
                    
            // 가장 많이 사용된 API Key 출력
            writer.write("최다 호출 API KEY\n" + mostUsedApiKey);
            writer.newLine();
            writer.newLine();

            // 가장 많이 사용된 상위 3개의 apiServiceId와 각각의 요청수 출력
            writer.write("상위 3개의 API Service ID와 각각의 요청 수");
            writer.newLine();
            for (SimpleEntry<String, Long> entry : topThreeApiServiceUsage) {
                writer.write(entry.getKey() + " : " + entry.getValue());
                writer.newLine();
            }
            writer.newLine();

            // 브라우저 사용 비율 출력
            writer.write("웹브라우저별 사용 비율");
            writer.newLine();
            for (SimpleEntry<String, Double> entry : browserUsagePercentage) {
                writer.write(String.format("%s : %.2f%%", entry.getKey(), entry.getValue()));
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}