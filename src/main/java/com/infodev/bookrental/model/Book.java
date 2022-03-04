package com.infodev.bookrental.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author rawalokes
 * Date:2/21/22
 * Time:2:40 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="brs_book")
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Book_SEQ_GEN")
    @SequenceGenerator(name = "Book_SEQ_GEN",sequenceName = "Book_SEQ",allocationSize = 1)
    private Integer bookId;
    @Column(name = "book_name",length = 50)
    private String name;
    @Column(name = "isbn",length = 20)
    private Integer isbn;
    @Column(name = "book_stock")
    private Integer stockCount;
    @Column(name = "no_of_pages")
    private Integer noOfPages;
    @Column(name = "rating")
    private Double rating;

    private Date publishDate;
    @Column(name = "book_path")
    private String photoUrl;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "catrgory_id",referencedColumnName = "catrgory_id",
            foreignKey = @ForeignKey(name = "FK_Book_Category"))
    private Category category;
    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name = "brs_book_author")
    private List<Author> authors;

}
