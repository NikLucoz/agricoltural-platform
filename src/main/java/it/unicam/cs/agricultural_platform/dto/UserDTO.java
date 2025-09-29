package it.unicam.cs.agricultural_platform.dto;

import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.models.user.UserType;
import it.unicam.cs.agricultural_platform.models.user.cart.UserCart;

import java.util.List;

public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String pIva;
    private String codFis;
    private String username;
    private List<UserType> userTypes;
    private UserCart userCart;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getpIva() { return pIva; }
    public void setpIva(String pIva) { this.pIva = pIva; }

    public String getCodFis() { return codFis; }
    public void setCodFis(String codFis) { this.codFis = codFis; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public List<UserType> getUserTypes() { return userTypes; }
    public void setUserTypes(List<UserType> userTypes) { this.userTypes = userTypes; }

    public UserCart getUserCart() { return userCart; }
    public void setUserCart(UserCart userCart) { this.userCart = userCart; }

    public static User fromDTO(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setpIva(dto.getpIva());
        user.setCodFis(dto.getCodFis());
        user.setUsername(dto.getUsername());
        user.setUserTypes(dto.getUserTypes());
        user.setUserCart(dto.getUserCart());
        return user;
    }

    public static UserDTO fromUser(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setpIva(user.getpIva());
        dto.setCodFis(user.getCodFis());
        dto.setUsername(user.getUsername());
        dto.setUserTypes(user.getUserTypes());
        dto.setUserCart(user.getUserCart());
        return dto;
    }
}
