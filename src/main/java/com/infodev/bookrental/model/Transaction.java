package com.infodev.bookrental.model;

import lombok.*;

import javax.persistence.*;

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
@Table(name = "brs_transaction")
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Transaction_SEQ_GEN")
    @SequenceGenerator(name = "Transaction_SEQ_GEN",sequenceName = "Transaction_SEQ",allocationSize = 1)
    private Integer transactionId;
    private Integer code;
    private String fromDate;
    private String toDate;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Book book;

}

