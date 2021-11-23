package com.jee.cabinetSpring.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String email;
    private String password;

    //EAGER : charger toute la donnée vs LAZY : les données seront charger sur demande
    //ManyToMany n'est pas porteuse de données
    @ManyToMany(fetch = FetchType.EAGER)
    //le nom de la table de jointure
    @JoinTable(name = "user_roles",
    //la colonne correspond au clé etrangére de la table user_roles correspond à la table user
    joinColumns = @JoinColumn(name = "user_id"),
    //la colonne correspond au clé etrangére de la table user_roles correspond à la table roles
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
