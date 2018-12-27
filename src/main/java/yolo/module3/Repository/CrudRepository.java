package yolo.module3.Repository;

import java.util.List;

public interface CrudRepository<T> {

    T getOne(long id);

    List<T> getAll();

    void addOne(T object);

    void addMany(List<T> objects);

    void deleteOne(long id);

    void deleteMany(List<T> objects);

    //TODO: add update method

}
