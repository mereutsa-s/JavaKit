package regular.ls5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PhoneBookApp extends Application {
    private ArrayList<Contact> contacts;
    private ListView<String> contactListView;
    private TextField nameInput;
    private TextField phoneInput;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        contacts = new ArrayList<>();

        primaryStage.setTitle("Телефонный справочник");

        nameInput = new TextField();
        nameInput.setPromptText("Введите имя контакта");

        phoneInput = new TextField();
        phoneInput.setPromptText("Введите номер телефона");

        Button addButton = new Button("Добавить контакт");
        addButton.setOnAction(e -> addContact());

        contactListView = new ListView<>();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(nameInput, phoneInput, addButton, contactListView);

        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addContact() {
        String name = nameInput.getText();
        String phone = phoneInput.getText();
        if (!name.isEmpty() && !phone.isEmpty()) {
            contacts.add(new Contact(name, phone));
            updateContactList();
            nameInput.clear();
            phoneInput.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Пожалуйста, заполните все поля.");
            alert.show();
        }
    }

    private void updateContactList() {
        contactListView.getItems().clear();
        for (Contact contact : contacts) {
            contactListView.getItems().add(contact.getName() + ": " + contact.getPhone());
        }
    }

    private static class Contact {
        private String name;
        private String phone;

        public Contact(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }
    }
}