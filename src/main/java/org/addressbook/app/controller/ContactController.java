package org.addressbook.app.controller;

import jakarta.validation.Valid;
import org.addressbook.app.dto.ContactDto;
import org.addressbook.app.entity.Contact;
import org.addressbook.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressbook/{addressBookId}")
public class ContactController {
    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> createContact(@PathVariable("addressBookId") Long addressBookId, @Valid @RequestBody ContactDto addContactDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.addContact(addressBookId, addContactDto));
    }

    @PutMapping("/contact/{contactId}")
    public ResponseEntity<Contact> updateContact(@PathVariable("addressBookId") Long addressBookId, @PathVariable("contactId") Long contactId, @Valid @RequestBody ContactDto updateContactDto) {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.updateContact(addressBookId, contactId, updateContactDto));
    }
}
