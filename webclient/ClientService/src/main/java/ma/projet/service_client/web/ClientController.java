package ma.projet.service_client.web;

import ma.projet.service_client.entities.Client;
import ma.projet.service_client.repositories.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository repository;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return repository.save(client);
    }

    @DeleteMapping
    public void removeAllClients() {
        repository.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void removeClientById(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
