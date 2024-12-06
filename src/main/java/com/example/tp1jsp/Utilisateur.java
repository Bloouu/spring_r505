package com.example.tp1jsp;

import jakarta.persistence.*;

@Entity
@Table(name = "Utilisateur", schema = "db")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER", nullable = false)
    private Integer id;

    @Column(name = "login", nullable = false, length = 50)
    private String login;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    @Lob
    @Column(name = "role", nullable = false)
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {if (id != null) this.id = id;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {this.motDePasse = motDePasse;}

    public String getRole() {
        return role;
    }

    public void setRole(String role) {this.role = role;}

}