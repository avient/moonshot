package utils;

import infrastructure.Configuration;
import infrastructure.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Downloader {
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
        Logger.getInstance().info(String.format("Downloading file by URL: %s", urlStr));
        try {
            URL url = new URL(urlStr);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(DownloadsDirectory + File.separator + fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            Logger.getInstance().info("File downloading finished");
        } catch (IOException e) {
            Logger.getInstance().error(e.getMessage());
            e.printStackTrace();
        }
    }

}
