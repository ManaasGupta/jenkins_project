package com.spring.restApp.service.impl;

import com.spring.restApp.dto.PersonDto;
import com.spring.restApp.entity.Person;
import com.spring.restApp.repo.PersonRepo;
import com.spring.restApp.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<PersonDto> getAll() {
        List<Person> personList = this.repo.findAll();
        return personList.stream().map(person -> mapper.map(person,PersonDto.class)).toList();
    }

    @Override
    public PersonDto getPerson(Integer personId) {
        Person getRec = null;
        try {
            getRec = this.repo.findById(personId).orElseThrow(()->new Exception("Record not Found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return mapper.map(getRec,PersonDto.class);
    }

    @Override
    public PersonDto addPersonRecord(PersonDto personDto) {
        Person rec = mapper.map(personDto,Person.class);
        this.repo.save(rec);
        return mapper.map(rec,PersonDto.class);
    }

    @Override
    public PersonDto updatePersonRecord(Integer personId, PersonDto personDto) {
        Person getRec = null;
        try {
            getRec = this.repo.findById(personId).orElseThrow(()->new Exception("Record not Found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        PersonDto updatedRec = mapper.map(getRec,PersonDto.class);
        updatedRec.setFirstName(personDto.getFirstName());
        updatedRec.setLastName(personDto.getLastName());
        updatedRec.setEmail(personDto.getEmail());
        updatedRec.setContactNumber(personDto.getContactNumber());
        Person savedRec = mapper.map(updatedRec,Person.class);
        this.repo.save(savedRec);
        return mapper.map(savedRec,PersonDto.class);
    }

    @Override
    public String deletedRecord(Integer personId) {
        Person getRec = null;
        try {
            getRec = this.repo.findById(personId).orElseThrow(()->new Exception("Record not Found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.repo.delete(getRec);
        return "Record Deleted Successfully";
    }

    @Override
    public List<PersonDto> getPersonByFirstName(String firstName) {
        List<Person> personList = this.repo.findByFirstName(firstName);
        return personList.stream().map(person -> mapper.map(person,PersonDto.class)).toList();
    }

    @Override
    public List<PersonDto> getPersonByLastName(String lastName) {
        List<Person> personList = this.repo.findByLastName(lastName);
        return personList.stream().map(person -> mapper.map(person,PersonDto.class)).toList();
    }

    @Override
    public List<PersonDto> getPersonByEmail(String email) {
        List<Person> personList = this.repo.findByEmail(email);
        return personList.stream().map(person -> mapper.map(person,PersonDto.class)).toList();
    }

    @Override
    public List<PersonDto> getPersonByEmailKeyword(String keyword) {
        List<Person> personList = this.repo.findByEmailContaining(keyword);
        return personList.stream().map(person -> mapper.map(person,PersonDto.class)).toList();
    }

    @Override
    public List<PersonDto> getPersonByContact(String contact) {
        List<Person> personList = this.repo.findByContactNumber(contact);
        return personList.stream().map(person -> mapper.map(person,PersonDto.class)).toList();
    }

    @Override
    public List<PersonDto> getPersonByContactKeyNum(String keywordNum) {
        List<Person> personList = this.repo.findByContactNumberContaining(keywordNum);
        return personList.stream().map(person -> mapper.map(person,PersonDto.class)).toList();
    }
    
    public PersonServiceImpl(PersonRepo repo,ModelMapper mapper) {
    	this.mapper=mapper;
    	this.repo= repo;
    }

}
