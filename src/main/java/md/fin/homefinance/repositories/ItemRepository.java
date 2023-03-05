package md.fin.homefinance.repositories;

import md.fin.homefinance.model.Category;
import md.fin.homefinance.model.Client;
import md.fin.homefinance.model.Item;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByDate(Date date);

    List<Item> findByOwner(Category category);
    List<Item> findByOwnClient(Client client);

    @Query(value = "select * from item i where i.name like %:keyword% ", nativeQuery = true)
    List<Item> findByKeyword(@Param("keyword") String keyword);



}
