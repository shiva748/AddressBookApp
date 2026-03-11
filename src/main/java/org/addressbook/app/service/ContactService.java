package org.addressbook.app.service;

import org.addressbook.app.dto.AddContactDto;
import org.addressbook.app.entity.AddressBook;
import org.addressbook.app.entity.Contact;
import org.addressbook.app.repository.AddressBookRepo;
import org.addressbook.app.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContactService {
    private final AddressBookRepo addressBookRepo;
    private ContactRepo contactRepo;

    @Autowired
    public ContactService(ContactRepo contactRepo, AddressBookRepo addressBookRepo) {
        this.contactRepo = contactRepo;
        this.addressBookRepo = addressBookRepo;
    }

    public Contact addContact(Long addressBookId,AddContactDto addContactDto) {
        AddressBook book = addressBookRepo.findById(addressBookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "Address Book Not Found"));
        return contactRepo.save(Contact.builder().firstName(addContactDto.getFirstName()).lastName(addContactDto.getLastName()).email(addContactDto.getEmail()).phone(addContactDto.getPhoneNumber()).address(addContactDto.getAddress()).city(addContactDto.getCity()).state(addContactDto.getState()).zip(addContactDto.getZip()).addressBook(book).build());
    };
}
