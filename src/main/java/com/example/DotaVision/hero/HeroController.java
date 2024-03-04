package com.example.DotaVision.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class HeroController {

    private final HeroService heroService;

    @Autowired
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }


    @GetMapping("/heroes")
    public String findAll(Model model){
        List<Hero> heroes = heroService.findAll();
        model.addAttribute("heroes", heroes);
        return "hero-list";
    }

    @GetMapping("/heroes/create")
    public String createItemForm(Hero hero) {
        return "hero-create";
    }

    @PostMapping("/heroes/create")
    public String createHero(Hero hero) {
        heroService.saveHero(hero);
        return "redirect:/heroes";
    }

    @GetMapping("heroes/{id}/delete")
    public String deleteHero(@PathVariable("id") Long id){
        heroService.deleteHero(id);
        return "redirect:/heroes";
    }

    @GetMapping("/heroes/{id}/update")
    public String updateHeroForm(@PathVariable("id") Long id, Model model){
        Hero item = heroService.findById(id);
        model.addAttribute("item", item);
        return "hero-update";
    }

    @PostMapping("/heroes/update")
    public String updateHero(Hero hero){
        heroService.saveHero(hero);
        return "redirect:/heroes";
    }
}
