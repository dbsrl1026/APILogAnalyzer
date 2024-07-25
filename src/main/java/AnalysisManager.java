package main.java;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

import main.java.analysis.AnalysisAPIKeyUsage;
import main.java.analysis.AnalysisBrowserUsage;
import main.java.analysis.AnalysisTopServiceID;
import main.java.reader.APILogReader;
import main.java.reader.LogData;
import main.java.writer.ReportFileWriter;

public class AnalysisManager {
    public static void main(String[] args) {
        APILogReader apiLogReader = new APILogReader();
        // 파일 경로와 파일명을 별도로 관리
        String inputFilePath = "src/main/resource/";
        String inputFileName = "input.log";
        String inputFullPath = inputFilePath + inputFileName;

        List<LogData> parsedLogs = apiLogReader.readLogAndParse(inputFullPath);

         // parsedLogs가 비어있는 경우 프로그램 종료
         if (parsedLogs.isEmpty()) {
            System.err.println("로그 데이터가 비어있습니다. input.log 파일을 확인하세요.");
            return; 
        }

        // 가장 많이 호출된 api key 찾기
        AnalysisAPIKeyUsage analysisApiKeyUsage = new AnalysisAPIKeyUsage();
        String mostUsedApiKey = analysisApiKeyUsage.findMostUsedApiKey(parsedLogs);

        // 가장 많이 사용된 상위 3개의 apiServiceId와 각각의 요청수 찾기
        AnalysisTopServiceID analysisTopServiceID = new AnalysisTopServiceID();
        List<SimpleEntry<String, Long>> topThreeApiServiceUsage = analysisTopServiceID.findTopThreeApiServiceUsage(parsedLogs);

        // 브라우저별 요청 비율 계산
        AnalysisBrowserUsage analysisBrowserUsage = new AnalysisBrowserUsage();
        List<SimpleEntry<String, Double>> browserUsagePercentage = analysisBrowserUsage.calculateBrowserUsagePercentage(parsedLogs);

        // 결과 파일 경로 설정
        String outputFilePath = "src/main/resource/";
        String outputFileName = "output.log";
        String outputFullPath = outputFilePath + outputFileName;
        ReportFileWriter reportFileWriter = new ReportFileWriter();

        // 결과를 output.log 파일에 작성
        reportFileWriter.writeReport(outputFullPath, mostUsedApiKey, topThreeApiServiceUsage, browserUsagePercentage);
    }
}
