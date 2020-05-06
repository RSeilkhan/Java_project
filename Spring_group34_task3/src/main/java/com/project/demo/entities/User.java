package com.project.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;


    @Column(name = "isActive")
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
 //id, email, password, name, surname, isactive(bool), roles, gender, city, telnumber, birthday
    //new
    @Column(name = "gender")
    private String gender;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="city")
    Set <City> city;

    @Column(name = "tel_number")
    private String tel_number;

    @Column(name = "about")
    private String about;

    @Column(name = "birthday")
    private Integer birthday;

    @Column(name = "avatar")
    private String avatar;






}
