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





    @Query(value = "SELECT SUM(o.sum_field) FROM item o JOIN categories ct ON ct.id = o.category_id", nativeQuery = true)
    long getTotalPrice();


    @Query(value = "SELECT SUM(sum_field-(((o.cost*o.quantity)*ct.discount)/100)) FROM item o JOIN client ct ON o.client_id = ct.id", nativeQuery = true)
    long findListOfSumFieldsAndDiscount();



    @Query(value = "select c.company_name from client c", nativeQuery = true)
    List<Category> findAllCategoryName();

    @Query(value = "select * from item i where i.name like %:keyword% ", nativeQuery = true)
    List<Item> findByKeyword(@Param("keyword") String keyword);


}
