package com.example.crudproject.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudproject.demo.model.entities.User;
import com.example.crudproject.demo.model.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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

    @GetMapping(value = "listAll")
    @ResponseBody
    public ResponseEntity<List<User>> listUsers() {
        
        List<User> usersList = userRepository.findAll();
        return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
    }

}
