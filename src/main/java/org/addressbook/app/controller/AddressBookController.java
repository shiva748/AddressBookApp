package org.addressbook.app.controller;

import jakarta.validation.Valid;
import org.addressbook.app.dto.AddressBookDto;
import org.addressbook.app.entity.AddressBook;
import org.addressbook.app.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private AddressBookService addressBookService;

    @Autowired
    public AddressBookController(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }

    @PostMapping("/create")
    public ResponseEntity<AddressBook> addAddress(@Valid @RequestBody AddressBookDto addressBookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressBookService.createAddressBook(addressBookDto));
    }
}
