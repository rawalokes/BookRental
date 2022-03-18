package com.infodev.bookrental.model;

import com.infodev.bookrental.enums.RentType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author rawalokes
 * Date:3/5/22
 * Time:1:21 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "brs_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Transaction_SEQ_GEN")
    @SequenceGenerator(name = "Transaction_SEQ_GEN",sequenceName = "Transaction_SEQ",allocationSize = 1)
    private Integer transactionId;

    private String code;

    private LocalDate fromDate;

    private LocalDate toDate;

    @Enumerated(value = EnumType.STRING)
    private RentType rentType;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Book book;

}

