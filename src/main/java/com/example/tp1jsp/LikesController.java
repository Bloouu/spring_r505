package com.example.tp1jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/likes")
public class LikesController {

    @Autowired
    private LikesRepository likesRepository;
    
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Likes> getAllArticle() {
        return likesRepository.findAll();
    }

    @GetMapping(path="/get/{id}")
    public @ResponseBody Likes getArticleById(@PathVariable int id) {
        return likesRepository.findById(id).orElse(null);
    }

    @PostMapping(path="/add")
    public @ResponseBody String addArticle(@RequestBody Likes a) {
        if (utilisateurRepository.findById(a.getIdUtilisateur().getId()).isEmpty()) {
            return "Utilisateur non trouvé !";
        }

        if (articleRepository.findById(a.getIdArticle().getId()).isEmpty()) {
            return "Article non trouvé !";
        }

        likesRepository.save(a);
        return "Like enregistré !";
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody String deleteArticle(@PathVariable int id) {
        if (likesRepository.existsById(id)) {
            likesRepository.deleteById(id);
            return "Like " + id + " supprimé !";
        }
        return "Like " + id + " introuvable";
    }

    @PutMapping(path="/edit/{id}")
    public @ResponseBody String editArticle(@PathVariable int id, @RequestBody Likes details) {
        Likes l = likesRepository.findById(id).orElse(null);
        if (l == null) {
            return "Like " +  id + " introuvable";
        }

        if (details.getIdUtilisateur() != null) {
            if (utilisateurRepository.findById(l.getIdUtilisateur().getId()).isEmpty()) {
                return "Utilisateur non trouvé !";
            }
            l.setIdUtilisateur(details.getIdUtilisateur());
        }

        if (details.getIdArticle() != null) {
            if (utilisateurRepository.findById(l.getIdArticle().getId()).isEmpty()) {
                return "Article non trouvé !";
            }
            l.setIdArticle(details.getIdArticle());
        }

        if (details.getLike() != null) {
            l.setLike(details.getLike());
        }

        likesRepository.save(l);
        return "Likes " + id + " modifié !";
    }
}

