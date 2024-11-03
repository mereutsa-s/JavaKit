package regular.ls3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDatabase<T> implements DatabaseOperations<T> {
    private final Map<Integer, T> database = new HashMap<>();
    private int currentId = 1;

    @Override
    public void save(T entity) {
        database.put(currentId++, entity);
        System.out.println("Saved: " + entity);
    }

    @Override
    public void delete(int id) {
        if (database.remove(id) != null) {
            System.out.println("Deleted ID: " + id);
        } else {
            System.out.println("No entity found with ID: " + id);
        }
    }

    @Override
    public T findById(int id) {
        return database.get(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(database.values());
    }
}