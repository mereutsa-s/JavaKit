import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedDownloader {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрос URL для загрузки
        System.out.print("Введите URL файла для загрузки: ");
        String fileURL = scanner.nextLine();

        // Запрос количества потоков
        System.out.print("Введите количество потоков для загрузки: ");
        int threadCount = scanner.nextInt();

        // Запускаем загрузку
        downloadFile(fileURL, threadCount);
    }

    private static void downloadFile(String fileURL, int threadCount) {
        try {
            URL url = new URL(fileURL);
            URLConnection connection = url.openConnection();
            int fileSize = connection.getContentLength();
            System.out.println("Размер файла: " + fileSize + " байт");

            // Размер блока для загрузки
            int blockSize = fileSize / threadCount;

            // Создание пула потоков
            ExecutorService executor = Executors.newFixedThreadPool(threadCount);

            for (int i = 0; i < threadCount; i++) {
                final int start = i * blockSize;
                final int end = (i == threadCount - 1) ? fileSize : start + blockSize;

                executor.submit(() -> {
                    try {
                        downloadPart(fileURL, start, end);
                    } catch (Exception e) {
                        System.err.println("Ошибка при загрузке части файла: " + e.getMessage());
                    }
                });
            }

            executor.shutdown();
            while (!executor.isTerminated()) {
                // Ожидание завершения всех потоков
            }
            System.out.println("Загрузка завершена!");

        } catch (Exception e) {
            System.err.println("Ошибка загрузки файла: " + e.getMessage());
        }
    }

    private static void downloadPart(String fileURL, int start, int end) throws Exception {
        URL url = new URL(fileURL);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Range", "bytes=" + start + "-" + (end - 1));

        try (InputStream in = new BufferedInputStream(connection.getInputStream());
             FileOutputStream out = new FileOutputStream("downloaded_part_" + start)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}