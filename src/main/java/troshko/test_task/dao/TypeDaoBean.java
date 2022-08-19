package troshko.test_task.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import troshko.test_task.model.Type;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class TypeDaoBean extends AbstractJpaDao<Type, Long> implements TypeDao {

    @Override
    public List<Type> getTypes() {
        Query query = getEntityManager().createQuery("SELECT e FROM Type e");
        return getResultList(query);
    }
}
