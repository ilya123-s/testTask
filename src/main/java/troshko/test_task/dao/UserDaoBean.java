package troshko.test_task.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import troshko.test_task.model.UserEntity;

import javax.persistence.Query;

@Repository
@Transactional
public class UserDaoBean extends AbstractJpaDao<UserEntity, Long> implements UserDao {

    @Override
    public UserEntity findByUsername(String username) {
        final Query query = getEntityManager().createNamedQuery(UserEntity.FIND_BY_USER_NAME);
        query.setParameter("username", username);
        return getSingleResult(query);
    }
}
