package basics.baseRepository;

import entity.base.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends BaseEntity> {
    Optional<T> saveOrUpdate(T t);
    void delete(T t);
    Optional<T> findById (long id);
    Optional<List<T>> findAll();
}
