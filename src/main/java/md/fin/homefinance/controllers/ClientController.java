package md.fin.homefinance.controllers;

import javax.persistence.*;
import javax.validation.Valid;

import md.fin.homefinance.model.Client;
import md.fin.homefinance.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "client/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", clientService.findOne(id));
        return "client/show";
    }

    @GetMapping("/newclient")
    public String newClient(@ModelAttribute("client") Client client) {
        return "client/newclient";
    }

    @PostMapping()
    public String create(@ModelAttribute("client") @Valid Client client,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "client/newclient";

        clientService.save(client);
        return "redirect:/clients";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("client", clientService.findOne(id));
        return "client/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("item") @Valid Client client, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "client/edit";

        clientService.update(id, client);
        return "redirect:/clients";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        clientService.delete(id);
        return "redirect:/clients";
    }
}


