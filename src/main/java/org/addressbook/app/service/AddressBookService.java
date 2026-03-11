package org.addressbook.app.service;

import org.addressbook.app.dto.AddressBookDto;
import org.addressbook.app.entity.AddressBook;
import org.addressbook.app.repository.AddressBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AddressBookService {
    private AddressBookRepo addressBookRepo;
    @Autowired
    public AddressBookService(AddressBookRepo addressBookRepo) {
        this.addressBookRepo = addressBookRepo;
    }

    public List<AddressBook> getAddressBooks() {
        return addressBookRepo.findAll();
    }

    public AddressBook createAddressBook(AddressBookDto addressBook) {
        if(addressBookRepo.existsByName(addressBook.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Address book with same name already exists");
        }
        return addressBookRepo.save(AddressBook.builder().name(addressBook.getName()).build());
    }

}
