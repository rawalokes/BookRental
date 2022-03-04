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
    @NotNull
    @Min(value = 1,message = "id must be greater than zero")
    private Integer id;

    @NotEmpty()
    @Size(min = 3 ,message = "invalid name")
    private String name;

    @NotEmpty
    @Email(message = "invalid email address")
    private String email;

    @NotNull
    @Size(max = 10,message = "invalid mobile no")
    @Size(min = 10,message = "invalid mobile no")
    private String number;
}
