package pl.mb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mb.model.Hero;
import pl.mb.dao.HeroDao;

@Controller
public class HomeController {

    @Autowired
   private HeroDao heroDao;


    @GetMapping("/")
    public String home1(){
        return "/home";
    }
    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/addHero")
    public String add(){
        return "addHero";
    }

    @PostMapping("/heroes")
  //  public String add(@RequestParam String humanName, @RequestParam String humanSurname,
  //                    @RequestParam String heroFullName, @RequestParam int phoneNumber,
   //                   @RequestParam String email, ModelMap modelMap) {
    public String heroes(@ModelAttribute Hero hero){
        heroDao.save(hero);

        return "redirect:/allHeroes";
    }

    @GetMapping("/allHeroes")
    public String all(ModelMap modelMap){
        modelMap.put("pokazuj", heroDao.findAll());
                return "allHeroes";
    }
    @GetMapping("allHeroes/{id}")
    public String show(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("hero", heroDao.findById(id).get());
        return "oneHero";
    }
}