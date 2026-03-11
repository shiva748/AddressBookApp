package org.addressbook.app.repository;

import org.addressbook.app.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepo extends JpaRepository<AddressBook, Long> {
    public boolean existsByName(String name);
}
