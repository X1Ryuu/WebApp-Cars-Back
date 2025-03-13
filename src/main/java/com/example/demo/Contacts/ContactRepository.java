package com.example.demo.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Repository
interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByName(String name);
    Optional<Contact> findByEmail(String email);
}
