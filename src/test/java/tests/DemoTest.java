package tests;

import utils.CsvParser;
import utils.DateUtil;
import utils.Downloader;
import org.testng.annotations.Test;
import utils.S3Util;
import web.BaseTest;
import web.forms.MainPage;
import web.forms.SourceDataPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DemoTest extends BaseTest {

    private final Downloader downloader = Downloader.getInstance();
    private final CsvParser csvParser = CsvParser.getInstance();
    private final DateUtil dateUtil = DateUtil.getInstance();
    private final S3Util s3Util = S3Util.getInstance();
    private final MainPage mainPage = new MainPage();
    private final SourceDataPage sourceDataPage = new SourceDataPage();

    @Test
    public void runTest() {
        String FILE_NAME = "owid-covid-data.csv";
        String RESULT_FILE_NAME = "owid-covid-data-results.csv";

        // Download file
        sourceDataPage.navigate();
        String linkUrl = sourceDataPage.getCsvLink();
        downloader.downloadUsingLink(linkUrl, FILE_NAME);

        // Parse to list
        List<List<String>> list = csvParser.parseToList(FILE_NAME);
        List<String> headers = list.get(0);

        // Sort, left only today records
        String today = dateUtil.getToday(); // 2020-06-10
        int dateIndex = headers.indexOf("date");
        List<List<String>> sortedList = list
                .stream()
                .filter(x -> x.size() > dateIndex)
                .filter(x -> x.get(dateIndex).equals(today))
                .collect(Collectors.toList());

        // Create new list with {"country", "newCases", "newDeaths"}
        List<List<String>> dataList = new ArrayList<>();
        String[] values = {"country", "newCases", "newDeaths"};
        dataList.add(Arrays.asList(values));
        for (List<String> c : sortedList) {
            String[] temp = {
                    c.get(headers.indexOf("location")),
                    c.get(headers.indexOf("new_cases")),
                    c.get(headers.indexOf("new_deaths"))
            };
            dataList.add(Arrays.asList(temp));
        }

        // Writing results to CSV
        csvParser.writeToFile(dataList, RESULT_FILE_NAME);

        // Upload file to S3
        s3Util.uploadToS3(RESULT_FILE_NAME);
    }
}
