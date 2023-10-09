package basics.baseRepository.impl;

import basics.baseRepository.BaseRepository;
import connection.Connection;
import entity.base.BaseEntity;
import lombok.Getter;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Getter
public class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T> {
    protected EntityManager entityManager;
    protected Class<T> className;

    public BaseRepositoryImpl(Class<T> className){
        entityManager = Connection.entityManager;
        this.className = className;
    }

    @Override
    public Optional<T> saveOrUpdate(T t) {
        if(t.getId()==0)
            entityManager.persist(t);
        else {
            entityManager.find(t.getClass(),t.getId());
            entityManager.merge(t);
        }
        return Optional.of(t);
    }

    @Override
    public void delete(T t) {
        entityManager.remove(t);
    }

    @Override
    public Optional<T> findById(long id) {
        return Optional.of(entityManager.find(className,id));
    }

    @Override
    public Optional<List<T>> findAll() {
        String queryLine = "from " + className.getSimpleName();
        return Optional.of(entityManager.createQuery(queryLine,className).getResultList());
    }
}
