package ma.projet.service_client.config;

import ma.projet.service_client.entities.Client;
import ma.projet.service_client.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ClientRepository clientRepository) {
        return args -> {
            // Supprimer tous les clients existants
            clientRepository.deleteAll();
            
            // Créer les nouveaux clients
            Client client1 = new Client(null, "Abla", 23.0f);
            Client client2 = new Client(null, "Kaoutar", 23.0f);
            Client client3 = new Client(null, "Hamza", 22.0f);
            Client client4 = new Client(null, "Hadil", 22.0f);
            
            clientRepository.save(client1);
            clientRepository.save(client2);
            clientRepository.save(client3);
            clientRepository.save(client4);
            
            System.out.println("Clients initialisés avec succès!");
        };
    }
}

