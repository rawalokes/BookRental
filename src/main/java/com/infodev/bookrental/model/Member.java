package com.infodev.bookrental.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author rawalokes
 * Date:2/27/22
 * Time:9:24 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "brs_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @SequenceGenerator(name = "Member_SQL_GEN", sequenceName = "Member_SEQ", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "number")
    private String mobileNumber;

    @Column(name = "address")
    private String address;


}
