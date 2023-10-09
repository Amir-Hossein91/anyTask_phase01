package connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("anyTask");
    public final static EntityManager entityManager = emf.createEntityManager();
}
