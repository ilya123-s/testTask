package troshko.test_task.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "SENSORS")
@NamedQueries(value = {
        @NamedQuery(name = Sensor.FIND_BY_NAME_MODEL,
                query = "select e from Sensor e " +
                        "where e.name = :name and e.model = :model")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = Sensor.UPDATE_SENSOR_BY_ID,
                query = "update SENSORS set NAME = ?, MODEL = ?, TYPE = ?, " +
                        "RANGE_FROM = ?, RANGE_TO = ?, UNIT = ?, " +
                        "LOCATION = ? where ID = ?")
})
public class Sensor {

    public static final String FIND_BY_NAME_MODEL = "Sensor.findByNameAndModel";
    public static final String UPDATE_SENSOR_BY_ID = "Sensor.updateSensorById";

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "RANGE_FROM")
    private int rangeFrom;

    @Column(name = "RANGE_TO")
    private int rangeTo;

    @Column(name = "UNIT")
    private String unit;

    @Column(name = "LOCATION")
    private String location;

    public Sensor(String name, String model, String type, int rangeFrom, int rangeTo, String unit, String location) {
        this.name = name;
        this.model = model;
        this.type = type;
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
        this.unit = unit;
        this.location = location;
    }
}
