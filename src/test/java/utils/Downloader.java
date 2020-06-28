package utils;

import infrastructure.Configuration;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.BaseEntity;
import web.Browser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Downloader extends BaseEntity {
    private static Downloader instance = null;
    private static final String DownloadsDirectory = Configuration.getInstance().getProperty("tempDataDirectory");

    private Downloader() {
        File file = new File(DownloadsDirectory);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static synchronized Downloader getInstance() {
        if (instance == null) {
            instance = new Downloader();
        }
        return instance;
    }

    public void downloadUsingLink(String urlStr, String fileName) {
        logger.info(String.format("Downloading file by URL: %s", urlStr));
        try {
            URL url = new URL(urlStr);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(DownloadsDirectory + File.separator + fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            logger.info("File downloading finished");
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteFileIfPresent(String fileName) {
        File file = new File(DownloadsDirectory + File.separator + fileName);
        if (file.exists()) {
            logger.info(String.format("Deleting file '%s'", fileName));
            file.delete();
        }
    }

    public void waitForFileToBeDownloaded(String fileName) {
        logger.info(String.format("Waiting for file '%s' to be downloaded", fileName));
        Path filePath = Paths.get(DownloadsDirectory + File.separator + fileName);
        File file = filePath.toFile();
        WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), Integer.parseInt(browser.getTimeoutForCondition()));

        wait.until((webDriver) -> file.exists());
    }

}
