package org.addressbook.app.controller;

import jakarta.validation.Valid;
import org.addressbook.app.dto.AddContactDto;
import org.addressbook.app.entity.Contact;
import org.addressbook.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressbook/{addressBookId}/contacts")
public class ContactController {
    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@PathVariable("addressBookId") Long addressBookId, @Valid @RequestBody AddContactDto addContactDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.addContact(addressBookId, addContactDto));
    }
}
