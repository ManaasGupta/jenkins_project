package com.spring.restApp.service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.spring.restApp.dto.PersonDto;
import com.spring.restApp.entity.Person;
import com.spring.restApp.repo.PersonRepo;

public class PersonServiceImplTest {

    private PersonServiceImpl personService;
    private PersonRepo personRepo;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        personRepo = mock(PersonRepo.class);
        modelMapper = new ModelMapper();
        personService = new PersonServiceImpl(personRepo, modelMapper);
    }

    @Test
    public void testGetAll() {
        // Arrange
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "John", "Doe", "john@example.com", "123456789"));
        personList.add(new Person(2, "Jane", "Doe", "jane@example.com", "987654321"));

        when(personRepo.findAll()).thenReturn(personList);

        // Act
        List<PersonDto> result = personService.getAll();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testGetPerson() {
        // Arrange
        Person existingPerson = new Person(1, "John", "Doe", "john@example.com", "123456789");
        when(personRepo.findById(1)).thenReturn(Optional.of(existingPerson));

        // Act
        PersonDto result = personService.getPerson(1);

        // Assert
        assertEquals(existingPerson.getFirstName(), result.getFirstName());
    }

    @Test
    public void testAddPersonRecord() {
        // Arrange
        PersonDto newPersonDto = new PersonDto(1, "New", "Person", "new@example.com", "987654321");
        // Act
        PersonDto result = personService.addPersonRecord(newPersonDto);

        // Assert
        assertNotNull(result.getPersonId());
        assertEquals(newPersonDto.getFirstName(), result.getFirstName());
    }

    @Test
    public void testUpdatePersonRecord() {
        // Arrange
        Person existingPerson = new Person(1, "John", "Doe", "john@example.com", "123456789");
        PersonDto updatedPersonDto = new PersonDto(null, "Updated", "Person", "updated@example.com", "555555555");

        when(personRepo.findById(1)).thenReturn(Optional.of(existingPerson));

        // Act
        PersonDto result = personService.updatePersonRecord(1, updatedPersonDto);

        // Assert
        assertEquals(updatedPersonDto.getFirstName(), result.getFirstName());
        assertEquals(updatedPersonDto.getLastName(), result.getLastName());
        assertEquals(updatedPersonDto.getEmail(), result.getEmail());
        assertEquals(updatedPersonDto.getContactNumber(), result.getContactNumber());
    }

    @Test
    public void testDeletedRecord() {
        // Arrange
        Person existingPerson = new Person(1, "John", "Doe", "john@example.com", "123456789");
        when(personRepo.findById(1)).thenReturn(Optional.of(existingPerson));

        // Act
        String result = personService.deletedRecord(1);

        // Assert
        assertEquals("Record Deleted Successfully", result);
    }

    @Test
    public void testGetPersonByFirstName() {
        // Arrange
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "John", "Doe", "john@example.com", "123456789"));
        when(personRepo.findByFirstName("John")).thenReturn(personList);

        // Act
        List<PersonDto> result = personService.getPersonByFirstName("John");

        // Assert
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    public void testGetPersonByLastName() {
        // Arrange
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "John", "Doe", "john@example.com", "123456789"));
        when(personRepo.findByLastName("Doe")).thenReturn(personList);

        // Act
        List<PersonDto> result = personService.getPersonByLastName("Doe");

        // Assert
        assertEquals(1, result.size());
        assertEquals("Doe", result.get(0).getLastName());
    }

    @Test
    public void testGetPersonByEmail() {
        // Arrange
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "John", "Doe", "john@example.com", "123456789"));
        when(personRepo.findByEmail("john@example.com")).thenReturn(personList);

        // Act
        List<PersonDto> result = personService.getPersonByEmail("john@example.com");

        // Assert
        assertEquals(1, result.size());
        assertEquals("john@example.com", result.get(0).getEmail());
    }

    @Test
    public void testGetPersonByEmailKeyword() {
        // Arrange
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "John", "Doe", "john@example.com", "123456789"));
        when(personRepo.findByEmailContaining("example")).thenReturn(personList);

        // Act
        List<PersonDto> result = personService.getPersonByEmailKeyword("example");

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.get(0).getEmail().contains("example"));
    }

    @Test
    public void testGetPersonByContact() {
        // Arrange
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "John", "Doe", "john@example.com", "123456789"));
        when(personRepo.findByContactNumber("123456789")).thenReturn(personList);

        // Act
        List<PersonDto> result = personService.getPersonByContact("123456789");

        // Assert
        assertEquals(1, result.size());
        assertEquals("123456789", result.get(0).getContactNumber());
    }

    @Test
    public void testGetPersonByContactKeyNum() {
        // Arrange
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "John", "Doe", "john@example.com", "123456789"));
        when(personRepo.findByContactNumberContaining("123")).thenReturn(personList);

        // Act
        List<PersonDto> result = personService.getPersonByContactKeyNum("123");

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.get(0).getContactNumber().contains("123"));
    }
}
