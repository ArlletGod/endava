package md.fin.homefinance.controllers;

import jakarta.validation.Valid;
import md.fin.homefinance.model.Category;
import md.fin.homefinance.model.Item;
import md.fin.homefinance.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
        model.addAttribute("list", itemService.findAll());
        long totalPrice = itemService.getSumWithDiscount();
        model.addAttribute("totalPrice", totalPrice);
        long countItems = itemService.getCount();
        model.addAttribute("countItems", countItems);

        return "finance/index";
    }

    @RequestMapping(path = {"finance/","/search"})
    public String home(Item item, Model model, String keyword) {
        if(keyword!=null) {
            List<Item> list = itemService.getByKeyword(keyword);
            model.addAttribute("list", list);
            long totalPrice = itemService.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);

        }else {
            List<Item> list = itemService.getAllShops();
            model.addAttribute("list", list);
            long totalPrice = itemService.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);
        }
        return "finance/index";
    }

    @GetMapping("/date/{date}")
    public String getItemsByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Model model) {
        model.addAttribute("items", itemService.getItemsByDate(date));

        return "finance/showallbydate";
    }

    @GetMapping("/category/{owner}")
    public String getItemsByOwner(@PathVariable("owner") Category category, Model model) {
        model.addAttribute("items", itemService.getItemsByOwner(category));

        return "finance/showallbycategory";
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

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") int id) {
        itemService.delete(id);
        return "redirect:/finance";
    }



}