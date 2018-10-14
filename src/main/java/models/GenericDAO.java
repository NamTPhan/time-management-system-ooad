package models;

import java.util.List;

public interface GenericDAO<T> {

    List<T> getAll() throws DAOException;

    T getByIndex(int index) throws DAOException;

    void save(T t) throws DAOException;

    void update(int index, T t) throws DAOException;

    void delete(int index, T t) throws DAOException;

    void deleteAll() throws DAOException;
}
