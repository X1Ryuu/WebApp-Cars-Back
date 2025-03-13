package com.example.demo.Contacts;
import com.example.demo.jwt.CustomJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;


@RestController
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

/*
    @GetMapping("/form")
    public String showForm(Model model) {
        //System.out.println(contactService);
        model.addAttribute("activeTab", "login");
        return "signform/sign";
    }
*/

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        System.out.println("\nHello from Spring!\n");
        return ResponseEntity.ok("Hello from Spring!");
    }



    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute Contact user, RedirectAttributes redirectAttributes) {
        contactService.deleteContact(user);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully!");
        return "redirect:/contacts/form";
    }

    @GetMapping("/logged")
    public String logged(@ModelAttribute Contact user, RedirectAttributes redirectAttributes) {
        //return "redirect:/contacts/logged";
        return "signform/logged";
    }


/*    @PostMapping("/login")
    public ResponseEntity<?> getUser(@RequestBody Contact user){
        if(contactService.validateUser(user)) {
            return ResponseEntity.ok("User logged in successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password!");
        }
    }*/



    /*    @PostMapping("/register")
    public String addUser(@ModelAttribute Contact user,
                          @RequestParam("password-rep") String passwordRep,
                          Model model) {
        //System.out.println(user+" passwordRep: "+passwordRep);
        //System.out.println(user.getPassword().equals(passwordRep));
        if(contactService.findByName(user.getName()).isPresent() || contactService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("errorMessage", "User already exists!");
            model.addAttribute("activeTab", "register");
            if (contactService.findByName(user.getName()).isPresent()) {
                System.out.println("User name already exists!");
            } else {
                System.out.println("User email already exists!");
            }
        } else {
            if(user.getPassword().equals(passwordRep)) {
                user.setUserType("user");
            //    contactService.saveContact(user);
                model.addAttribute("successMessage", "User added successfully!");
                System.out.println("User added successfully!");
            } else {
                model.addAttribute("errorMessage", "Passwords do not match!");
                model.addAttribute("activeTab", "register");
                System.out.println("Passwords do not match!");
            }

        }

         // Przekierowanie po dodaniu użytkownika
        return "redirect:/contacts/form";
    }*/

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Contact user) {
        System.out.println("Received registration data: " + user); // Dodajemy logowanie danych wejściowych

        if(contactService.findByName(user.getName()).isPresent() || contactService.findByEmail(user.getEmail()).isPresent()) {
            if (contactService.findByName(user.getName()).isPresent()) {
                System.out.println("User name already exists!");
            } else {
                System.out.println("User email already exists!");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email or name already exists!");
        } else{
            /*return ResponseEntity.ok("User registered in successfully!");*/
            user.setUserType("user");
            contactService.saveContact(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered in successfully!");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Contact user) {
        boolean valid = contactService.validateUser(user);
        System.out.println("Received login data: " + user.toString() +", valid: "+ valid); // Dodajemy logowanie danych wejściowych
        if (valid) {
            System.out.println("User logged in successfully!"); // Logujemy sukces
            /*return ResponseEntity.ok("User logged in successfully!");*/
            return ResponseEntity.ok("User logged in successfully!");
        } else {
            System.out.println("Invalid username or password!"); // Logujemy błąd
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



    /*@PostMapping("/login")
    public ResponseEntity<?> getUser(@RequestBody Contact user){
        System.out.println(user);
        if(contactService.validateUser(user)) {
            return ResponseEntity.ok("User logged in successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password!");
        }
    }
*/
/*    public String getUser(@ModelAttribute Contact user,
                          Model model) {
        System.out.println(user);
        System.out.println(contactService.findByName(user.getName()));
        System.out.println(contactService.validateUser(user));
        if (contactService.validateUser(user)) {
            System.out.println("User logged in successfully!\n");
            return "redirect:/contacts/logged";
        } else {
            model.addAttribute("errorMessage", "Invalid username or password!");
            model.addAttribute("activeTab", "login");
            System.out.println("Invalid username or password!\n");
            return "signform/sign";
        }
*//*
        return "redirect:/contacts/form";
*//*
    }*/
}
