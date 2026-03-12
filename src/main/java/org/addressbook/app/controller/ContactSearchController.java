package org.addressbook.app.controller;

import org.addressbook.app.entity.Contact;
import org.addressbook.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contacts")
public class ContactSearchController {
    private ContactService contactService;

    @Autowired
    public ContactSearchController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Contact>> searchByCity(@RequestParam(required = false) String city,  @RequestParam(required = false) String state) {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.searchByCityOrState(city, state));
    }

    @GetMapping("/group/city")
    public ResponseEntity<Map<String, List<Contact>>> groupByCity() {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.viewPersonByCity());
    }

    @GetMapping("/group/state")
    public ResponseEntity<Map<String, List<Contact>>> groupByState() {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.viewPersonByState());
    }

    @GetMapping("/count/city")
    public ResponseEntity<Map<String, Long>> countByCity() {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.countPersonByCity());
    }

    @GetMapping("/count/state")
    public ResponseEntity<Map<String, Long>> countByState() {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.countPersonByState());
    }
}
