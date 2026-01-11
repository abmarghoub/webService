package ma.projet.service_car.web;

import ma.projet.service_car.entities.Car;
import ma.projet.service_car.repositories.CarRepository;
import ma.projet.service_car.services.ClientApi;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarRepository carRepository;
    private final ClientApi clientService;

    public CarController(CarRepository carRepository, ClientApi clientService) {
        this.carRepository = carRepository;
        this.clientService = clientService;
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping
    public List<Car> getAllCars() {
        List<Car> cars = carRepository.findAll();
        for (Car car : cars) {
            if (car.getClientId() != null) {
                car.setClient(clientService.findClientById(car.getClientId()));
            }
        }
        return cars;
    }

    @GetMapping("/byClient/{clientId}")
    public List<Car> getCarsByClientId(@PathVariable Long clientId) {
        List<Car> cars = carRepository.findByClientId(clientId);
        for (Car car : cars) {
            car.setClient(clientService.findClientById(clientId));
        }
        return cars;
    }
}
