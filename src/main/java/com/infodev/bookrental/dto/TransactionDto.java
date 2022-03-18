package com.infodev.bookrental.dto;

import com.infodev.bookrental.enums.RentType;
import com.infodev.bookrental.model.Book;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author rawalokes
 * Date:3/4/22
 * Time:1:38 PM
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private Integer transactionId;
    @NotEmpty(message = "code cannot be empty")
    private String code;
    private LocalDate fromDate;
    @NotNull(message = "no of days cannot be empty")
    private Integer noOfDays = 0;
    private LocalDate toDate;

    private RentType rentType;
    @NotNull(message = "Book cannot be empty")
    private Integer book_id;

    @NotNull(message = "Member cannot be empty")
    private Integer member_id;
   private Book book;
}
