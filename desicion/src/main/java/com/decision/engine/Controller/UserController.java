package com.decision.engine.Controller;



import com.decision.engine.Exceptions.LoanException;
import com.decision.engine.Model.User;
import com.decision.engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<User> getUserById(@PathVariable(value = "id", required = true) final Long id) {

        User userFound = userService.findUserById(id);
        return ResponseEntity
                .ok()
                .body(userFound);
    }


    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<List<User>> getAllUsers() {
;
        return ResponseEntity
                .ok()
                .body(userService.findAllUsers());
    }


    @PostMapping(value = "/create", produces = {"application/json"}, consumes = { "application/json"})
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null) {
            throw new LoanException("User data are missing");
        }
        return ResponseEntity
                .ok()
                .body(userService.save(user));
    }

    @PutMapping(value = "/update", produces = {"application/json"}, consumes = { "application/json"})
    public ResponseEntity<User> updateUser( @RequestBody User user) {


        return ResponseEntity
                .ok()
                .body(userService.updateUser(user));
    }



}
