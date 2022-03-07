package com.infodev.bookrental.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @author rawalokes
 * Date:3/2/22
 * Time:2:52 PM
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Integer bookId;
    @NotEmpty
    private String name;

    @NotNull
    private Integer isbn;

    @NotNull
    @Min(value =1)
    private Integer stockCount;

    @NotNull
    @Min(value = 10)
    private Integer noOfPages;

    @NotNull
    @Min(value = 0)
    private Double rating;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    private Date publishDate;

    private String pathUrl;

    @NotNull(message = "cannot be empty")
    private MultipartFile multipartFile;
    private Integer categoryId;
    private List<Integer> AuthorId;


}
