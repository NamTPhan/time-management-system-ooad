package models;

import java.util.List;

public interface GenericDAO<T> {

    List<T> getAll();

    T getByIndex(int index);
    void save(T t);
    void update(int index, T t);
    void delete(int index, T t);
    void deleteAll();
}
