package com.spring.restApp.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto
{
    private Integer personId;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNumber;
}
