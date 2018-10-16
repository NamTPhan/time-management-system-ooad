package models;

import java.util.List;

public interface GenericDAO<T> {

    List<T> getAll() throws DAOException;

    T getByIndex(int index) throws DAOException;

    boolean save(T t) throws DAOException;

    boolean update(int index, T t) throws DAOException;

    boolean delete(int index, T t) throws DAOException;

    boolean deleteAll() throws DAOException;
}
