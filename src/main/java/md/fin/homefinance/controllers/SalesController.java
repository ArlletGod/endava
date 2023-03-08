package md.fin.homefinance.controllers;

import jakarta.validation.Valid;
import md.fin.homefinance.model.Category;
import md.fin.homefinance.model.Item;
import md.fin.homefinance.services.CategoryService;
import md.fin.homefinance.services.ClientService;
import md.fin.homefinance.services.SalesService;
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
public class SalesController {
    @Autowired
    private final SalesService salesService;

    @Autowired
    private final ClientService clientService;

    @Autowired
    private final CategoryService categoryService;


    public SalesController(SalesService salesService, ClientService clientService, CategoryService categoryService) {
        this.salesService = salesService;
        this.clientService = clientService;
        this.categoryService = categoryService;
    }




    @GetMapping()
    public String index(Model model) {
        model.addAttribute("list", salesService.findAll());
        long totalPrice = salesService.getSumWithDiscount();
        model.addAttribute("totalPrice", totalPrice);
        long countItems = salesService.getCount();
        model.addAttribute("countItems", countItems);

        return "finance/index";
    }

    @RequestMapping(path = {"finance/","/search"})
    public String home(Item item, Model model, String keyword) {
        if(keyword!=null) {
            List<Item> list = salesService.getByKeyword(keyword);
            model.addAttribute("list", list);
            long totalPrice = salesService.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);

        }else {
            List<Item> list = salesService.getAllShops();
            model.addAttribute("list", list);
            long totalPrice = salesService.getTotalPrice();
            model.addAttribute("totalPrice", totalPrice);
        }
        return "finance/index";
    }

    @GetMapping("/date/{date}")
    public String getItemsByDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Model model) {
        model.addAttribute("items", salesService.getItemsByDate(date));

        return "finance/showallbydate";
    }

    @GetMapping("/category/{owner}")
    public String getItemsByOwner(@PathVariable("owner") Category category, Model model) {
        model.addAttribute("items", salesService.getItemsByOwner(category));

        return "finance/showallbycategory";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", salesService.findOne(id));
        return "finance/show";
    }

    @GetMapping("/newproduct")
    public String newItem(@ModelAttribute("item") Item item , Model model) {
        model.addAttribute("list", clientService.findAll());
        model.addAttribute("categoryList", categoryService.findAll());
        return "finance/newproduct";
    }



    @PostMapping()
    public String create(@ModelAttribute("item") @Valid Item item,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "finance/newproduct";

        salesService.save(item);
        return "redirect:/finance";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("item", salesService.findOne(id));
        return "finance/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("item") @Valid Item item, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "finance/edit";

        salesService.update(id, item);
        return "redirect:/finance";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        salesService.delete(id);
        return "redirect:/finance";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") int id) {
        salesService.delete(id);
        return "redirect:/finance";
    }



}