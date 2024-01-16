package b.daw.cifpcm.animales_persistencia.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import b.daw.cifpcm.animales_persistencia.models.Animal;
import b.daw.cifpcm.animales_persistencia.services.AnimalService_Rep;

import java.util.List;

@Controller
public class AnimalController {
    @Autowired
    private AnimalService_Rep asR;

    @GetMapping("/")
    public String Index(Model model){
        return "index";
    }

    @GetMapping("/createget")/*Create*/
    public String Create_Animal(Model model){
        model.addAttribute("emptyAnimal", new Animal());
        return "animales/create";
    }

    @PostMapping("/createpost")/*Create*/
    public String Create_Animal(@ModelAttribute("emptyAnimal") Animal myAnimalModel
    , BindingResult result){
        if (result.hasErrors()) {
            return "/createget";
        }
        
        asR.Create(myAnimalModel);

        return "redirect:/list";
    }
    
    @GetMapping("/list")/*Read All*/
    public String Read_All_Animal(Model model){
        List<Animal> animalList = asR.ReadAll();
        model.addAttribute("animalList", animalList);
        return "animales/list";
    }
    
    @GetMapping("/single/{idAnimal}")/*Read One*/
    public String Read_Single_Animal(@PathVariable Integer idAnimal, Model model){
        Animal animalList = asR.GetAnimal(idAnimal);

        if (!(asR.Exist(idAnimal))) {
            return "animales/error";
        }

        model.addAttribute("animalSingle", animalList);
        return "animales/single";
    }
    
    //Get + Post method
    @GetMapping("/update/{idAnimal}")
    public String Update_Animal(@PathVariable Integer idAnimal, Model model){/*Update*/
        if (!asR.Exist(idAnimal)) {
            return "animales/error";
        }

        Animal animalUpdate = asR.GetAnimal(idAnimal);
        model.addAttribute("animalUpdate", animalUpdate);
        
        return "animales/update";
    }
    
    @PostMapping("/update")
    public String Update_Animal(@ModelAttribute("animalUpdate") Animal myAnimalModel, BindingResult result){/*Update*/
        if (!result.hasErrors()) {
            asR.Update(myAnimalModel);
            return "redirect:/list";
        }

        return "animales/update/" + myAnimalModel.getId();
    }
    
    //Get + Post method
    @GetMapping("/delete/{idAnimal}")
    public String Delete_Animal(@PathVariable Integer idAnimal, Model model){/*Delete*/
        //¿Error al Encontrar un Animal?
        if (!(asR.Exist(idAnimal))){
            return "/animales/error";
        }

        Animal animalDel = asR.GetAnimal(idAnimal);
        model.addAttribute("animalDelete", animalDel);
        
        return "animales/delete";
    }

    @PostMapping("/delete")
    public String Delete_Animal(@ModelAttribute("animalDelete") Animal myAnimalModel, BindingResult result){/*Delete*/
        //¿Otro Error?
        if (result.hasErrors()) {
            return "animales/delete";
        }

        asR.Delete(myAnimalModel.getId());

        return "redirect:/list";
    }

}