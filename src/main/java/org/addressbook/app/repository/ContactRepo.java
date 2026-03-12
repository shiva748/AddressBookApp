package org.addressbook.app.repository;

import org.addressbook.app.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {
    Optional<Contact> findByIdAndAddressBookId(Long id, Long addressBookId);
}
