package com.infodev.bookrental.dto;

import com.infodev.bookrental.enums.RentType;
import com.infodev.bookrental.model.Book;
import lombok.*;

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
    private Integer code;
    private LocalDate fromDate;
    private Integer noOfDays = 0;
    private LocalDate toDate;
    private RentType rentType;
    private Integer book_id;
    private Integer member_id;
   private String bookName;
}
