package org.lessons.springlamiapizzeriacrud.service;

import org.lessons.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.lessons.springlamiapizzeriacrud.model.Pizza;
import org.lessons.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    public Pizza createPizza(Pizza formPizza) {

        Pizza pizzaToStorage = new Pizza();
        pizzaToStorage.setName(formPizza.getName());
        pizzaToStorage.setPrice(formPizza.getPrice());
        pizzaToStorage.setImageLink(formPizza.getImageLink());
        pizzaToStorage.setDescription(formPizza.getDescription());
        pizzaToStorage.setIngredients(formPizza.getIngredients());
        pizzaToStorage.setCreatedAt(LocalDateTime.now());
        pizzaToStorage.setUpdatedAt(LocalDateTime.now());

        return pizzaRepository.save(pizzaToStorage);

    }

    public Pizza updatePizza(Pizza formPizza, Integer id) throws PizzaNotFoundException{
        Pizza pizzaToUpdate = getPizzaById(id);
        pizzaToUpdate.setName(formPizza.getName());
        pizzaToUpdate.setPrice(formPizza.getPrice());
        pizzaToUpdate.setImageLink(formPizza.getImageLink());
        pizzaToUpdate.setDescription(formPizza.getDescription());
        pizzaToUpdate.setIngredients(formPizza.getIngredients());
        pizzaToUpdate.setUpdatedAt(LocalDateTime.now());

        return pizzaRepository.save(pizzaToUpdate);

    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> getSearchedPizzas(String keyword) {
        return pizzaRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Pizza getPizzaById(Integer id) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PizzaNotFoundException(Integer.toString(id));
        }
    }

    public boolean deletePizzaById(Integer id) {
        pizzaRepository.findById(id).orElseThrow(() -> new PizzaNotFoundException(Integer.toString(id)));
        try {
            pizzaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
