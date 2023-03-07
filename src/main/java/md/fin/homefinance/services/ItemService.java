package md.fin.homefinance.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import md.fin.homefinance.model.Category;
import md.fin.homefinance.model.Item;
import md.fin.homefinance.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }


    public Item findOne(int id) {
        Optional<Item> foundItem = itemRepository.findById(id);
        return foundItem.orElse(null);
    }

    public Item getItemById(int id) throws Exception {
        return itemRepository.findById(id)
                .orElseThrow(() -> new Exception("Item with id " + id + " not found"));
    }

    public List<Item> getItemsByDate(Date date) {
        return itemRepository.findByDate(date);
    }

    public List<Item> getItemsByOwner(Category category) {
        return itemRepository.findByOwner(category);
    }


    @Transactional
    public void save(Item item) {
        item.setDate(new Date());
        itemRepository.save(item);
    }

    @Transactional
    public void update(int id, Item updatedItem) {
        updatedItem.setId(id);
        itemRepository.save(updatedItem);
    }


    @Transactional
    public void delete(int id) {
        itemRepository.deleteById(id);
    }

    public List<Item> getAllShops(){
        List<Item> list =  (List<Item>)itemRepository.findAll();
        return list;
    }

    /*
     * TODO: Get Shop By keyword
     */
    public List<Item> getByKeyword(String keyword){
        return itemRepository.findByKeyword(keyword);
    }

    public long getSumWithDiscount(){
        return itemRepository.findListOfSumFieldsAndDiscount();
    }


    public long getTotalPrice() {
        return itemRepository.getTotalPrice();
    }

public long getCount() {
    long count = itemRepository.count();
    return count;
}

    public List<Category> getAllCategoriesName(){
        return itemRepository.findAllCategoryName();
    }
}