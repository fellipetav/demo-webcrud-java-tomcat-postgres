package com.example.crudproject.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudproject.demo.model.entities.User;
import com.example.crudproject.demo.model.repository.UserRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

// Annotation to identify this class as a web resource controlled by a REST controller
@RestController
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "user/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String grettingText(@PathVariable String name) {

        User user = new User();
        user.setName(name);
        userRepository.save(user);
        return "Hello, " + name + "!";
    }

    @GetMapping(value = "list_all")
    @ResponseBody // Returns the data bounded on the web response body
    public ResponseEntity<List<User>> listUsers() {

        List<User> usersList = userRepository.findAll(); // Requests the database
        return new ResponseEntity<List<User>>(usersList, HttpStatus.OK); // Returns the users (the object List) in JSON
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User createdUser = userRepository.save(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "update")
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody User user) {

        if (user.getId() == null) {
            return new ResponseEntity<String>(
                    "Operation not processed. You must inform an user id.",
                    HttpStatus.NOT_FOUND);
        }

        User updatedUser = userRepository.saveAndFlush(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@RequestParam Long userId) {

        userRepository.deleteById(userId);
        return new ResponseEntity<String>(
                "User successfully deleted.",
                HttpStatus.OK);
    }

    @GetMapping(value = "search_user_id")
    @ResponseBody
    public ResponseEntity<?> getUserById(@RequestParam(name = "userId") Long userId) {

        if (userId == null) {
            return new ResponseEntity<String>(
                    "Operation not processed. You must inform an user Id.",
                    HttpStatus.NOT_FOUND);
        }

        User foundUser = userRepository.findById(userId).get();
        return new ResponseEntity<User>(foundUser, HttpStatus.FOUND);
    }

    @GetMapping(value = "search_name")
    public ResponseEntity<List<User>> getUserByName(
            @RequestParam(name = "inputedName") String inputedName) {

        List<User> usersList = userRepository.searchByInexactName(inputedName.trim().toLowerCase());
        return new ResponseEntity<List<User>>(usersList, HttpStatus.FOUND);
    }

}
