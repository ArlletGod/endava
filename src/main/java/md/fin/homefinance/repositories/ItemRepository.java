package md.fin.homefinance.repositories;

import md.fin.homefinance.model.Item;
import md.fin.homefinance.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
