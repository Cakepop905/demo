package com.example.demo.web;

import com.example.demo.data.TacoRepository;
import com.example.demo.domain.Ingredient.Type;
import com.example.demo.domain.Taco;
import com.example.demo.domain.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class tacoDesignController{
    @ModelAttribute
    public void addIngredientToModel(Model model){
            List<Ingredient> ingredients = Arrays.asList(
                    new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                    new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                    new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                    new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                    new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                    new Ingredient("LETC", "lettuce", Ingredient.Type.VEGGIES),
                    new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                    new Ingredient("CLBY", "Colby Jack", Ingredient.Type.CHEESE),
                    new Ingredient("MANGO", "Mango Salsa", Ingredient.Type.SAUCE),
                    new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                    new Ingredient("CHSU", "Chesse sauce", Ingredient.Type.SAUCE),
                    new Ingredient("CHKN", "Chicken", Ingredient.Type.PROTEIN),
                    new Ingredient("CLNT", "Cilantro", Ingredient.Type.VEGGIES),
                    new Ingredient("PRSL", "Parsley", Ingredient.Type.VEGGIES),
                    new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
            );
            // Type[] types = Type.values();
            Type[] types = Type.values();
            for (Type type : types) {
                model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
            }
        }
        @GetMapping
        public String showDesignForm(Model model) {
            model.addAttribute("design", new Taco());
            return "design";
        }

        @PostMapping
        public String processDesign (@Valid @ModelAttribute("design") Taco taco, Errors error){
            if (error.hasErrors()) {
                return "design";
            }
            Taco saved = tacoRepository.save(taco);
            log.info("Processing taco design for " + taco.getName());
            return "redirect:/orders/current";
        }

        private List<Ingredient> filterByType (List < Ingredient > ingredients, Type type){
            return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
        }

    }
