package com.infodev.bookrental.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author rawalokes
 * Date:3/5/22
 * Time:3:33 PM
 */
public class RentDto {

    private Integer book_id;

    private Integer member_id;
    @NotEmpty(message = "code cannot be empty")
    private String code;
    @NotNull(message = "no of days cannot be empty")
    private Integer noOfDays;
}
