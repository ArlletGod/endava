package md.fin.homefinance.controllers;

import jakarta.validation.Valid;
import md.fin.homefinance.model.Item;
import md.fin.homefinance.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/finance")
public class ItemController {
    private final ItemService itemService;

@Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "finance/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemService.findOne(id));
        return "finance/show";
    }

    @GetMapping("/newproduct")
    public String newItem(@ModelAttribute("item") Item item) {
        return "finance/newproduct";
    }

    @PostMapping()
    public String create(@ModelAttribute("item") @Valid Item item,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "finance/newproduct";

        itemService.save(item);
        return "redirect:/finance";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("item", itemService.findOne(id));
        return "finance/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("item") @Valid Item item, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "finance/edit";

        itemService.update(id, item);
        return "redirect:/finance";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        itemService.delete(id);
        return "redirect:/finance";
    }
}