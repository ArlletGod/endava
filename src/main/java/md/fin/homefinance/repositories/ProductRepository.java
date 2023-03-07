package md.fin.homefinance.repositories;

import md.fin.homefinance.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
