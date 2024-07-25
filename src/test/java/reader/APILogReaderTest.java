package test.java.reader;

import static org.junit.Assert.*;
import org.junit.Test;
import main.java.reader.APILogReader;
import main.java.reader.LogData;

import java.time.LocalDateTime;
import java.util.List;

public class APILogReaderTest {
    @Test
    public void testReadLogAndParse() {
        String filePath = "src/test/resource/";
        String fileName = "testInput.log";
        String fullPath = filePath + fileName;

        APILogReader apiLogReader = new APILogReader();

        // 로그 파일을 읽고 파싱한 결과를 가져옴
        List<LogData> result = apiLogReader.readLogAndParse(fullPath);

        // 결과가 null이 아닌지 확인
        assertNotNull(result);

        // 성공적으로 파싱된 라인의 수를 확인
        assertEquals(2, result.size());

        // 첫 번째 라인의 데이터를 확인
        LogData firstLine = result.get(0);
        assertEquals("200", firstLine.getStatusCode());
        assertEquals("Firefox", firstLine.getBrowser());
        assertEquals(LocalDateTime.of(2012, 6, 10, 8, 0, 11), firstLine.getCallTime());
        assertEquals("news", firstLine.getApiServiceId());
        assertEquals("23jf", firstLine.getApiKey());
        assertEquals("daum", firstLine.getSearchWord());
    }
}
