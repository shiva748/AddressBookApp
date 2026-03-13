package org.addressbook.app.service;

import org.addressbook.app.dto.ContactDto;
import org.addressbook.app.entity.AddressBook;
import org.addressbook.app.entity.Contact;
import org.addressbook.app.repository.AddressBookRepo;
import org.addressbook.app.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContactService {
    private final AddressBookRepo addressBookRepo;
    private ContactRepo contactRepo;

    @Autowired
    public ContactService(ContactRepo contactRepo, AddressBookRepo addressBookRepo) {
        this.contactRepo = contactRepo;
        this.addressBookRepo = addressBookRepo;
    }

    public Contact addContact(Long addressBookId, ContactDto addContactDto) {
        AddressBook book = addressBookRepo.findById(addressBookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "Address Book Not Found"));
        if (contactRepo.existsByFirstNameAndLastNameAndAddressBookId(addContactDto.getFirstName(), addContactDto.getLastName(), addressBookId)) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "Contact Already Exists In AddressBook");
        }
        return contactRepo.save(Contact.builder().firstName(addContactDto.getFirstName()).lastName(addContactDto.getLastName()).email(addContactDto.getEmail()).phone(addContactDto.getPhoneNumber()).address(addContactDto.getAddress()).city(addContactDto.getCity()).state(addContactDto.getState()).zip(addContactDto.getZip()).addressBook(book).build());
    }

    ;

    public Contact updateContact(Long addressBookId, Long contactId, ContactDto updateContactDto) {

        Contact contact = contactRepo.findByIdAndAddressBookId(contactId, addressBookId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "The requested contact does not exist in the specified address book."
                ));

        contact.setFirstName(updateContactDto.getFirstName());
        contact.setLastName(updateContactDto.getLastName());
        contact.setEmail(updateContactDto.getEmail());
        contact.setPhone(updateContactDto.getPhoneNumber());
        contact.setAddress(updateContactDto.getAddress());
        contact.setCity(updateContactDto.getCity());
        contact.setState(updateContactDto.getState());
        contact.setZip(updateContactDto.getZip());

        return contactRepo.save(contact);
    }

    public Contact deleteContact(Long addressBookId, Long contactId) {
        Contact contact = contactRepo.findByIdAndAddressBookId(contactId, addressBookId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "The requested contact does not exist in the specified address book."
        ));
        contactRepo.delete(contact);
        return contact;
    }

    public List<Contact> searchByCityOrState(String city, String state) {
        return contactRepo.findByCityOrState(city, state);
    }

    public Map<String, List<Contact>> viewPersonByCity(){
        return contactRepo.findAll().stream().collect(Collectors.groupingBy(Contact::getCity));
    }

    public Map<String, List<Contact>> viewPersonByState(){
        return contactRepo.findAll().stream().collect(Collectors.groupingBy(Contact::getState));
    }

    public Map<String, Long> countPersonByState(){
        return contactRepo.findAll().stream().collect(Collectors.groupingBy(Contact::getState, Collectors.counting()));
    }

    public Map<String, Long> countPersonByCity(){
        return contactRepo.findAll().stream().collect(Collectors.groupingBy(Contact::getCity, Collectors.counting()));
    }

    public List<Contact> getSortedByName(){
        return contactRepo.findAll(
                Sort.by("firstName", "lastName")
        );
    }

    public List<Contact> sortContactsByCity() {
        return contactRepo.findAll(
                Sort.by("city").ascending()
        );
    }

    public List<Contact> sortContactsByState() {
        return contactRepo.findAll(
                Sort.by("state").ascending()
        );
    }

    public List<Contact> sortContactsByZip() {
        return contactRepo.findAll(
                Sort.by("zip").ascending()
        );
    }
}
