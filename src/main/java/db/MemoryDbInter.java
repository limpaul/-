package db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbInter <T>{
    T save(T contactVO);
    void deleteById(int id);
    void deleteByName(String name);
    Optional<T> findById(int id);
    List<T> findByName(String name);
    List<T> findAll();
}
