package com.infodev.bookrental.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * @author rawalokes
 * Date:2/21/22
 * Time:11:15 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Integer id;
    @NotEmpty
    @Size(min = 3,max = 100,message = "invalid name")
    private String name;
    @NotEmpty(message = "invalid description ")
    @Size(min = 3,max = 100,message = "invalid description")
    private String description;

}
