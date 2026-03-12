package org.addressbook.app.repository;

import org.addressbook.app.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {
    Optional<Contact> findByIdAndAddressBookId(Long id, Long addressBookId);

    boolean existsByFirstNameAndLastNameAndAddressBookId(String firstName, String lastName, Long addressBookId);

    List<Contact> findByCityOrState(String city, String state);
}
