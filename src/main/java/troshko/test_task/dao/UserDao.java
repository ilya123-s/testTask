package troshko.test_task.dao;

import troshko.test_task.model.UserEntity;

public interface UserDao {

    UserEntity findByUsername(String username);
}
