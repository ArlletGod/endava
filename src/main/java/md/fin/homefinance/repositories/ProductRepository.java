package md.fin.homefinance.repositories;

import md.fin.homefinance.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
