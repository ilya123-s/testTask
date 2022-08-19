package troshko.test_task.dao;

import troshko.test_task.model.Sensor;

import java.util.List;

public interface SensorDao {

    List<Sensor> getSensors();

    void save(Sensor entity);

    List<Sensor> findSensorByNameAndModel(String name, String model);

    void removeSensor(Long id);

    void editSensor(String name, String model, String type, int rangeFrom, int rangeTo, String unit, String location, Long id);

    Sensor findByIdSensor(Long id);
}
