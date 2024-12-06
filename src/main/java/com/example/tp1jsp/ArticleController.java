package com.example.tp1jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @GetMapping(path="/get/{id}")
    public @ResponseBody Article getArticleById(@PathVariable int id) {
        return articleRepository.findById(id).orElse(null);
    }

    @PostMapping(path="/add")
    public @ResponseBody String addArticle(@RequestBody Article a) {
        if (utilisateurRepository.findById(a.getIdUser().getId()).isEmpty()) {
            return "Utilisateur non trouvé !";
        }

        articleRepository.save(a);
        return "Article enregistré !";
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteArticle(@PathVariable int id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return "Article " + id + " supprimé !";
        }
        return "Article " + id + " introuvable";
    }

    @PutMapping(path="/edit/{id}")
    public @ResponseBody String editArticle(@PathVariable int id, @RequestBody Article details) {
        Article a = articleRepository.findById(id).orElse(null);
        if (a == null) {
            return "Article " +  id + " introuvable";
        }

        if (details.getDatePublication() != null) {
            a.setDatePublication(details.getDatePublication());
        }

        if (details.getContenu() != null && !details.getContenu().isEmpty()) {
            a.setContenu(details.getContenu());
        }

        if (details.getIdUser() != null) {
            if (utilisateurRepository.findById(a.getIdUser().getId()).isEmpty()) {
                return "Utilisateur non trouvé !";
            }
                a.setIdUser(details.getIdUser());
        }

        articleRepository.save(a);
        return "Article " + id + " modifié !";
    }
}
