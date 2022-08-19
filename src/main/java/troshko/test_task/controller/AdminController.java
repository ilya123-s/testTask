package troshko.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import troshko.test_task.dao.SensorDao;
import troshko.test_task.dao.TypeDao;
import troshko.test_task.dao.UnitDao;
import troshko.test_task.model.Sensor;
import troshko.test_task.model.Type;
import troshko.test_task.model.Unit;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMINISTRATOR')")
public class AdminController {

    @Autowired
    private SensorDao sensorDao;

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private UnitDao unitDao;

    @GetMapping
    public String sensorList(Map<String, Object> model) {
        List<Sensor> sensors = sensorDao.getSensors();
        model.put("sensors", sensors);
        List<Type> typesSensors = typeDao.getTypes();
        model.put("typesSensors", typesSensors);
        List<Unit> units = unitDao.getUnits();
        model.put("units", units);

        return "sensorTable";
    }

    @PostMapping
    public String addProduct(@RequestParam String name, @RequestParam String model, @RequestParam("choiceTypeSen") String choice,
                             @RequestParam int rangeFrom, @RequestParam int rangeTo,
                             @RequestParam("choiceUnit") String choiceUnit, @RequestParam String location,
                             Map<String, Object> modelMap, Model modelUI) {
        List<Type> typesSensors = typeDao.getTypes();
        Type type = new Type();

        for (Type resultType : typesSensors) {
            if (resultType.getName().equals(choice)) {
                type = resultType;
            }
        }

        modelMap.put("typesSensors", typesSensors);

        List<Unit> units = unitDao.getUnits();
        Unit unit = new Unit();

        for (Unit resultUnit : units) {
            if (resultUnit.getName().equals(choiceUnit)) {
                unit = resultUnit;
            }
        }

        modelMap.put("units", units);

        Sensor sensor = new Sensor(name, model, type.getName(), rangeFrom, rangeTo, unit.getName(), location);
        int flag = 0;

        if (name.isEmpty() || name.length() > 30) {
            flag = 1;
        } else if (model.isEmpty() || model.length() > 15) {
            flag = 2;
        } else if (rangeFrom > rangeTo) {
            flag = 3;
        } else if (location.length() > 40) {
            flag = 4;
        }

        modelUI.addAttribute("flagResult", String.valueOf(flag));

        if (flag == 0) {
            sensorDao.save(sensor);
        }

        List<Sensor> sensors = sensorDao.getSensors();
        modelMap.put("sensors", sensors);

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

    @PostMapping("/deleteSensor")
    public String deleteEvent(@RequestParam("sensorId") String sensor, Map<String, Object> model) {
        sensorDao.removeSensor(Long.valueOf(sensor));

        List<Sensor> sensors = sensorDao.getSensors();
        model.put("sensors", sensors);

        return "sensorTable";
    }

    @GetMapping("/{sensor}")
    public String editSensor(@PathVariable String sensor, Model model) {
        Sensor sensorEntity = sensorDao.findByIdSensor(Long.valueOf(sensor));
        model.addAttribute("sensor", sensorEntity);
        List<Type> typesSensors = typeDao.getTypes();
        model.addAttribute("typesSensors", typesSensors);
        List<Unit> units = unitDao.getUnits();
        model.addAttribute("units", units);

        return "editSensor";
    }

    @PostMapping("/show")
    public String edit(@RequestParam String name, @RequestParam String model, @RequestParam("choiceTypeSen") String choice,
                       @RequestParam int rangeFrom, @RequestParam int rangeTo,
                       @RequestParam("choiceUnit") String choiceUnit, @RequestParam String location, @RequestParam("id") String sensor,
                       Model modelUI) {
        List<Type> typesSensors = typeDao.getTypes();
        Type type = new Type();
        List<Unit> units = unitDao.getUnits();
        Unit unit = new Unit();
        int flag = 0;

        for (Type resultType : typesSensors) {
            if (resultType.getName().equals(choice)) {
                type = resultType;
            }
        }

        for (Unit resultUnit : units) {
            if (resultUnit.getName().equals(choiceUnit)) {
                unit = resultUnit;
            }
        }

        if (name.isEmpty() || name.length() > 30) {
            flag = 1;
        } else if (model.isEmpty() || model.length() > 15) {
            flag = 2;
        } else if (rangeFrom > rangeTo) {
            flag = 3;
        } else if (location.length() > 40) {
            flag = 4;
        }

        modelUI.addAttribute("flagResult", String.valueOf(flag));

        if (flag == 0) {
            sensorDao.editSensor(name, model, type.getName(), rangeFrom, rangeTo, unit.getName(), location, Long.valueOf(sensor));
        }

        return "redirect:/admin";
    }
}
