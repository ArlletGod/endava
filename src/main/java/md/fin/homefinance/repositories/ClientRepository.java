package md.fin.homefinance.repositories;

import md.fin.homefinance.model.Client;
import md.fin.homefinance.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByCompanyName(String companyName);
}
