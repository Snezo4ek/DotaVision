package com.example.DotaVision.hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {

    private final HeroRepository heroRepository;

    @Autowired
    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public Hero findById(Long id) {
        return heroRepository.findById(id).orElse(null);
    }

    public List<Hero> findAll() {
        List<Hero> items =  heroRepository.findAll();
        if (items.isEmpty()) {
            return null;
        }
        return heroRepository.findAll();
    }

    public Hero saveHero(Hero hero) {
        return heroRepository.save(hero);
    }

    public void deleteHero(Long id) {
        heroRepository.deleteById(id);
    }
}
