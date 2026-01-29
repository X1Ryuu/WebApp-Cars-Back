package com.example.demo.contact;
import com.example.demo.jwt.CustomJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;


import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;


@RestController
@Profile("h2")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/hello")
    public Message hello(){
        var jwt = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
      //  System.out.println(jwt);
        var message = MessageFormat.format("Hello {0}", jwt.getUsername());
        return new Message(message);
    }

    record Message(String message){

    }


    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        System.out.println("\nHello from Spring!\n");
        return ResponseEntity.ok("Hello from Spring!");
    }



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Contact user) {
        System.out.println("Received registration data: " + user);

        if(contactService.findByName(user.getName()).isPresent() || contactService.findByEmail(user.getEmail()).isPresent()) {
            if (contactService.findByName(user.getName()).isPresent()) {
                System.out.println("User name already exists!");
            } else {
                System.out.println("User email already exists!");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email or name already exists!");
        } else{

            user.setUserType("user");
            contactService.saveContact(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered in successfully!");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Contact user) {
        boolean valid = contactService.validateUser(user);
        System.out.println("Received login data: " + user.toString() +", valid: "+ valid);
        if (valid) {
            System.out.println("User logged in successfully!");
            /*return ResponseEntity.ok("User logged in successfully!");*/
            return ResponseEntity.ok("User logged in successfully!");
        } else {
            System.out.println("Invalid username or password!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password!");
        }
    }

    @GetMapping("/all")
    public List<Contact> getAllContacts() {
        System.out.println("Getting all contacts...");
        System.out.println(contactService.getAllContacts());
        return contactService.getAllContacts();
    }

    @GetMapping("/{name}")
    public Optional<Contact> getContactByName(@PathVariable String name) {
        return contactService.findByName(name);
    }



}
