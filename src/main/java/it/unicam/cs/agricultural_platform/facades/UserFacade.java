package it.unicam.cs.agricultural_platform.facades;

import it.unicam.cs.agricultural_platform.dto.content.PasswordChangeRequestDTO;
import it.unicam.cs.agricultural_platform.dto.user.UserDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.middlewares.MiddlewareValidationContext;
import it.unicam.cs.agricultural_platform.middlewares.user.UserDataMiddleware;
import it.unicam.cs.agricultural_platform.middlewares.user.UserPasswordMiddleware;
import it.unicam.cs.agricultural_platform.middlewares.user.UserUpdatePasswordMiddleware;
import it.unicam.cs.agricultural_platform.models.user.UserType;
import it.unicam.cs.agricultural_platform.services.UserService;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.models.user.cart.UserCart;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {
    @Autowired
    private UserService userService;

    @Autowired
    private ContentFacade contentFacade;

    @Autowired EventFacade eventFacade;

    private Middleware<UserDTO> userMiddleware;
    private Middleware<PasswordChangeRequestDTO> passwordMiddleware;

    @PostConstruct
    private void init(){
        userMiddleware = Middleware.link(
                new UserDataMiddleware(userService),
                new UserPasswordMiddleware(userService)
       );

        passwordMiddleware = Middleware.link(new UserUpdatePasswordMiddleware(userService));
    }

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

    public boolean updateUserPassword(PasswordChangeRequestDTO passwordChangeRequestDTO) {
        if(!passwordMiddleware.handle(passwordChangeRequestDTO, null)) return false;
        var user = userService.getUserById(passwordChangeRequestDTO.getUserId());
        userService.changePassword(user, passwordChangeRequestDTO.getNewPassword());
        return true;
    }

    // === CRUD ===

    public boolean addUser(UserDTO userDTO) {
        if(!userMiddleware.handle(userDTO, MiddlewareValidationContext.forCreate())) return false;
        var user = UserDTO.fromDTO(userDTO);
        return userService.addUser(user);
    }

    public boolean deleteUser(long id) {
        if(!userService.existsUser(id)) return false;
        var user = userService.getUserById(id);

        var userProducts = contentFacade.getProductsByUser(id);
        var userProductPackets = contentFacade.getProductPacketsByUser(id);

        for (var userProductPacket : userProductPackets) {
            contentFacade.removeOrphanContentFromCartItems(userProductPacket);
            contentFacade.deleteProductPacket(userProductPacket.getId());
        }

        for (var userProduct : userProducts) {
            contentFacade.removeOrphanContentFromCartItems(userProduct);
            contentFacade.deleteProduct(userProduct.getId());
        }

        eventFacade.removeOrphanPartecipationFor(user);

        return userService.deleteUser(id);
    }

    public boolean updateUser(long id, UserDTO userDTO) {
        if(!userMiddleware.handle(userDTO, MiddlewareValidationContext.forUpdate())) return false;
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
