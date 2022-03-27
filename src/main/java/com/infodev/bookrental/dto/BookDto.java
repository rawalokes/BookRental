package com.infodev.bookrental.dto;

import com.infodev.bookrental.model.Author;
import com.infodev.bookrental.model.Category;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
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
    @NotEmpty(message = "name cannot be empty")
    @Pattern(regexp = "[a-zA-Z]+",message = "Name cannot have numbers or special character")
    private String name;

    @NotEmpty(message = "isbn cannot be empty")
    private String isbn;

    @NotNull(message = "stock cannot be empty")
    @Min(value = 1, message = "invalid stock")
    private Integer stockCount;

    @NotNull(message = "pages cannot be empty")
    @Min(value = 10, message = "invalid no of pages")
    private Integer noOfPages;

    @NotNull(message = "rating cannot be empty ")
    @Min(value = 0, message = "min rating is 0")
    @Max(value = 5, message = "max rating is 5")
    private Double rating;

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Temporal(TemporalType.DATE)
    private String publishDate;

    private String pathUrl;
    @NotNull(message = "please select an image")
    private MultipartFile multipartFile;

    private Integer categoryId;
    private List<Integer> AuthorId;

    private List<AuthorDto> authorDtoList;
    private List<Author> authors;
    private Category category;
    private CategoryDto categoryDto;

    public static class BookDtoBuilder {
        public BookDtoBuilder categoryDto(Category category) {

            this.categoryDto = CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            return this;
        }



    }
}
