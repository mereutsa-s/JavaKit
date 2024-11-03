package regularfx.jkit;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PhoneBookApp extends Application {

    private ListView<String> contactsListView;
    private ObservableList<Contact> contacts;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Phone Book");

        contacts = FXCollections.observableArrayList();

        contactsListView = new ListView<>();
        contactsListView.setItems(FXCollections.observableArrayList());

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");

        Button addButton = new Button("Add Contact");
        addButton.setOnAction(e -> addContact(nameField.getText(), phoneField.getText()));

        GridPane inputLayout = new GridPane();
        inputLayout.setPadding(new Insets(10));
        inputLayout.setVgap(8);
        inputLayout.setHgap(10);
        inputLayout.add(new Label("Name:"), 0, 0);
        inputLayout.add(nameField, 1, 0);
        inputLayout.add(new Label("Phone Number:"), 0, 1);
        inputLayout.add(phoneField, 1, 1);
        inputLayout.add(addButton, 1, 2);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(contactsListView);
        mainLayout.setBottom(inputLayout);

        Scene scene = new Scene(mainLayout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addContact(String name, String phoneNumber) {
        if (!name.isEmpty() && !phoneNumber.isEmpty()) {
            Contact contact = new Contact(name, phoneNumber);
            contacts.add(contact);
            contactsListView.getItems().add(contact.getName() + ": " + contact.getPhoneNumber());
        }
    }
}