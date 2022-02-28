package com.infodev.bookrental.model;

import lombok.*;

import javax.persistence.*;

/**
 * @catrgory rawalokes
 * Date:2/21/22
 * Time:10:46 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "brs_catrgory")

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catrgory_SEQ_GEN")
    @SequenceGenerator(name = "catrgory_SEQ_GEN", sequenceName = "catrgory_SEQ", allocationSize = 1)
    @Column(name = "catrgory_id")
    private Integer id;
    @Column(name = "catrgory_name")
    private String name;
    @Column(name = "description",length = 2000)
    private String description;


}
