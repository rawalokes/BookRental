package com.infodev.bookrental.dto;

import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author rawalokes
 * Date:2/27/22
 * Time:9:38 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private Integer id;
    @Size(max = 40,min = 3)
    @NotEmpty
    private String name;

    @Email
    private String email;

    @NumberFormat
    private String mobileNumber;

    @NotEmpty
    private String address;
}
