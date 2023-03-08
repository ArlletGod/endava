package md.fin.homefinance.services;

import md.fin.homefinance.model.Category;
import md.fin.homefinance.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findOne(int id) {
        Optional<Category> foundItem = categoryRepository.findById(id);
        return foundItem.orElse(null);
    }

    @Transactional
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public void update(int id, Category updatedCategory) {
        updatedCategory.setId(id);
        categoryRepository.save(updatedCategory);
    }

    @Transactional
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }
}

