package com.example.tp1jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/utilisateur")
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Utilisateur> getAllUtilisateur() {
        return utilisateurRepository.findAll();
    }

    @GetMapping(path="/get/{id}")
    public @ResponseBody Utilisateur getUtilisateurById(@PathVariable int id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    @PostMapping(path="/add")
    public @ResponseBody String addUtilisateur(@RequestBody Utilisateur u) {
        utilisateurRepository.save(u);
        return "Utilisateur enregistré !";
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteUtilisateur(@PathVariable int id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return "Utilisateur " + id + " supprimé !";
        }
        return "Utilisateur " + id + " introuvable";
    }

    @PutMapping(path="/edit/{id}")
    public @ResponseBody String editUtilisateur(@PathVariable int id, @RequestBody Utilisateur details) {
        Utilisateur u = utilisateurRepository.findById(id).orElse(null);
        if (u == null) {
            return "Utilisateur " +  id + " introuvable";
        }

        if (details.getLogin() != null && !details.getLogin().isEmpty()) {
            u.setLogin(details.getLogin());
        }
        if (details.getMotDePasse() != null && !details.getMotDePasse().isEmpty()) {
            u.setMotDePasse(details.getMotDePasse());
        }
        if (details.getRole() != null && !details.getRole().isEmpty()) {
            u.setRole(details.getRole());
        }
        utilisateurRepository.save(u);
        return "Utilisateur " + id + " modifié !";
    }
}
