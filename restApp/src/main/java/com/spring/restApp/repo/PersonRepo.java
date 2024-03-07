package com.spring.restApp.repo;

import com.spring.restApp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person,Integer> {
    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String lastName);
    List<Person> findByEmail(String email);
    List<Person> findByContactNumber(String contactNumber);
    List<Person> findByEmailContaining(String keyword);
    List<Person> findByContactNumberContaining(String keyNum);
}
