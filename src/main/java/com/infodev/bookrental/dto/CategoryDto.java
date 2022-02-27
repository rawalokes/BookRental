package com.infodev.bookrental.dto;

import lombok.*;


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
    private String name;
    private String discription;

}
