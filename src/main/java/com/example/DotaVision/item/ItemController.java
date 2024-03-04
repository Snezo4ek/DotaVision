package com.example.DotaVision.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/items")
    public String findAll(Model model){
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "item-list";
    }

    @GetMapping("/items/create")
    public String createItemForm(Item item) {
        return "item-create";
    }

    @PostMapping("/items/create")
    public String createItem(Item item) {
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("items/{id}/delete")
    public String deleteItem(@PathVariable("id") Long id){
        itemService.deleteItem(id);
        return "redirect:/items";
    }

    @GetMapping("/items/{id}/update")
    public String updateHeroForm(@PathVariable("id") Long id, Model model){
        Item item = itemService.findById(id);
        model.addAttribute("item", item);
        return "item-update";
    }

    @PostMapping("/items/update")
    public String updateUser(Item item){
        itemService.saveItem(item);
        return "redirect:/items";
    }
}
