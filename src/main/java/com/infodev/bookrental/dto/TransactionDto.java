package com.infodev.bookrental.dto;

import com.infodev.bookrental.model.Transaction;
import lombok.*;

/**
 * @author rawalokes
 * Date:3/4/22
 * Time:1:38 PM
 */

@Getter
@Setter
@Builder
public class TransactionDto {
    private Integer transactionId;
    private Integer code;
    private String fromDate;
    private String toDate;
    private String rentStatus;
    private Integer book_id;
    private Integer member_id;

}
