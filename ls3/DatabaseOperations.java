package regular.ls3;

import java.util.List;

public interface DatabaseOperations<T> {
    void save(T entity);        // Метод для сохранения объекта
    void delete(int id);        // Метод для удаления объекта по ID
    T findById(int id);         // Метод для получения объекта по ID
    List<T> findAll();          // Метод для получения всех объектов
}