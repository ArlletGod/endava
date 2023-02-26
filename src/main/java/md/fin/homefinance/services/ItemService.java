package md.fin.homefinance.services;

import md.fin.homefinance.model.Category;
import md.fin.homefinance.model.Client;
import md.fin.homefinance.model.Item;
import md.fin.homefinance.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    public List<Item> findAll() {
        return itemRepository.findAll();
    }


    public Item findOne(int id) {
        Optional<Item> foundItem = itemRepository.findById(id);
        return foundItem.orElse(null);
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



}