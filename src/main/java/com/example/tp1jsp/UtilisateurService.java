package com.example.tp1jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Iterable<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(int id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur getUtilisateurByLogin(String login) {
        for (Utilisateur utilisateur : utilisateurRepository.findAll()) {
            if (utilisateur.getLogin().equals(login)) {
                return utilisateur;
            }
        }
        return null;
    }

    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public String deleteUtilisateur(int id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return "Utilisateur " + id + " supprimé !";
        }
        return "Utilisateur " + id + " introuvable";
    }

    public String editUtilisateur(int id, Utilisateur details) {
        Utilisateur u = utilisateurRepository.findById(id).orElse(null);
        if (u == null) {
            return "Utilisateur " + id + " introuvable";
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
