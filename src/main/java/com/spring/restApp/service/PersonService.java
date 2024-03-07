package com.spring.restApp.service;

import com.spring.restApp.dto.PersonDto;

import java.util.List;

public interface PersonService {
    List<PersonDto> getAll();

    PersonDto getPerson(Integer personId);

    PersonDto addPersonRecord(PersonDto personDto);

    PersonDto updatePersonRecord(Integer personId, PersonDto personDto);

    String deletedRecord(Integer personId);

    List<PersonDto> getPersonByFirstName(String firstName);

    List<PersonDto> getPersonByLastName(String lastName);

    List<PersonDto> getPersonByEmail(String email);

    List<PersonDto> getPersonByEmailKeyword(String keyword);

    List<PersonDto> getPersonByContact(String contact);

    List<PersonDto> getPersonByContactKeyNum(String keywordNum);
}
