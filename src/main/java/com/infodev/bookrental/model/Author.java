package com.infodev.bookrental.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author rawalokes
 * Date:2/21/22
 * Time:10:29 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "brs_author")

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Author_SEQ_GEN")
    @SequenceGenerator(name ="Author_SEQ_GEN",sequenceName = "Author_SEQ",allocationSize = 1)
    @Column(name = "author_id")
    private Integer id;

    @Column(name = "author_name",length = 30)
    private String name;

    @Column(name ="author_email",length = 100 )
    private String email;

    @Column(name ="author_phone", length = 10)
    private String phone;

}
