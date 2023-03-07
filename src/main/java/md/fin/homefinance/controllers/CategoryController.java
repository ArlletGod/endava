package md.fin.homefinance.controllers;


import jakarta.validation.Valid;
import md.fin.homefinance.model.Category;
import md.fin.homefinance.model.Item;
import md.fin.homefinance.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("category", categoryService.findOne(id));
        return "category/show";
    }

    @GetMapping("/newcategory")
    public String newCategory(@ModelAttribute("category") Category category) {
        return "category/newcategory";
    }

    @PostMapping()
    public String create(@ModelAttribute("category") @Valid Category category,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "category/newcategory";

        categoryService.save(category);
        return "redirect:/categories";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("category", categoryService.findOne(id));
        return "category/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("item") @Valid Category category, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "category/edit";

        categoryService.update(id, category);
        return "redirect:/category";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryService.delete(id);
        return "redirect:/category";
    }
}