package com.epam.libraryservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private int bookId;
    private String bookName;
    private String publisherName;
    private String authorName;
}
