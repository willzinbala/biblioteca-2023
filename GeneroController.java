package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Genero;
import application.model.GeneroRepository;

@Controller
@RequestMapping("/genero")
public class GeneroController {
    @Autowired
    private GeneroRepository generoRepo;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("generos", generoRepo.findAll());
        return "/genero/list"; // Removi a barra inicial aqui
    }
    
    @RequestMapping("/insert")
    public String insert() {
        return "/genero/insert"; // Removi a barra inicial aqui
    }

    @RequestMapping(value="/insert", method=RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        Genero genero = new Genero();
        genero.setNome(nome);
        generoRepo.save(genero);
        return "redirect:/genero/list"; // Mantive a barra inicial aqui
    }

    @RequestMapping("/update")
    public String update(Model model, @RequestParam("id") int id) {
        Optional<Genero> genero = generoRepo.findById(id);

        if(genero.isPresent()) {
            model.addAttribute("genero", genero.get());
            return "/genero/update"; // Removi a barra inicial aqui
        }

        return "redirect:/genero/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
        @RequestParam("id") int id,
        @RequestParam("nome") String nome
    ) {
        Optional<Genero> genero = generoRepo.findById(id);

        if(genero.isPresent()) {
            genero.get().setNome(nome);
            generoRepo.save(genero.get());
        }

        return "redirect:/genero/list"; // Mantive a barra inicial aqui
    }

    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam("id") int id) {
        Optional<Genero> genero = generoRepo.findById(id);

        if(genero.isPresent()) {
            model.addAttribute("genero", genero.get());
            return "/genero/delete"; // Removi a barra inicial aqui
        }

        return "redirect:/genero/list";
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id) {
        generoRepo.deleteById(id);
        return "redirect:/genero/list"; // Mantive a barra inicial aqui
    }
}
