package md.fin.homefinance.services;



import md.fin.homefinance.model.Product;
import md.fin.homefinance.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }


    public Product findOne(Long id) {
        Optional<Product> foundItem = productRepository.findById(id);
        return foundItem.orElse(null);
    }

    public Product getItemById(Long id) throws Exception {
        return productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product with id " + id + " not found"));
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }
}
