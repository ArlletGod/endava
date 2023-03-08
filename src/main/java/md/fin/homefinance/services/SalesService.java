package md.fin.homefinance.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import md.fin.homefinance.model.Category;
import md.fin.homefinance.model.Item;
import md.fin.homefinance.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SalesService {

    private final SalesRepository salesRepository;

    @Autowired
    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<Item> findAll() {
        return salesRepository.findAll();
    }


    public Item findOne(int id) {
        Optional<Item> foundItem = salesRepository.findById(id);
        return foundItem.orElse(null);
    }

    public Item getItemById(int id) throws Exception {
        return salesRepository.findById(id)
                .orElseThrow(() -> new Exception("Item with id " + id + " not found"));
    }

    public List<Item> getItemsByDate(Date date) {
        return salesRepository.findByDate(date);
    }

    public List<Item> getItemsByOwner(Category category) {
        return salesRepository.findByOwner(category);
    }


    @Transactional
    public void save(Item item) {
        item.setDate(new Date());
        salesRepository.save(item);
    }

    @Transactional
    public void update(int id, Item updatedItem) {
        updatedItem.setId(id);
        salesRepository.save(updatedItem);
    }


    @Transactional
    public void delete(int id) {
        salesRepository.deleteById(id);
    }

    public List<Item> getAllShops(){
        List<Item> list =  (List<Item>) salesRepository.findAll();
        return list;
    }

    /*
     * TODO: Get Shop By keyword
     */
    public List<Item> getByKeyword(String keyword){
        return salesRepository.findByKeyword(keyword);
    }

    public long getSumWithDiscount(){
        return salesRepository.findListOfSumFieldsAndDiscount();
    }


    public long getTotalPrice() {
        return salesRepository.getTotalPrice();
    }

public long getCount() {
    long count = salesRepository.count();
    return count;
}

    public List<Category> getAllCategoriesName(){
        return salesRepository.findAllCategoryName();
    }
}