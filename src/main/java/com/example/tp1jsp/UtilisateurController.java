package com.example.tp1jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Utilisateur> getAllUtilisateur() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping(path="/get/{id}")
    public @ResponseBody Utilisateur getUtilisateurById(@PathVariable int id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @PostMapping(path="/add")
    public @ResponseBody String addUtilisateur(@RequestBody Utilisateur u) {
        utilisateurService.addUtilisateur(u);
        return "Utilisateur enregistré !";
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteUtilisateur(@PathVariable int id) {
            return utilisateurService.deleteUtilisateur(id);
    }

    @PutMapping(path="/edit/{id}")
    public @ResponseBody String editUtilisateur(@PathVariable int id, @RequestBody Utilisateur details) {
        return utilisateurService.editUtilisateur(id, details);
    }
}
