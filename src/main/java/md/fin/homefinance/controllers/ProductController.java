package md.fin.homefinance.controllers;

import jakarta.validation.Valid;
import md.fin.homefinance.model.Client;
import md.fin.homefinance.model.Product;
import md.fin.homefinance.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("products", productService.findOne(id));
        return "product/show";
    }





    @GetMapping("/newproduct")
    public String newClient(@ModelAttribute("product") Product product) {
        return "product/newproduct";
    }




    @PostMapping()
    public String create(@ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "product/newproduct";

        productService.save(product);
        return "redirect:/products";
    }
}
