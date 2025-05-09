package dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
    boolean add(T object) throws  SQLException;
    List<T> getAll() throws SQLException;
    T getByID(Object id) throws SQLException;
    void update(T object) throws SQLException;
    void delete(T object) throws SQLException;
}
