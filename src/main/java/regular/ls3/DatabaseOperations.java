package regular.ls3;

import java.util.List;

public interface DatabaseOperations {
    // Метод для сохранения данных
    void save(Object data);

    // Метод для удаления данных по идентификатору
    void delete(int id);

    // Метод для получения данных по идентификатору
    Object get(int id);

    // Метод для получения всех данных
    List<Object> getAll();
}