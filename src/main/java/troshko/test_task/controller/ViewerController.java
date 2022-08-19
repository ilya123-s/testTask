package troshko.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import troshko.test_task.dao.SensorDao;
import troshko.test_task.model.Sensor;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('VIEWER')")
public class ViewerController {

    @Autowired
    private SensorDao sensorDao;

    @GetMapping
    public String sensorList(Map<String, Object> model) {
        List<Sensor> sensors = sensorDao.getSensors();
        model.put("sensors", sensors);

        return "sensorTable";
    }

    @PostMapping(value = "/filter")
    public String filterProduct(@RequestParam("filterName") String filterName, @RequestParam("filterModel") String filterModel,
                                Map<String, Object> model) {
        List<Sensor> sensors;
        if (filterName != null && !filterName.isEmpty() && filterModel != null && !filterModel.isEmpty()) {
            sensors = sensorDao.findSensorByNameAndModel(filterName, filterModel);
        } else {
            sensors = sensorDao.getSensors();
        }

        model.put("sensors", sensors);

        return "sensorTable";
    }
}
