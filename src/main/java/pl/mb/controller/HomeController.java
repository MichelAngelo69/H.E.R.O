package pl.mb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mb.dao.CommentDao;
import pl.mb.model.Comment;
import pl.mb.model.Hero;
import pl.mb.dao.HeroDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Autowired
    private HeroDao heroDao;

    @Autowired
    private CommentDao commentDao;

    @GetMapping("/")
    public String home1() {
        return "/login";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/addHero")
    public String add() {
        return "addHero";
    }
    @PostMapping("/loginIn")
     public String loginIn(){
        return"redirect:/home";
    }

    @PostMapping("/heroes")
    //  public String add(@RequestParam String humanName, @RequestParam String humanSurname,
    //                    @RequestParam String heroFullName, @RequestParam int phoneNumber,
    //                   @RequestParam String email, ModelMap modelMap) {
    public String heroes(@ModelAttribute Hero hero) {
        heroDao.save(hero);

        return "redirect:/allHeroes";
    }

    @GetMapping("/allHeroes")
    public String all(ModelMap modelMap) {
        modelMap.put("pokazuj", heroDao.findAll());
        return "allHeroes";
    }

    @GetMapping("allHeroes/{id}")
    public String show(@PathVariable Long id, ModelMap modelMap) {
        modelMap.put("hero", heroDao.findById(id).get());


        return "oneHero";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, ModelMap map) {
        heroDao.deleteById(id);
        return "redirect:/allHeroes";
    }

    // @GetMapping("/logout")
    //public String logout(){
    //   return"redirect:/login";
    // }
  /*  @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
*/

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute Comment comment, RedirectAttributes redirectAttributes){
        commentDao.save(comment);
        redirectAttributes.addFlashAttribute("message", "Comment added");
        return "redirect:/allHeroes/"+comment.getHero().getId();


    }}