package com.infodev.bookrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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

@Builder
@Table(name ="brs_book", uniqueConstraints = {
        @UniqueConstraint(name = "Book_Name", columnNames = {"book_name"}),
        @UniqueConstraint(name = "Book_Isbn", columnNames = {"isbn"})
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Book_SEQ_GEN")
    @SequenceGenerator(name = "Book_SEQ_GEN",sequenceName = "Book_SEQ",allocationSize = 1)
    private Integer bookId;

    @Column(name = "book_name",length = 50)
    private String name;

    @Column(name = "isbn",length = 20)
    private String isbn;

    @Column(name = "book_stock")
    private Integer stockCount;

    @Column(name = "no_of_pages")
    private Integer noOfPages;

    @Column(name = "rating")
    private Double rating;

    private String publishDate;
    @Column(name = "book_path")
    private String photoUrl;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_Book_Category"))
    private Category category;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(name = "brs_book_author")
    private List<Author> authors;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    List<Transaction> bookTransactions;

}
