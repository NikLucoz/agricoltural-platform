package it.unicam.cs.agricultural_platform.models.user;

import it.unicam.cs.agricultural_platform.models.user.cart.UserCart;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "platform_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String pIva;

    @Column(unique = true, nullable = false)
    private String codFis;

    @Column(unique = true, nullable = false)
    private String username;

    @ElementCollection(targetClass = UserType.class)
    @CollectionTable(name = "users_user_types", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "user_types")
    private List<UserType> userTypes = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_cart_id")
    private UserCart userCart;

    public UserCart getUserCart() {
        return userCart;
    }

    public void setUserCart(UserCart userCart) {
        this.userCart = userCart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getpIva() {
        return pIva;
    }

    public void setpIva(String pIva) {
        this.pIva = pIva;
    }

    public String getCodFis() {
        return codFis;
    }

    public void setCodFis(String codFis) {
        this.codFis = codFis;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }


    public boolean hasUserType(UserType userType) {
        return userTypes.contains(userType);
    }

    public List<UserType> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<UserType> userTypes) {
        this.userTypes = userTypes;
    }

    public void addUserType(UserType userType) {
        this.userTypes.add(userType);
    }

    public void removeUserType(UserType userType){
        this.userTypes.remove(userType);
    }
}
