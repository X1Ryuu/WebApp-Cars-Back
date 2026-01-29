package com.example.demo.contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("h2")
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void saveContact(Contact newContact) {
        contactRepository.save(newContact);
    }

    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    public Optional<Contact> findByName(String name) {
        return contactRepository.findByName(name);
    }

    public Optional<Contact> findByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    public Boolean validateUser(Contact contact) {
        Optional<Contact> userOptMail = findByEmail(contact.getName());
        Optional<Contact> userOptName = findByName(contact.getName());
        System.out.println(contact.getName() + " " +contact.getEmail()+" " + contact.getPassword());
        System.out.println(userOptName.isPresent());
        System.out.println(userOptMail.isPresent());

        return userOptMail.isPresent() && userOptMail.get().getPassword().equals(contact.getPassword())
                || userOptName.isPresent() && userOptName.get().getPassword().equals(contact.getPassword());
    }
}
