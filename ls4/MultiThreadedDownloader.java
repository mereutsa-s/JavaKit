package regular.ls4;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedDownloader {

    private static class DownloadTask implements Runnable {
        private final String fileUrl;
        private final String destination;

        public DownloadTask(String fileUrl, String destination) {
            this.fileUrl = fileUrl;
            this.destination = destination;
        }

        @Override
        public void run() {
            try (InputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
                 FileOutputStream out = new FileOutputStream(destination)) {
                byte[] data = new byte[1024];
                int count;
                while ((count = in.read(data, 0, data.length)) != -1) {
                    out.write(data, 0, count);
                }
                System.out.println("Downloaded: " + destination);
            } catch (IOException e) {
                System.err.println("Error downloading " + fileUrl + ": " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите URL файла для загрузки: ");
        String fileUrl = scanner.nextLine();

        System.out.print("Введите путь к сохранению файла (например, файл.zip): ");
        String destination = scanner.nextLine();

        System.out.print("Введите количество потоков для загрузки: ");
        int numberOfThreads = scanner.nextInt();

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        executorService.submit(new DownloadTask(fileUrl, destination));

        executorService.shutdown();
        System.out.println("Файл загружается...");

        scanner.close();
    }
}