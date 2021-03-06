package com.infodev.bookrental.dto;

import lombok.*;

import javax.validation.constraints.*;

/**
 * @author rawalokes
 * Date:2/21/22
 * Time:11:39 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class AuthorDto {

    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 3, message = "invalid name")
    @Pattern(regexp = "[a-zA-Z]+",message = "Name cannot have numbers or special character")
    private String name;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "invalid email address")
    private String email;
    @NotEmpty(message = "mobile cannot be empty")
    @Size(min = 10,max = 10, message = "invalid mobile no")
    @Pattern(regexp ="[9][0-9]{9}",message = "invalid number")
    private String mNumber;
}
