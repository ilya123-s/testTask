package troshko.test_task.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public abstract class AbstractJpaDao<Entity, Id> {

    @PersistenceContext
    private EntityManager entityManager;

    protected final List<Entity> getResultList(final Query query) {
        return query.getResultList();
    }

    protected final Entity getSingleResult(final Query query) {
        try {
            query.setMaxResults(1);
            return (Entity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    protected final EntityManager getEntityManager() {
        return entityManager;
    }
}
