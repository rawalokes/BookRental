package com.infodev.bookrental.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
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
    private String name;
    private Integer isbn;
    private Integer stockCount;
    private Integer noOfPages;
    private Double rating;
    private Date publishDate;
    private String photoUrl;
    private MultipartFile multipartFile;
    private Integer categoryId;
    private List<Integer> AuthorId;


}
