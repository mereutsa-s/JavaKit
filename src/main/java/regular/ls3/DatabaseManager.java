package regular.ls3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager implements DatabaseOperations {
    // Временное хранилище данных, имитирующее базу данных
    private Map<Integer, Object> database;
    private int currentId;

    public DatabaseManager() {
        database = new HashMap<>();
        currentId = 1; // Начинаем с 1
    }

    @Override
    public void save(Object data) {
        // Сохраняем данные с уникальным идентификатором
        database.put(currentId++, data);
        System.out.println("Data saved: " + data);
    }

    @Override
    public void delete(int id) {
        // Удаляем данные по идентификатору
        if (database.containsKey(id)) {
            database.remove(id);
            System.out.println("Data with ID " + id + " deleted.");
        } else {
            System.out.println("No data found with ID " + id);
        }
    }

    @Override
    public Object get(int id) {
        // Получаем данные по идентификатору
        return database.get(id);
    }

    @Override
    public List<Object> getAll() {
        // Получаем все данные
        return new ArrayList<>(database.values());
    }
}