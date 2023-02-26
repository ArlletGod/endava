package md.fin.homefinance.repositories;

import md.fin.homefinance.model.Item;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByDate(Date date);

}
