package troshko.test_task.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import troshko.test_task.model.Unit;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UnitDaoBean extends AbstractJpaDao<Unit, Long> implements UnitDao {

    @Override
    public List<Unit> getUnits() {
        Query query = getEntityManager().createQuery("SELECT e FROM Unit e");
        return getResultList(query);
    }
}
