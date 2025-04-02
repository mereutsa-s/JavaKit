package regular.ls5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class ChatClient {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;
    private PrintWriter out;
    private BufferedReader in;
    private File historyFile = new File("chat_history.txt");

    public ChatClient() {
        frame = new JFrame("Chat Client");
        textArea = new JTextArea();
        textField = new JTextField();

        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        loadHistory();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        frame.setVisible(true);

        // Запуск в отдельном потоке, чтобы не блокировать UI
        new Thread(() -> connectToServer()).start();
    }

    private void loadHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(historyFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            textArea.append("Не удалось загрузить историю сообщений.\n");
        }
    }

    private void sendMessage() {
        String message = textField.getText();
        if (!message.trim().isEmpty()) {
            textArea.append("Вы: " + message + "\n");
            logMessage("Вы: " + message);
            out.println(message);
            textField.setText("");
        }
    }

    private void logMessage(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            textArea.append("Ошибка при сохранении сообщения: " + message + "\n");
        }
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 12345); // Замените на адрес сервера
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String receivedMessage;
            while ((receivedMessage = in.readLine()) != null) {
                textArea.append("Сервер: " + receivedMessage + "\n");
                logMessage("Сервер: " + receivedMessage);
            }
        } catch (IOException e) {
            textArea.append("Ошибка соединения с сервером.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatClient::new);
    }
}