package com.spring.restApp.controller;

import com.spring.restApp.response.ApiResponse;
import com.spring.restApp.dto.PersonDto;
import com.spring.restApp.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/person")
@CrossOrigin
public class PersonRestController {
    @Autowired
    private PersonService personService;

    @Autowired
    private ModelMapper mapper;


    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<PersonDto>>> getAll(){
        List<PersonDto> personDtoList = this.personService.getAll();
        ApiResponse<List<PersonDto>> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Database Entries Retrieved");
        apiResponse.setContent(personDtoList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<ApiResponse<List<PersonDto>>> getPerson(@PathVariable Integer personId){
        PersonDto personDto = this.personService.getPerson(personId);
        ApiResponse<List<PersonDto>> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Record of Person ID: "+personId);
        apiResponse.setContent(Collections.singletonList(personDto));
        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<ApiResponse<List<PersonDto>>> getPersonByFirstName(@PathVariable String firstName){
        List<PersonDto> personDtoList = this.personService.getPersonByFirstName(firstName);

        ApiResponse<List<PersonDto>> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Records for First Name: "+firstName);
        apiResponse.setContent(personDtoList);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<ApiResponse<List<PersonDto>>> getPersonByLastName(@PathVariable String lastName){
        List<PersonDto> personDtoList = this.personService.getPersonByLastName(lastName);

        ApiResponse<List<PersonDto>> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Record for Last Name: "+lastName);
        apiResponse.setContent(personDtoList);

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<List<PersonDto>>> getPersonByEmail(@PathVariable String email){
        List<PersonDto> personDtoList = this.personService.getPersonByEmail(email);

        ApiResponse<List<PersonDto>> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Records for Email: "+email);
        apiResponse.setContent(personDtoList);

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @GetMapping("/emailLike/{keyword}")
    public ResponseEntity<ApiResponse<List<PersonDto>>> getPersonByEmailKeyword(@PathVariable String keyword) {
        List<PersonDto> personDtoList = this.personService.getPersonByEmailKeyword(keyword);

        ApiResponse<List<PersonDto>> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Records for partial Email match: "+keyword);
        apiResponse.setContent(personDtoList);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/contact/{contact}")
    public ResponseEntity<ApiResponse<List<PersonDto>>> getPersonByContact(@PathVariable String contact) {
        List<PersonDto> personDtoList = this.personService.getPersonByContact(contact);

        ApiResponse<List<PersonDto>> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Records for Contact number: "+contact);
        apiResponse.setContent(personDtoList);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/contactLike/{keywordNum}")
    public ResponseEntity<ApiResponse<List<PersonDto>>> getPersonByContactKeyNum(@PathVariable String keywordNum) {
        List<PersonDto> personDtoList = this.personService.getPersonByContactKeyNum(keywordNum);

        ApiResponse<List<PersonDto>> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Records for partial contact number match: "+keywordNum);
        apiResponse.setContent(personDtoList);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<ApiResponse<PersonDto>> addPersonRecord(@RequestBody PersonDto personDto){
        PersonDto saved = this.personService.addPersonRecord(personDto);

        ApiResponse<PersonDto> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Record saved");
        apiResponse.setContent(saved);
        apiResponse.setHttpStatus(HttpStatus.CREATED);

        return  new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }

    @PutMapping("/{personId}")
    public ResponseEntity<ApiResponse<PersonDto>> updatePersonRecord(@PathVariable Integer personId,@RequestBody PersonDto personDto){
        PersonDto updated = this.personService.updatePersonRecord(personId,personDto);

        ApiResponse<PersonDto> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Record updated");
        apiResponse.setContent(updated);

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<ApiResponse<String>> deletedRecord(@PathVariable Integer personId){
        String message = this.personService.deletedRecord(personId);

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Record Deleted");
        apiResponse.setContent(message);

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
