package troshko.test_task.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import troshko.test_task.model.Sensor;

import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class SensorDaoBean extends AbstractJpaDao<Sensor, Long> implements SensorDao {

    @Override
    public List<Sensor> getSensors() {
        Query query = getEntityManager().createQuery("SELECT e FROM Sensor e");
        return getResultList(query);
    }

    @Override
    public void save(Sensor entity) {
        getEntityManager().persist(entity);
    }

    @Override
    public List<Sensor> findSensorByNameAndModel(String name, String model) {
        Query query = getEntityManager().createNamedQuery(Sensor.FIND_BY_NAME_MODEL);
        query.setParameter("name", name);
        query.setParameter("model", model);
        return getResultList(query);
    }

    @Override
    public void removeSensor(Long id) {
        Query query = getEntityManager().createQuery("DELETE FROM Sensor WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void editSensor(String name, String model, String type, int rangeFrom, int rangeTo, String unit, String location, Long id) {
        Query query = getEntityManager().createNamedQuery(Sensor.UPDATE_SENSOR_BY_ID);
        query.setParameter(1, name);
        query.setParameter(2, model);
        query.setParameter(3, type);
        query.setParameter(4, rangeFrom);
        query.setParameter(5, rangeTo);
        query.setParameter(6, unit);
        query.setParameter(7, location);
        query.setParameter(8, id);
        query.executeUpdate();
    }

    @Override
    public Sensor findByIdSensor(Long id) {
        Query query = getEntityManager().createQuery("SELECT e FROM Sensor e where e.id = :id");
        query.setParameter("id", id);
        return getSingleResult(query);
    }
}
