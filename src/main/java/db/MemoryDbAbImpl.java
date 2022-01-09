package db;

import dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class MemoryDbAbImpl<T extends User> implements MemoryDbInter<T>{
    private List<T> db = new ArrayList<>();
    private int index;
    @Override
    public T save(T contactVO) {
        Optional<T> optionalEntity = db.stream().filter(it -> it.getId() == contactVO.getId()).findFirst();
        if(optionalEntity.isPresent()){ // if exist will be update
            int existId = optionalEntity.get().getId();
            contactVO.setId(existId);
            db.remove(existId);
            db.add(contactVO);
        }else{ // if no exist will be save
            index++;
            contactVO.setId(index);
            db.add(contactVO);
        }
        return contactVO;
    }

    @Override
    public void deleteById(int id) {
        boolean result = db.removeIf(it -> it.getId() == id);
    }

    @Override
    public Optional<T> findById(int id) {
        return Optional.ofNullable(db.stream().filter(it -> it.getId() == id).findFirst().orElse(null));
    }

    @Override
    public List<T> findAll() {
        return db;
    }

    @Override
    public List<T> findByName(String name) {
       return db.stream().filter(it -> it.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public void deleteByName(String name) {
        db.removeIf(it -> it.getName().equals(name));
    }
}
