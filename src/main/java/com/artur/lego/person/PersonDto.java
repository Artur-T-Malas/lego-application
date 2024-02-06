package com.artur.lego.person;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    @NotBlank(message = "Nickname can't be blank!")
    private String nickname;

    @NotBlank(message = "Email can't be blank!")
    private String email;

    private String firstName;
    private String lastName;
    private String phoneNumber;

    public PersonDto(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;
    }

    public PersonDto(String nickname, String email, String firstName) {
        this.nickname = nickname;
        this.email = email;
        this.firstName = firstName;
    }

    public PersonDto(String nickname, String email, String firstName, String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
    }


}
