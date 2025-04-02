package regular.ls3;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        // Сохранение данных
        dbManager.save("First entry");
        dbManager.save("Second entry");

        // Получение данных
        System.out.println("Get Entry 1: " + dbManager.get(1));
        System.out.println("Get all entries: " + dbManager.getAll());

        // Удаление данных
        dbManager.delete(1);
        System.out.println("Get all entries after deletion: " + dbManager.getAll());
    }
}