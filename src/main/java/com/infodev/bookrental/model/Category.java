package com.infodev.bookrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

@Table(name = "brs_catrgory", uniqueConstraints = {
        @UniqueConstraint(name = "Category_Name", columnNames = {"category_name"}),

})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catrgory_SEQ_GEN")
    @SequenceGenerator(name = "catrgory_SEQ_GEN", sequenceName = "catrgory_SEQ", allocationSize = 1)

    private Integer id;
    @Column(name = "category_name")
    private String name;
    @Column(name = "description",length = 2000)
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> books;

}

