package it.unicam.cs.agricultural_platform.controllers;

import it.unicam.cs.agricultural_platform.dto.PasswordChangeRequestDTO;
import it.unicam.cs.agricultural_platform.dto.UserCartDTO;
import it.unicam.cs.agricultural_platform.dto.UserDTO;
import it.unicam.cs.agricultural_platform.facades.UserFacade;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.models.user.UserType;
import it.unicam.cs.agricultural_platform.models.user.cart.UserCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserFacade userFacade;


    // === GENERIC ===

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
        User user = userFacade.getUser(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(UserDTO.fromUser(user), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userFacade.getUsers();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(UserDTO.fromUser(user));
        }

        if (users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}/cart")
    public ResponseEntity<UserCartDTO> getUserCart(@PathVariable long id) {
        UserCart userCart = userFacade.getUserCart(id);
        if (userCart == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(UserCartDTO.fromUserCart(userCart), HttpStatus.OK);
    }

    @PutMapping("/{id}/changePassword")
    public ResponseEntity<Object> changePassword(@PathVariable long id, @RequestBody PasswordChangeRequestDTO passwordChangeRequestDTO) {
        if(!userFacade.updateUserPassword(id, passwordChangeRequestDTO)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // === CRUD ===

    @PostMapping("/register")
    public ResponseEntity<Object> addUser(@RequestBody UserDTO user) {
        if(!userFacade.addUser(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateUser(@PathVariable long id, @RequestBody UserDTO updatedUser) {
        if(!userFacade.updateUser(id, updatedUser)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        if(!userFacade.deleteUser(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // === MANAGEMENT ===

    @PostMapping("/{id}/addUserType")
    public ResponseEntity<Object> addUserType(@PathVariable long id, @RequestBody UserType userType){
        if(!userFacade.setUserType(id, userType)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/removeUserType")
    public ResponseEntity<Object> removeUserType(@PathVariable long id, @RequestBody UserType userType){
        if(!userFacade.removeUserType(id, userType)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
