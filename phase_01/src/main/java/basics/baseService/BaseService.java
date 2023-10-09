package basics.baseService;

import entity.base.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity> {
    T saveOrUpdate(T t);
    void delete(T t);
    T findById (long id);
    List<T> findAll();
    boolean isValid(T t);
}
