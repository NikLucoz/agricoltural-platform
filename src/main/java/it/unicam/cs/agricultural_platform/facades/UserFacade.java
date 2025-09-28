package it.unicam.cs.agricultural_platform.facades;

import it.unicam.cs.agricultural_platform.dto.PasswordChangeRequestDTO;
import it.unicam.cs.agricultural_platform.dto.UserDTO;
import it.unicam.cs.agricultural_platform.models.user.UserType;
import it.unicam.cs.agricultural_platform.services.UserService;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.models.user.cart.UserCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {
    @Autowired
    private UserService userService;

    @Autowired
    private ContentFacade contentFacade;


    // === GENERIC ===

    public List<User> getUsers() {
        return userService.getUsers();
    }

    public User getUser(long id) {
        return userService.getUserById(id);
    }

    public UserCart getUserCart(long id) {
        return userService.getUserCart(id);
    }

    public boolean updateUserPassword(long id, PasswordChangeRequestDTO passwordChangeRequestDTO) {
        if(!userService.existsUser(id)) return false;
        var user = userService.getUserById(id);
        return userService.changePassword(user, passwordChangeRequestDTO.getOldPassword(), passwordChangeRequestDTO.getNewPassword());
    }


    // === CRUD ===

    public boolean addUser(UserDTO userDTO) {
        var user = UserDTO.fromDTO(userDTO);
        if(userService.existsUserByUsername(user.getUsername())) return false;
        return userService.addUser(user);
    }

    public boolean deleteUser(long id) {
        var userProducts = contentFacade.getProductsByUser(id);
        var userProductPackets = contentFacade.getProductPacketsByUser(id);

        for (var userProductPacket : userProductPackets) {
            contentFacade.deleteProductPacket(userProductPacket.getId());
        }

        for (var userProduct : userProducts) {
            contentFacade.deleteProduct(userProduct.getId());
        }

        return userService.deleteUser(id);
    }

    public boolean updateUser(long id, UserDTO userDTO) {
        var updatedUser = UserDTO.fromDTO(userDTO);
        return userService.updateUser(id, updatedUser);
    }


    // === MANAGEMENT ===

    public boolean setUserType(long id, UserType userType) {
        if(!userService.existsUser(id)) return false;
        var user = userService.getUserById(id);
        if(user == null) return false;

        return userService.setUserType(user, userType);
    }

    public boolean removeUserType(long id, UserType userType) {
        if(!userService.existsUser(id)) return false;
        var user = userService.getUserById(id);
        if(user == null) return false;

        return userService.removeUserType(user, userType);
    }

}
