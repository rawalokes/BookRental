package com.infodev.bookrental.dto;

import lombok.*;

/**
 * @author rawalokes
 * Date:2/24/22
 * Time:1:43 PM
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private boolean responseStatus;
    private String response;
    private BookDto bookDto;
    private CategoryDto categoryDto;
    private MemberDto memberDto;
    private AuthorDto authorDto;
}
